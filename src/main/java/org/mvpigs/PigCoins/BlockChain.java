package org.mvpigs.PigCoins;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;

public class BlockChain {

    private LinkedList<Transaction> blockChain = new LinkedList<Transaction>();

    public void addOrigin(Transaction transaction) {

        blockChain.add(transaction);
    }

    public void summarize() {
        
        for (Transaction transaccion : blockChain) {
            System.out.println(transaccion.toString());
        }
    }

    public void summarize(int index) {
        Transaction transaction = blockChain.get(index);
        System.out.println(transaction.toString());
    }

    public LinkedList<Transaction> getBlockChain() {
        return this.blockChain;
    }

    public Boolean isConsumedCoinValid(Transaction consumedCoins) {

        for (Transaction transaction : this.getBlockChain()) {
            if (transaction.equals(consumedCoins)){
                return true;
            } else;
        } return false;
    }



    /* Comprueba en el blockChain todas las transacciones cuya pkRecipt coincida con la Address de la Wallet que le pasamos,
     y las devuelve en un lista */

    public ArrayList<Transaction> loadInputTransactions(PublicKey address) {
        
        
         ArrayList<Transaction> inputTransactions;
        inputTransactions = getBlockChain().stream().filter(transact -> transact.getpKey_recipient().equals(address)).collect(Collectors.toCollection(ArrayList<Transaction>::new));
        /*
        for (Transaction transaccion : blockChain) {
            if (transaccion.getpKey_recipient() == address) {
                inputTransactions.add(transaccion);
            }   
        }*/
        return inputTransactions;
    }

    public ArrayList<Transaction> loadOutputTransactions(PublicKey address) {

        ArrayList<Transaction> outPutTransactions;
        outPutTransactions = getBlockChain().stream().filter(transact -> transact.getpKey_sender().equals(address)).collect(Collectors.toCollection(ArrayList<Transaction>::new));
        /*
        for (Transaction transaccion : blockChain) {
            if (transaccion.getpKey_sender() == address) {
            outPutTransactions.add(transaccion);
            }   
        } */
        return outPutTransactions;
    }

    public Map<String,Double> loadWallet(PublicKey address) {
        double totalInput = 0d;
        double totalOutput = 0d;
        Map<String,Double> totalTrans = new HashMap<String,Double>();

        for (Transaction transaccion : this.loadInputTransactions(address)) {
            totalInput += transaccion.getPigCoins();
        }
        totalTrans.put("totalInput", Double.valueOf(totalInput));

        for (Transaction transaccion : this.loadOutputTransactions(address)) {
            totalOutput += transaccion.getPigCoins();
        }
        totalTrans.put("totalOutput", Double.valueOf(totalOutput));

        return totalTrans;

    }

    public boolean isSignatureValid(PublicKey pKey_sender, String message, byte[] signedTransaction) {

        return GenSig.verify(pKey_sender, message, signedTransaction);

    }

    public void createTransaction(PublicKey pKey_sender,PublicKey pKey_recipient, Transaction consumedCoins ,String message,byte[] signedTransaction) {

        String hash = "Hash"+(this.getBlockChain().size()+1);
        String prev_hash = this.getBlockChain().getLast().getHash();
        double pigCoins = consumedCoins.getPigCoins();
        Transaction transaction = new Transaction(hash, prev_hash, pKey_sender, pKey_recipient, pigCoins, message);
        transaction.setSignature(signedTransaction);
        blockChain.add(transaction);
    }

    public void processTransactions(PublicKey pKey_sender,PublicKey pKey_recipient, Transaction consumedCoins,String message,byte[] signedTransaction) {

        if (isSignatureValid(pKey_sender, message, signedTransaction) & isConsumedCoinValid(consumedCoins)) {
            createTransaction(pKey_sender, pKey_recipient, consumedCoins, message, signedTransaction);
        } 
    }
}
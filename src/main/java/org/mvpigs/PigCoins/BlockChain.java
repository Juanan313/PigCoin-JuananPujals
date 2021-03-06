package org.mvpigs.PigCoins;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.Painter;

public class BlockChain {

    private LinkedList<Transaction> blockChain = new LinkedList<Transaction>();

    public void addOrigin(Transaction transaction) {

        blockChain.add(transaction);
    }

    public void sumarize() {
        
        for (Transaction transaccion : blockChain) {
            System.out.println(transaccion.toString());
        }
    }

    public void sumarize(int index) {
        Transaction transaction = blockChain.get(index);
        System.out.println(transaction.toString());
    }

    public LinkedList<Transaction> getBlockChain() {
        return this.blockChain;
    }

    public Boolean isConsumedCoinValid(Transaction consumedCoins) {

        if (blockChain.contains(consumedCoins)) {
            return false;
        } else {
            return true;
        }
    }



    /* Comprueba en el blockChain todas las transacciones cuya pkRecipt coincida con la Address de la Wallet que le pasamos,
     y las devuelve en un lista */

    public ArrayList<Transaction> loadInputTransactions(PublicKey address) {
        
        ArrayList<Transaction> inputTransactions = new ArrayList<Transaction>();

        for (Transaction transaccion : blockChain) {
            if (transaccion.getpKey_recipient() == address) {
                inputTransactions.add(transaccion);
            }   
        }
        return inputTransactions;
    }

    public ArrayList<Transaction> loadOutputTransactions(PublicKey address) {

        ArrayList<Transaction> outPutTransactions = new ArrayList<Transaction>();

        for (Transaction transaccion : blockChain) {
            if (transaccion.getpKey_recipient() == address) {
            outPutTransactions.add(transaccion);
            }   
        } return outPutTransactions;
    }

    public double[] loadWallet(PublicKey address) {
        double totalInput = 0d;
        double totalOutput = 0d;
        double[] totalTrans = new double[2];

        for (Transaction transaccion : this.loadInputTransactions(address)) {
            totalInput += transaccion.getPigCoins();
        }
        totalTrans[0] = totalInput;

        for (Transaction transaccion : this.loadOutputTransactions(address)) {
            totalOutput += transaccion.getPigCoins();
        }
        totalTrans[1] = totalOutput;

        return totalTrans;

    }

    public boolean isSignatureValid(PublicKey pKey_sender, String message, byte[] signedTransaction) {

        return GenSig.verify(pKey_sender, message, signedTransaction);

    }

    public void createTransaction(PublicKey pKey_sender,PublicKey pKey_recipient, Transaction consumedCoins ,String message,byte[] signedTransaction) {

        String hash = "Hash"+blockChain.size()+1;
        String prev_hash = blockChain.getLast().getHash();
        double `pigCoins = consumedCoins.getPigCoins();
        Transaction transaction = new Transaction(hash, prev_hash, pigCoins, message);
        transaction.setSignature(signedTransaction);
        blockChain.add(transaction);
    }

    public void processTransactions(PublicKey pKey_sender,PublicKey pKey_recipient, Transaction consumedCoins,String message,byte[] signedTransaction) {

        if (isSignatureValid(pKey_sender, message, signedTransaction) & isConsumedCoinValid(consumedCoins)) {
            createTransaction(pKey_sender, pKey_recipient, consumedCoins, message, signedTransaction);
        } else;
        System.out.println("Esta transaccion ya ha sido utilizada");
    }
}
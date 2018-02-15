package org.mvpigs.PigCoins;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.LinkedList;

public class BlockChain {

    private LinkedList<Transaction> blockChain = new LinkedList<Transaction>();

    public void addOrigin(Transaction transaction) {

        blockChain.add(transaction);
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
    
}
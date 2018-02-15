package org.mvpigs.PigCoins;

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


}
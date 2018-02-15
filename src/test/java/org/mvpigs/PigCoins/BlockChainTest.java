package org.mvpigs.PigCoins;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

public class BlockChainTest {

    Transaction transaccionTest;
    private Wallet walletOutput;
    private Wallet walletInput;
    private Transaction transaccionTest2;
    private BlockChain blockChainTest;

    @Before
    public void setUp() {
        walletOutput = new Wallet();
        walletInput = new Wallet();
        walletOutput.generateKeyPair();
        walletInput.generateKeyPair();

        transaccionTest = new Transaction("hash1", "0", 20, "You're the real mvpig!");
        transaccionTest.setpKey_sender(walletOutput.getAddress());
        transaccionTest.setpKey_recipient(walletInput.getAddress());

        transaccionTest2 = new Transaction("hash2", "hash1", 10, "You're not the real mvpig!");
        transaccionTest2.setpKey_sender(walletInput.getAddress());
        transaccionTest2.setpKey_recipient(walletOutput.getAddress());

        blockChainTest = new BlockChain();
    }

    // Test AddOrigin - añade transacciones a BlockChain
    @Test
    public void blockChainAddOriginTest() {

        blockChainTest.addOrigin(transaccionTest);
        blockChainTest.addOrigin(transaccionTest2);

        assertEquals(blockChainTest.getBlockChain().size(), 2);
    }
    @Test
    public void isConsumedCoinValid() {

        blockChainTest.addOrigin(transaccionTest);
        blockChainTest.addOrigin(transaccionTest2);
        assertFalse(blockChainTest.isConsumedCoinValid(transaccionTest));
    }

    @Test
    public void loadInputTransactions() {

        blockChainTest.addOrigin(transaccionTest);
        blockChainTest.addOrigin(transaccionTest2);

        blockChainTest.loadInputTransactions(walletInput);

        assertEquals(blockChainTest.loadInputTransactions(walletInput).size(), 1);
    }
}
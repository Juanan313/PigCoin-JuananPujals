package org.mvpigs.PigCoins;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

public class BlockChainTest {

    Transaction transaccionTest;
    private Wallet walletOutput;
    private Wallet walletInput;
    private Wallet walletVacia;
    private Transaction transaccionTest2;
    private BlockChain blockChainTest;

    @Before
    public void setUp() {
        walletOutput = new Wallet();
        walletInput = new Wallet();
        walletVacia = new Wallet();
        walletOutput.generateKeyPair();
        walletInput.generateKeyPair();
        walletVacia.generateKeyPair();

        transaccionTest = new Transaction("hash1", "0", 20, "You're the real mvpig!");
        transaccionTest.setpKey_sender(walletOutput.getAddress());
        transaccionTest.setpKey_recipient(walletInput.getAddress());

        transaccionTest2 = new Transaction("hash2", "hash1", 10, "You're not the real mvpig!");
        transaccionTest2.setpKey_sender(walletInput.getAddress());
        transaccionTest2.setpKey_recipient(walletOutput.getAddress());

        blockChainTest = new BlockChain();

        blockChainTest.addOrigin(transaccionTest);
        blockChainTest.addOrigin(transaccionTest2);
    }

    // Test AddOrigin - añade transacciones a BlockChain
    @Test
    public void blockChainAddOriginTest() {

        assertEquals(blockChainTest.getBlockChain().size(), 2);
    }

    // Test isConsumedCoinValid , comprueba que la transaccion que se intenta realizar no se ha utilizado ya
    @Test
    public void isConsumedCoinValid() {

        assertFalse(blockChainTest.isConsumedCoinValid(transaccionTest));
    }

    // Test load
    @Test
    public void loadInputTransactions() {

        assertEquals(blockChainTest.loadInputTransactions(walletInput.getAddress()).size(), 1);
        assertEquals(blockChainTest.loadInputTransactions(walletVacia.getAddress()).size(), 0);
    }

    @Test
    public void loadOutputTransactionsTest() {

        assertEquals(blockChainTest.loadOutputTransactions(walletInput.getAddress()).size(), 1);
        assertEquals(blockChainTest.loadOutputTransactions(walletVacia.getAddress()).size(), 0);
    }

    @Test
    public void sumarizeTest() {
        blockChainTest.sumarize();
    }

    @Test
    public void sumarizeOverloadTest() {
        blockChainTest.sumarize(1);
    }
}
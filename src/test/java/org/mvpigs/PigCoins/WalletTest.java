package org.mvpigs.PigCoins;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class WalletTest {

    Wallet walletTest;
    Wallet walletTest2;
    Wallet walletVacia;
	private Transaction transaccionTest;
	private BlockChain blockChainTest;

    @Before
    public void setUp() {
        walletTest = new Wallet();
        walletTest2 = new Wallet();
        walletVacia = new Wallet();
        walletTest.generateKeyPair();
        walletTest2.generateKeyPair();

        transaccionTest = new Transaction("hash1", "0", 20, "You're the real mvpig!");
        transaccionTest.setpKey_sender(walletTest.getAddress());
        transaccionTest.setpKey_recipient(walletTest2.getAddress());

        transaccionTest = new Transaction("hash2", "hash1", 10, "You're not the real mvpig!");
        transaccionTest.setpKey_sender(walletTest2.getAddress());
        transaccionTest.setpKey_recipient(walletTest.getAddress());

        blockChainTest = new BlockChain();

        blockChainTest.addOrigin(transaccionTest);
        blockChainTest.addOrigin(transaccionTest);
    }

    /* Compruebo que la funcion GenerateKeyPair funcione*/
    @Test
    public void walletGenerateKeyPairTest() {

        walletTest.generateKeyPair();
        assertNotNull(walletTest.getAddress());
        assertNotNull(walletTest.getSKey());
        assertNull(walletVacia.getAddress());
    }
    @Test
    public void walletLoadInputTransTest() {

        walletTest.loadInputTransactions(blockChainTest);
        assertNotNull(walletTest.getInputTransactions());
    }

    @Test
    public void walletLoadOutputTransTest() {

        walletTest.loadOutputTransactions(blockChainTest);
        assertNotNull(walletTest.getOutputTransactions());
    }

    @Test
    public void walletLoadCoins() {

        walletTest.loadCoins(blockChainTest);
        assertEquals(walletTest.getTotal_input(), 10, 0.1);
        assertEquals(walletTest.getTotal_output(), 20, 0.1);
    }

}
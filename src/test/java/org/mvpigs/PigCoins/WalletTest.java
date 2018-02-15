package org.mvpigs.PigCoins;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class WalletTest {

    Wallet walletTest;
    Wallet walletTest2;
    @Before 
    public void setUp() {
        walletTest = new Wallet();
        walletTest2 = new Wallet();

    }
    /* Compruebo que la funcion GenerateKeyPair funcione*/
    @Test
    public void walletGenerateKeyPairTest() {
        
        walletTest.generateKeyPair();
        assertNotNull(walletTest.getAddress());
        assertNotNull(walletTest.getSKey());
        assertNull(walletTest2.getAddress());
    }
}
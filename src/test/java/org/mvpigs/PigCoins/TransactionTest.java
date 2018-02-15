package org.mvpigs.PigCoins;

import org.junit.Before;
import org.junit.Test;

public class TransactionTest {

    Transaction transaccion;
    Wallet walletOutput;
    Wallet walletInput;

    @Before
    public void setUp() {

        walletOutput = new Wallet();
        walletInput = new Wallet();
        walletOutput.generateKeyPair();
        walletInput.generateKeyPair();

        transaccion = new Transaction("hash1", "0", 20, "You're the real mvpig!");
        transaccion.setpKey_sender(walletOutput.getAddress());
        transaccion.setpKey_recipient(walletInput.getAddress());

    }

    @Test
    public void TransactionConstructorTest() {

        System.out.println(transaccion.toString());
    }

}
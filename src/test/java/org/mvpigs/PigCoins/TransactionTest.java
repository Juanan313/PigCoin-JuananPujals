package org.mvpigs.PigCoins;

import java.security.KeyPair;
import org.junit.Before;
import org.junit.Test;

public class TransactionTest {

    Transaction transaccion;
    @Before
    public void setUp() {

        KeyPair pair = GenSig.generateKeyPair();
        
        transaccion = new Transaction("hash1", "0", 20, "You're the real mvpig!");
        transaccion.setpKey_sender(pair.getPublic());
        transaccion.setpKey_recipient(pair.getPublic());
        
    }
    @Test
    public void TransactionConstructorTest() {

        System.out.println(transaccion.toString());
    }


}
package org.mvpigs.PigCoins;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class BlockChainTest {

    Transaction transaccionTest;
    private Wallet wallet1;
    private Wallet wallet2;
    private Wallet walletVacia;
    private Transaction transaccionTest2;
    private BlockChain blockChainTest;
    private Transaction validTransaction;

    @Before
    public void setUp() {
        wallet1 = new Wallet();
        wallet2 = new Wallet();
        walletVacia = new Wallet();
        wallet1.generateKeyPair();
        wallet2.generateKeyPair();
        walletVacia.generateKeyPair();

        transaccionTest = new Transaction("hash1", "0", 20, "You're the real mvpig!");
        transaccionTest.setpKey_sender(wallet1.getAddress());
        transaccionTest.setpKey_recipient(wallet2.getAddress());
        transaccionTest.setSignature(GenSig.sign(wallet1.getSKey(),transaccionTest.getMessage()));

        transaccionTest2 = new Transaction("hash2", "hash1", 10, "You're not the real mvpig!");
        transaccionTest2.setpKey_sender(wallet2.getAddress());
        transaccionTest2.setpKey_recipient(wallet1.getAddress());
        transaccionTest2.setSignature(GenSig.sign(wallet2.getSKey(), transaccionTest2.getMessage()));

        validTransaction = new Transaction("hash3", "hash2",wallet1.getAddress(),walletVacia.getAddress(),25, "ya queda menos");
        validTransaction.setSignature(GenSig.sign(wallet1.getSKey(), validTransaction.getMessage()));

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

        assertTrue(blockChainTest.isConsumedCoinValid(validTransaction));
        assertFalse(blockChainTest.isConsumedCoinValid(transaccionTest));
    }

    // Test load
    @Test
    public void loadInputTransactions() {

        assertEquals(blockChainTest.loadInputTransactions(wallet1.getAddress()).size(), 1);
        assertEquals(blockChainTest.loadInputTransactions(wallet2.getAddress()).size(), 1);
        assertEquals(blockChainTest.loadInputTransactions(walletVacia.getAddress()).size(), 0);
    }

    @Test
    public void loadOutputTransactionsTest() {
        
        assertEquals(blockChainTest.loadOutputTransactions(wallet1.getAddress()).size(), 1);
        assertEquals(blockChainTest.loadOutputTransactions(wallet2.getAddress()).size(), 1);
        assertEquals(blockChainTest.loadOutputTransactions(walletVacia.getAddress()).size(), 0);
    }

    @Test
    public void sumarizeTest() {
        blockChainTest.summarize();
    }

    @Test
    public void sumarizeOverloadTest() {
        blockChainTest.summarize(1);
        blockChainTest.summarize(0);
    }
    @Test
    public void loadWalletTest() {
        blockChainTest.loadWallet(wallet2.getAddress());
        wallet1.loadCoins(this.blockChainTest);
        wallet2.loadCoins(this.blockChainTest);
        assertEquals(wallet2.getTotal_input(), 20, 0.1);
        assertEquals(wallet2.getTotal_output(), 10, 0.1);
        assertEquals(wallet2.getBalance(),10,0.1);
        assertEquals(wallet1.getTotal_input(), 10, 0.1);
        assertEquals(wallet1.getTotal_output(), 20, 0.1);
        assertEquals(wallet1.getBalance(), -10, 0.1);
        
    }
    @Test
    public void isSignaturaValid() {

       assertTrue(blockChainTest.isSignatureValid(transaccionTest.getpKey_sender(), transaccionTest.getMessage(), transaccionTest.getSignature()));
       assertFalse(blockChainTest.isSignatureValid(transaccionTest.getpKey_sender(), "Ni de coña", transaccionTest.getSignature()));
       assertTrue(blockChainTest.isSignatureValid(transaccionTest2.getpKey_sender(), transaccionTest2.getMessage(), transaccionTest2.getSignature()));
       assertFalse(blockChainTest.isSignatureValid(transaccionTest2.getpKey_sender(), "Ni de coña", transaccionTest2.getSignature()));
    }

    @Test
    public void isConsumedCoinValidTest() {
        assertTrue(blockChainTest.isConsumedCoinValid(transaccionTest));
        assertTrue(blockChainTest.isConsumedCoinValid(transaccionTest2));
        assertFalse(blockChainTest.isConsumedCoinValid(validTransaction));

    }
    
    @Test
    public void processTransactionTest() {

        blockChainTest.processTransactions(validTransaction.getpKey_sender(), validTransaction.getpKey_recipient(), validTransaction, validTransaction.getMessage(), validTransaction.getSignature());
        sumarizeTest();
        }
    }


package org.mvpigs.PigCoins;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.security.PublicKey;

import org.junit.Before;
import org.junit.Test;

public class BlockChainTest {

    Transaction transaccionTest;
    private Wallet walletOutput;
    private Wallet walletInput;
    private Wallet walletVacia;
    private Transaction transaccionTest2;
    private BlockChain blockChainTest;
    private Transaction validTransaction;

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
        transaccionTest.setSignature(GenSig.sign(walletOutput.getSKey(),transaccionTest.getMessage()));

        transaccionTest2 = new Transaction("hash2", "hash1", 10, "You're not the real mvpig!");
        transaccionTest2.setpKey_sender(walletInput.getAddress());
        transaccionTest2.setpKey_recipient(walletOutput.getAddress());

        blockChainTest = new BlockChain();

        blockChainTest.addOrigin(transaccionTest);
        blockChainTest.addOrigin(transaccionTest2);

        validTransaction = new Transaction("hash3", "hash2", 25, "ya queda menos");
        validTransaction.setpKey_sender(walletOutput.getAddress());
        validTransaction.setpKey_sender(walletVacia.getAddress());
        validTransaction.setSignature(GenSig.sign(walletOutput.getSKey(), validTransaction.getMessage()));
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
    @Test
    public void loadWalletTest() {
        blockChainTest.loadWallet(walletInput.getAddress());

        walletInput.loadCoins(this.blockChainTest);
        assertEquals(walletInput.getTotal_input(), 20, 0.1);
        assertEquals(walletInput.getTotal_output(), 10, 0.1);
    }
    @Test
    public void isSignaturaValid() {

       assertTrue(blockChainTest.isSignatureValid(transaccionTest.getpKey_sender(), transaccionTest.getMessage(), transaccionTest.getSignature()));
    }
    @Test
    public void crearTransaction() {

        PublicKey pKey_sender = walletOutput.getAddress();
        PublicKey pKey_recipient = walletVacia.getAddress();
        Transaction consumedCoins = validTransaction;
        String message = validTransaction.getMessage();
        byte[] signedTransaction = validTransaction.getSignature();

        blockChainTest.processTransactions(pKey_sender, pKey_recipient, consumedCoins, message, signedTransaction);
        assertTrue(blockChainTest.getBlockChain().contains(validTransaction));

    }
 }

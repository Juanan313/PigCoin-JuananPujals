package org.mvpigs.PigCoins;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;

public class Wallet {

    private PublicKey address;
    private PrivateKey sKey;
    private int total_input;
    private int total_output;
    private int balance;
    private ArrayList<Transaction> inputTransactions = new ArrayList<Transaction>();
    private ArrayList<Transaction> outputTransactions = new ArrayList<Transaction>();

	public PublicKey getAddress() {
		return address;
	}

	public void setAddress(PublicKey address) {
		this.address = address;
	}

	public PrivateKey getSKey() {
		return sKey;
	}

	public void setSK(PrivateKey sKey) {
		this.sKey = sKey;
	}

	public int getTotal_input() {
		return total_input;
	}

	public void setTotal_input(int total_input) {
		this.total_input = total_input;
	}

	public int getTotal_output() {
		return total_output;
	}

	public void setTotal_output(int total_output) {
		this.total_output = total_output;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public ArrayList<Transaction> getInputTransactions() {
		return inputTransactions;
	}

	public void setInputTransactions(ArrayList<Transaction> inputTransactions) {
		this.inputTransactions = inputTransactions;
	}

	public ArrayList<Transaction> getOutputTransactions() {
		return outputTransactions;
	}

	public void setOutputTransactions(ArrayList<Transaction> outputTransactions) {
		this.outputTransactions = outputTransactions;
    }
    
    // No reescribo el metodo constructor para esta clase por que como se ve en el main no se utiliza.

    public void generateKeyPair() {
        KeyPair pair = GenSig.generateKeyPair();
        this.setSK(pair.getPrivate());
        this.setAddress(pair.getPublic());
    }
}

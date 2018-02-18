package org.mvpigs.PigCoins;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Map;
import org.mvpigs.PigCoins.Transaction;
import org.mvpigs.PigCoins.BlockChain;
import org.mvpigs.PigCoins.GenSig;

public class Wallet {

	private PublicKey address;
	private PrivateKey sKey;
	private double total_input;
	private double total_output;
	private double balance;
	private ArrayList<Transaction> inputTransactions = new ArrayList<Transaction>();
	private ArrayList<Transaction> outputTransactions = new ArrayList<Transaction>();

	public String toString() {
		return ("Wallet: " + this.getAddress().hashCode() + "\n" + "Total input: " + getTotal_input() + "\n"
				+ "Total output: " + getTotal_output() + "\n" + "Balance: " + getBalance());
	}
	
	/* Getters y setters */

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

	public double getTotal_input() {
		return total_input;
	}

	public void setTotal_input(double total_input) {
		this.total_input = total_input;
	}

	public double getTotal_output() {
		return total_output;
	}

	public void setTotal_output(double total_output) {
		this.total_output = total_output;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
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
	/*--------------------------------------*/


	// No reescribo el metodo constructor para esta clase por que como se ve en el main no se utiliza.

	public void generateKeyPair() {
		KeyPair pair = GenSig.generateKeyPair();
		this.setSK(pair.getPrivate());
		this.setAddress(pair.getPublic());
	}

	public void signTransaction( String message) {
		GenSig.sign(this.getSKey(), message);
	}

	public void loadCoins(BlockChain bChain) {

		Map<String, Double> totalTrans = bChain.loadWallet(this.getAddress());
		this.setTotal_input(totalTrans.get("totalInput"));
		this.setTotal_output(totalTrans.get("totalOutput"));
		this.setBalance(total_input-total_output);
	}

	public void loadInputTransactions(BlockChain bChain) {
		this.setInputTransactions(bChain.loadInputTransactions(this.getAddress()));
	}

	public void loadOutputTransactions(BlockChain bChain) {
		this.setOutputTransactions(bChain.loadOutputTransactions(this.getAddress()));
	}

	
}

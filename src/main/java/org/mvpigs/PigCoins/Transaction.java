package org.mvpigs.PigCoins;

import java.security.PublicKey;

public class Transaction {

	private String hash = "";
	private String prev_hash = "";
	private String message = "";
	private int pigCoins = 0;
	private PublicKey pKey_sender;
	private PublicKey pKey_recipient;
	private byte[] signature;

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getPrev_hash() {
		return prev_hash;
	}

	public void setPrev_hash(String prev_hash) {
		this.prev_hash = prev_hash;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getPigCoins() {
		return pigCoins;
	}

	public void setPigCoins(int pigCoins) {
		this.pigCoins = pigCoins;
	}

	public PublicKey getpKey_sender() {
		return pKey_sender;
	}

	public void setpKey_sender(PublicKey pKey_sender) {
		this.pKey_sender = pKey_sender;
	}

	public PublicKey getpKey_recipient() {
		return pKey_recipient;
	}

	public void setpKey_recipient(PublicKey pKey_recipient) {
		this.pKey_recipient = pKey_recipient;
	}

	public byte[] getSignature() {
		return signature;
	}

	public void setSignature(byte[] signature) {
		this.signature = signature;
	}

	public Transaction(String hash, String prev_hash, int pigCoins, String message) {
		this.setHash(hash);
		this.setPrev_hash(prev_hash);
		this.setMessage(message);
		this.setPigCoins(pigCoins);

	}

	public String toString() {
		return "hash = " + this.getHash() + "\n" + "prev_has = " + this.getHash() + "\n" + "pKey_Sender = "
				+ this.getpKey_sender().hashCode() + "\n" + "pKey_Recipient = " + this.getpKey_recipient().hashCode()
				+ "\n" + "pigCoins = " + this.getPigCoins() + "\n" + "message = " + this.getMessage();
	}

}
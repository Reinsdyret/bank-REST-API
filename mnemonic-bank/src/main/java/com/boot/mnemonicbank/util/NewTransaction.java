package com.boot.mnemonicbank.util;

public class NewTransaction {

	private double cashAmount;

	private long sourceAccountID;

	private long destinationAccountID;

	public NewTransaction(double cashAmount, long sourceAccountID, long destinationAccountID) {
		this.cashAmount = cashAmount;
		this.sourceAccountID = sourceAccountID;
		this.destinationAccountID = destinationAccountID;
	}

	public double getCashAmount() {
		return cashAmount;
	}

	public long getSourceAccountID() {
		return sourceAccountID;
	}

	public long getDestinationAccountID() {
		return destinationAccountID;
	}
}

package com.boot.mnemonicbank.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "transaction")
public class Transaction {

	@Id
	private long id;

	@Column(nullable = false)
	private long registeredTime;

	@Column(nullable = false)
	private long executedTime;

	@Column(nullable = false)
	private boolean success;

	@Column(nullable = false)
	private double cashAmount;

	private long sourceAccountID;


	private long destinationAccountID;


	public Transaction(long registeredTime, long executedTime, boolean success, double cashAmount, long sourceAccountID, long destinationAccountID) {
		this.registeredTime = registeredTime;
		this.executedTime = executedTime;
		this.success = success;
		this.cashAmount = cashAmount;
		this.sourceAccountID = sourceAccountID;
		this.destinationAccountID = destinationAccountID;
	}

	public long getRegisteredTime() {
		return registeredTime;
	}

	public long getExecutedTime() {
		return executedTime;
	}

	public boolean isSuccess() {
		return success;
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

package com.boot.mnemonicbank;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "transaction")
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	public long getId() {
		return id;
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

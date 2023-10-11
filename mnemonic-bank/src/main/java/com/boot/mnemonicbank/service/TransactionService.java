package com.boot.mnemonicbank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boot.mnemonicbank.entity.Account;
import com.boot.mnemonicbank.entity.Transaction;
import com.boot.mnemonicbank.repository.AccountRepository;
import com.boot.mnemonicbank.repository.TransactionRepository;
import com.boot.mnemonicbank.util.CustomResponseEntity;
import com.boot.mnemonicbank.util.NewTransaction;

@Service
public class TransactionService {

	private final AccountRepository accountRepository;
	private final TransactionRepository transactionRepository;

	public TransactionService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
		super();
		this.accountRepository = accountRepository;
		this.transactionRepository = transactionRepository;
	}


	public List<Transaction> getAllTransactions(){
		return transactionRepository.findAll();
	}

	public void deleteTransaction(Long id){
		transactionRepository.deleteById(id); // If fails then it fails silent
	}

	@Transactional
	public ResponseEntity<?> createTransaction(NewTransaction transaction) {
		long registeredTime = System.currentTimeMillis();
		// Get accounts by id.
		long fromAccountID = transaction.getSourceAccountID();
		long toAccountID = transaction.getDestinationAccountID();
		Optional<Account> fromAccountOptional = accountRepository.findById(fromAccountID);
		Optional<Account> toAccountOptional = accountRepository.findById(toAccountID);
		Account fromAccount, toAccount;

		// Check that accounts exist and throw errors if not
		if(!fromAccountOptional.isPresent()) return CustomResponseEntity.accountNotFound(fromAccountID);
		else { // Update account if it exists
			fromAccount = fromAccountOptional.get();
		}

		if(!toAccountOptional.isPresent()) return CustomResponseEntity.accountNotFound(toAccountID);
		else { // Update account if it exists
			toAccount = toAccountOptional.get();
		}

		double amount = transaction.getCashAmount();

		// Check that amount is not more than fromAccount's balance
		if (amount > fromAccount.getAvailableCash()) {
			// Throw exception with code 400 and correct message
			return CustomResponseEntity.insufficientFunds(fromAccountID);
		}

		// Withdraw amount from accont dont save yet as something can go wrong with deposit
		fromAccount.withdraw(amount);

		// Deposit amount to account
		toAccount.deposit(amount);

		long executedTime = saveUpdatedUsers(fromAccount, toAccount);

		// WHY HAVE SUCCESS? WHEN WE CAN JUST STOP USER FROM EXECUTING NOT VALID TRANSACTIONS
		boolean success = true;

		Transaction newTransaction = new Transaction(registeredTime, executedTime, success, amount, fromAccountID, toAccountID);

		return saveTransaction(newTransaction);
	}

	@Transactional
	private long saveUpdatedUsers(Account fromAccount, Account toAccount){
		/**
		 * Saves the given users in the accountrepository
		 * @Return long Current epoch time.
		 */
		accountRepository.saveAll(List.of(fromAccount, toAccount));
		return System.currentTimeMillis();
	}

	@Transactional
	private ResponseEntity<?> saveTransaction(Transaction transaction){

		Transaction newTransaction = transactionRepository.save(transaction);
		return ResponseEntity.ok(newTransaction);
	}

}

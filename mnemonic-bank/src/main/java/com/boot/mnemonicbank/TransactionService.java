package com.boot.mnemonicbank;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class TransactionService {
	
	private final TransactionRepository transactionRepository;

	public TransactionService(TransactionRepository transactionRepository) {
		super();
		this.transactionRepository = transactionRepository;
	}
	
	public ResponseEntity<Transaction> saveTransaction(@RequestBody Transaction transaction){
		Transaction newTransaction = transactionRepository.save(transaction);
		return ResponseEntity.ok(newTransaction);
	}
	
	public ResponseEntity<List<Transaction>> getAllTransactions(){
		return ResponseEntity.ok(transactionRepository.findAll());
	}
	
	public ResponseEntity<String> deleteTransaction(Long id){
		transactionRepository.deleteById(id); // If fails then it fails silent
		return ResponseEntity.ok("Transaction deleted successfully");
	}
	
}

package com.boot.bank.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.bank.entity.Transaction;
import com.boot.bank.service.TransactionService;
import com.boot.bank.util.NewTransaction;

@RestController
@RequestMapping("/api/v1")
public class TransactionController {

	private final TransactionService transactionService;

	public TransactionController(TransactionService transactionService) {
		super();
		this.transactionService = transactionService;
	}


	@PutMapping("/transaction")
	public ResponseEntity<?> saveTransaction(@RequestBody NewTransaction transaction){

		// Return saved transaction
		return transactionService.createTransaction(transaction);
	}

	@GetMapping("/transactions")
	public List<Transaction> getAllTransactions(){
		return transactionService.getAllTransactions();
	}
}

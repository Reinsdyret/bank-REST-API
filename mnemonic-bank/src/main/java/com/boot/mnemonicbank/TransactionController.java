package com.boot.mnemonicbank;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class TransactionController {
	
	private final AccountRepository accountRepository;
	private final TransactionService transactionService;
	
	public TransactionController(AccountRepository accountRepository, TransactionService transactionService) {
		super();
		this.accountRepository = accountRepository;
		this.transactionService = transactionService;
	}
	
	@PutMapping("/transaction")
	public ResponseEntity saveTransaction(@RequestBody Transaction transaction){
		// Update account balances.
		long fromAccountID = transaction.getSourceAccountID();
		long toAccountID = transaction.getDestinationAccountID();
		Optional<Account> fromAccount = accountRepository.findById(fromAccountID);
		Optional<Account> toAccount = accountRepository.findById(toAccountID);
		
		double amount = transaction.getCashAmount();
		
		// Check that amount is not more than fromAccount's balance
		if (amount > fromAccount.getAvailableCash()) {
			// Return failed response with code 400
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Account to transfer from does not have enough funds");
		}
		
		
		
		// Return saved transaction
		return transactionService.saveTransaction(transaction);
	}
}

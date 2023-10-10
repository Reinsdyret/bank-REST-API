package com.boot.mnemonicbank;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AccountController {

	private final AccountService accountService;
	//private final TransactionService transactionService;
	
	public AccountController(AccountService accountService, TransactionService transactionService) {
		this.accountService = accountService;
		//this.transactionService = transactionService;
	}
	
	@PutMapping("/account")
	public ResponseEntity<Account> saveAccount(@RequestBody Account account){
		return accountService.saveAccount(account);
	}
	
	@GetMapping("/accounts")
	public ResponseEntity<List<Account>> getAllAccounts(){
		return accountService.getAllAccounts();
	}
	
	@GetMapping("/account/{id}")
	public ResponseEntity<Account> getAccountById(@PathVariable Long id){
		return accountService.getAccountByID(id); 
	}
}

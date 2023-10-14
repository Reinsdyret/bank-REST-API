package com.boot.bank.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.bank.entity.Account;
import com.boot.bank.service.AccountService;
import com.boot.bank.service.TransactionService;

@RestController
@RequestMapping("/api/v1")
public class AccountController {

	private final AccountService accountService;
	//private final TransactionService transactionService;

	public AccountController(AccountService accountService, TransactionService transactionService) {
		this.accountService = accountService;
		//this.transactionService = transactionService;
	}

	@PostMapping("/account")
	public Account saveAccount(@RequestBody Account account){
		return accountService.saveAccount(account);
	}

	@PutMapping("/account/{id}")
	public ResponseEntity<?> updateAccount(@RequestBody Account account, @PathVariable Long id){
		return accountService.updateAccount(id, account);
	}

	@GetMapping("/accounts")
	public List<Account> getAllAccounts(){
		return accountService.getAllAccounts();
	}

	@GetMapping("/account/{id}")
	public ResponseEntity<?> getAccountById(@PathVariable Long id){
		return accountService.getAccountByID(id);
	}

	@DeleteMapping("/delete/account/{id}")
	public void deleteAccountById(@PathVariable Long id) {
		accountService.deleteAccount(id);
	}
}

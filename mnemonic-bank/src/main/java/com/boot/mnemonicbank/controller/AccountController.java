package com.boot.mnemonicbank.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.mnemonicbank.entity.Account;
import com.boot.mnemonicbank.service.AccountService;
import com.boot.mnemonicbank.service.TransactionService;

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
	public Account saveAccount(@RequestBody Account account){
		return accountService.saveAccount(account);
	}

	@PutMapping("/account/update")
	public ResponseEntity<?> updateAccount(@RequestBody Account account){
		return accountService.updateAccount(account.getId(), account);
	}

	@GetMapping("/accounts")
	public List<Account> getAllAccounts(){
		return accountService.getAllAccounts();
	}

	@GetMapping("/account/{id}")
	public ResponseEntity<?> getAccountById(@PathVariable Long id){
		return accountService.getAccountByID(id);
	}

	@PutMapping("/delete/account/{id}")
	public void deleteAccountById(@PathVariable Long id) {
		accountService.deleteAccount(id);
	}
}

package com.boot.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.boot.bank.entity.Account;
import com.boot.bank.repository.AccountRepository;
import com.boot.bank.util.CustomResponseEntity;

@Service
public class AccountService {
	private final AccountRepository accountRepository;

	public AccountService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	public Account saveAccount(@RequestBody Account account){
		return accountRepository.save(account);
	}

	public List<Account> getAllAccounts(){
		return accountRepository.findAll();
	}

	public ResponseEntity<?> getAccountByID(Long id){
		Optional<Account> account = accountRepository.findById(id);
		if(account.isPresent())
			return ResponseEntity.ok(account.get());
		else
			return CustomResponseEntity.accountNotFound(id);
	}

	@Transactional
	public ResponseEntity<?> updateAccount(Long id, Account updatedAccount){
		if (id == null) return ResponseEntity.notFound().build();

		Optional<Account> existingAccountOpt = accountRepository.findById(id);
		Account existingAccount;

		if(!existingAccountOpt.isPresent()) {
			return CustomResponseEntity.accountNotFound(id);
		} else {
			existingAccount = existingAccountOpt.get();
		}

		// Update fields
		existingAccount.setName(updatedAccount.getName());
		existingAccount.setAvailableCash(updatedAccount.getAvailableCash());

		Account savedAccount = accountRepository.save(existingAccount);

		return ResponseEntity.ok(savedAccount);
	}

	public void deleteAccount(Long id){
		accountRepository.deleteById(id);
	}
}

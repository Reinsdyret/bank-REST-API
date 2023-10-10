package com.boot.mnemonicbank;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AccountService {
	private final AccountRepository accountRepository;
	
	public AccountService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
	
	public ResponseEntity<Account> saveAccount(@RequestBody Account account){
		Account newAccount = accountRepository.save(account);
		return ResponseEntity.ok(newAccount);
	}
	
	public ResponseEntity<List<Account>> getAllAccounts(){
		return ResponseEntity.ok(accountRepository.findAll());
	}
	
	public ResponseEntity<Account> getAccountByID(Long id){
		Optional<Account> account = accountRepository.findById(id);
		if(account.isPresent())
			return ResponseEntity.ok(account.get());
		else
			return ResponseEntity.notFound().build();
	}
	
	
	public ResponseEntity<Account> updateAccount(Long id, Account updatedAccount){
		if (id == null) return ResponseEntity.notFound().build();
		
		Account existingAccount = accountRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundException(String.valueOf(id)));
		
		// Update fields
		existingAccount.setName(updatedAccount.getName());
		existingAccount.setAvailableCash(updatedAccount.getAvailableCash());
		
		Account savedAccount = accountRepository.save(existingAccount);
		
		return ResponseEntity.ok(savedAccount);
	}
	
	public ResponseEntity<String> deleteAccount(Long id){
		accountRepository.deleteById(id);
		return ResponseEntity.ok("Account deleted successfully");
	}
}

package com.boot.mnemonicbank.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CustomResponseEntity{

	public static ResponseEntity<?> accountNotFound(long accountID) {
		return new ResponseEntity<>(new ApplicationException("Account with id " + String.valueOf(accountID) + " does not exist", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
	}


	public static ResponseEntity<?> insufficientFunds(long accountID) {
		return new ResponseEntity<>(new ApplicationException("Account " + String.valueOf(accountID) + " does not have enough funds", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
	}
}
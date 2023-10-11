package com.boot.mnemonicbank.util;

import org.springframework.http.HttpStatus;

public class ApplicationException{

    private final String message;
    private final HttpStatus httpStatus;
	
    public ApplicationException(String message, HttpStatus httpStatus) {
		super();
		this.message = message;
		this.httpStatus = httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
    
    
}

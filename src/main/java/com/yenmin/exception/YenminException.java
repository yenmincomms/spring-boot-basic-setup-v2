package com.yenmin.exception;

import org.springframework.http.HttpStatus;

public class YenminException extends Exception {

	private static final long serialVersionUID = -8876930018642615734L;

	private HttpStatus errorCode;

	public YenminException(String message) {
		super(message);
	}

	public YenminException(String message, HttpStatus errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public HttpStatus getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(HttpStatus errorCode) {
		this.errorCode = errorCode;
	}
}
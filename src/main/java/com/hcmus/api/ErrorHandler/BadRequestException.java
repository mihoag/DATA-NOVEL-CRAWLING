package com.hcmus.api.ErrorHandler;

public class BadRequestException extends Exception {
	public BadRequestException(String message) {
		super(message);
	}
}

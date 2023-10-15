package com.qsp.springstudent.exception;

public class PhoneNotFound extends RuntimeException
{
	private String message;

	public PhoneNotFound(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
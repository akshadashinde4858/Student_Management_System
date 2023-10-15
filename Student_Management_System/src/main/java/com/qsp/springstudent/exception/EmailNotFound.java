package com.qsp.springstudent.exception;

public class EmailNotFound extends RuntimeException
{
	private String message;
	
	public EmailNotFound(String message) 
	{
		super();
		this.message=message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}

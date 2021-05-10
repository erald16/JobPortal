package com.ikubinfo.primefaces.service.exceptions;

public class UsernameIsTakenException extends Exception {
	
	private static final long serialVersionUID = -7615177798654148960L;

	public UsernameIsTakenException(String message) {
		super(message);
	}
}

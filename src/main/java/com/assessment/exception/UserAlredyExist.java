package com.assessment.exception;

public class UserAlredyExist extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserAlredyExist(String message) {
		super(message);
	}

}

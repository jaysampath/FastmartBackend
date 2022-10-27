package com.services.fastmart.exception;

public class CartActionException extends RuntimeException {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CartActionException(String message) {
		super(message);
	}
}

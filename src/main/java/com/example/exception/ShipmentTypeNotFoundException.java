package com.example.exception;

public class ShipmentTypeNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ShipmentTypeNotFoundException(String msg) {
		super(msg);
	}
}

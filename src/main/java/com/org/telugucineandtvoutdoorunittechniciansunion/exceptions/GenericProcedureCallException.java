package com.org.telugucineandtvoutdoorunittechniciansunion.exceptions;

public class GenericProcedureCallException extends Throwable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7038571531928511162L;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	public GenericProcedureCallException(String message){
		this.message=message;
	}
	

}

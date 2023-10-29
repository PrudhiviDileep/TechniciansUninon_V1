package com.org.telugucineandtvoutdoorunittechniciansunion.exceptions;

public class NotValidCardNumberException extends RuntimeException {
	private String exceptinmessage = " Card Number Should contain Numerics Only! ";

	private String cardNo;

	public NotValidCardNumberException() {
	}

	public NotValidCardNumberException(String message) {
		this.exceptinmessage = message;
	}

	public NotValidCardNumberException(String message, String cardNo) {
		super(message);
		this.cardNo = cardNo;
	}

	public NotValidCardNumberException(String message, String cardNo, Throwable cause) {
		super(message, cause);
		this.cardNo = cardNo;
	}

	public String toString() {
		return super.toString();
	}

	public String getMessage() {
		return this.exceptinmessage;
	}

	public String getAmmount() {
		return this.cardNo;
	}
}

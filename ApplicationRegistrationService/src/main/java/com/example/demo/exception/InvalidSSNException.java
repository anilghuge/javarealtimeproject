package com.example.demo.exception;

public class InvalidSSNException extends Exception {
	private static final long serialVersionUID = -6654108227423972387L;

	public InvalidSSNException() {
		super();
	}

	public InvalidSSNException(String msg) {
		super(msg);
	}
}

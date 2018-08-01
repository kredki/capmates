package com.capgemini.jstk.capmates.exceptions;

public class NoSuchIndexException extends RuntimeException {

	private static final long serialVersionUID = -5098384666844518308L;

	public NoSuchIndexException() {
	}

	public NoSuchIndexException(String message) {
		super(message);
	}

	public NoSuchIndexException(Throwable cause) {
		super(cause);
	}

	public NoSuchIndexException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoSuchIndexException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

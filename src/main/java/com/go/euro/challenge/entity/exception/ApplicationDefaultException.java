package com.go.euro.challenge.entity.exception;

/**
 * Created by tymoshenkol on 10-Nov-16.
 */
//TODO: there might exist different SpecialSituationException/s
public class ApplicationDefaultException extends RuntimeException {
	private static final String DEFAULT_BAD_REQUEST_URI_MESSAGE = "Unsupported path. Read documentation.";
	private static final String DEFAULT_EXCEPTION_MESSAGE = "Application Default exception.";

	public ApplicationDefaultException () {
		this(DEFAULT_BAD_REQUEST_URI_MESSAGE);
	}

	public ApplicationDefaultException (Exception e) {
		this(e.getMessage(), e);
	}

	public ApplicationDefaultException (String message, Throwable e) {
		super(message, e);
	}

	public ApplicationDefaultException (String message) {
		this(message, null);
	}


}

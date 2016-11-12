package com.go.euro.challenge.entity.exception;

/**
 * Created by tymoshenkol on 11-Nov-16.
 */
public class AppInvalidParametersException extends ApplicationDefaultException {
	public AppInvalidParametersException () {
		super("Bad request params.");
	}
}

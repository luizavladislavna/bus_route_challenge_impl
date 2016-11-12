package com.go.euro.challenge.entity.exception;

/**
 * Created by tymoshenkol on 11-Nov-16.
 */
public class AppUnexpectedSizeStationsException extends ApplicationDefaultException {
	public AppUnexpectedSizeStationsException(Integer maxValue, Integer currentValue) {
		super(String.format("There is incorrect number N of routes. Expected is %s, and there is %s", maxValue, currentValue));
	}
}

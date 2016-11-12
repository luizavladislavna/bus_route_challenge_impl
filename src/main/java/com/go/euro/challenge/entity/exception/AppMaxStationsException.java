package com.go.euro.challenge.entity.exception;

/**
 * Created by tymoshenkol on 11-Nov-16.
 */
public class AppMaxStationsException extends ApplicationDefaultException {
	public AppMaxStationsException (Integer maxValue, Integer currentValue) {
		super(String.format("There is incorrect number N of stations. Maximum is %s, and there is %s", maxValue, currentValue));
	}
}

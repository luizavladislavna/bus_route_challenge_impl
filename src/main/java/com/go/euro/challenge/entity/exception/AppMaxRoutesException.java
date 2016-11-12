package com.go.euro.challenge.entity.exception;

/**
 * Created by tymoshenkol on 11-Nov-16.
 */
public class AppMaxRoutesException  extends ApplicationDefaultException {
	public AppMaxRoutesException (Integer maxValue, Integer currentValue) {
		super(String.format("There is incorrect number N of bus routes. Maximum is %s, and there is %s", maxValue, currentValue));
	}
}

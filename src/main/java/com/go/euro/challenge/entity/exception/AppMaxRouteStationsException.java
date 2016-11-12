package com.go.euro.challenge.entity.exception;

/**
 * Created by tymoshenkol on 11-Nov-16.
 */
public class AppMaxRouteStationsException extends ApplicationDefaultException {
	public AppMaxRouteStationsException (Integer maxValue, Integer currentValue) {
		super(String.format("There is incorrect number N of route stations. Maximum is %s, and there is %s", maxValue, currentValue));
	}
}

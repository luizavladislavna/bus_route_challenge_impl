package com.go.euro.challenge.entity.exception;

import java.util.Arrays;

/**
 * Created by tymoshenkol on 11-Nov-16.
 */
public class AppUnknownStationsException extends ApplicationDefaultException {
	public AppUnknownStationsException (Integer ... ids) {
		super(String.format("There is unknown stations: %s", Arrays.toString(ids)));
	}
}

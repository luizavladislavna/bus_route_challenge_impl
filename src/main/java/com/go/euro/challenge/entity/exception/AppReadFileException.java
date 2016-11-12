package com.go.euro.challenge.entity.exception;

import java.io.IOException;

/**
 * Created by tymoshenkol on 10-Nov-16.
 */
//TODO: there might exist different SpecialSituationException/s
public class AppReadFileException extends ApplicationDefaultException {
	public AppReadFileException (String filePath, IOException e) {
		super(String.format("Error while reading data from file: %s", filePath), e);
	}
}

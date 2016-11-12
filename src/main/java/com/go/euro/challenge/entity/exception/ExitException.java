package com.go.euro.challenge.entity.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ExitCodeGenerator;

/**
 * Created by tymoshenkol on 10-Nov-16.
 */
@Slf4j
public class ExitException extends RuntimeException implements ExitCodeGenerator {

	public ExitException (String message) {
		super(message);
		log.error("EXIT: {}", message);
	}

	@Override
	public int getExitCode () {
		return 10;
	}

}

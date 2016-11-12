package com.go.euro.challenge.controller;

import com.go.euro.challenge.entity.response.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by tymoshenkol on 10-Nov-16.
 */
@ControllerAdvice
@Slf4j
public class ExceptionController {

	//TODO: there might be a lot of ExceptionHandlers

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ExceptionResponse handleAllException (Exception ex) {
		return new ExceptionResponse(ex);
	}
}

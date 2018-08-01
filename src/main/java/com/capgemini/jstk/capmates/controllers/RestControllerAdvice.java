package com.capgemini.jstk.capmates.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.capgemini.jstk.capmates.exceptions.NoSuchIndexException;

@ControllerAdvice
public class RestControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ ArithmeticException.class })
	public ResponseEntity<Object> handleArithemticException(NoSuchIndexException ex, HttpServletRequest request) {
		return ResponseEntity.badRequest().body("Processing error: " + request.getRequestURI());
	}
}

package com.assessment.exception;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.RollbackException;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handelValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));;
		return errors;
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(RollbackException.class)
	public Map<String, String> handelRollBackExceptions(RollbackException ex) {
		Map<String, String> errors = new HashMap<>();
		
//		ex.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));;
		errors.put("error", ex.getMessage());
		return errors;
	}
	
//	ConstraintViolationException
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	public Map<String, String> handelConstraintViolationException(ConstraintViolationException ex) {
		Map<String, String> errors = new HashMap<>();
		errors.put("error", ex.getMessage());
		return errors;
	}

}

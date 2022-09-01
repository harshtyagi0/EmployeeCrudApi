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
	String error = "error";

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handelValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors()
				.forEach(e -> errors.put(e.getField(), e.getDefaultMessage()));
		return errors;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(RollbackException.class)
	public Map<String, String> handelRollBackExceptions(RollbackException ex) {
		Map<String, String> errors = new HashMap<>();
		errors.put(error, ex.getMessage());
		return errors;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	public Map<String, String> handelConstraintViolationException(ConstraintViolationException ex) {
		Map<String, String> errors = new HashMap<>();
		errors.put(error, ex.getMessage());
		return errors;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(UserAlredyExist.class)
	public Map<String, String> handelUserAlredyExist(UserAlredyExist ex) {
		Map<String, String> errors = new HashMap<>();
		errors.put(error, ex.getMessage());
		return errors;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(UserNotExist.class)
	public Map<String, String> handelUserNotExist(UserNotExist ex) {
		Map<String, String> errors = new HashMap<>();
		errors.put(error, ex.getMessage());
		return errors;
	}

}

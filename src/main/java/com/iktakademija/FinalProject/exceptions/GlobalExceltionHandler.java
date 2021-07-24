package com.iktakademija.FinalProject.exceptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iktakademija.FinalProject.controllers.utils.RESTError;
import com.iktakademija.FinalProject.controllers.utils.enums.ERESTErrorCodes;

@ResponseBody
@ControllerAdvice
public class GlobalExceltionHandler {// extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<?> handleAccessDeniedException(HttpServletRequest req, DataIntegrityViolationException e) {
		return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.ALREADY_EXISTS), HttpStatus.NOT_ACCEPTABLE);
	}
    
//	// Global exception interceptor
//	@ExceptionHandler(value = { Error.class })
//	public void handleConflict(Error e) {
//		logger.error("=============================================");
//		logger.error("Unhandled exception that need your attention:");
//		e.printStackTrace();
//		logger.error("=============================================");
//	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {

		// Initialize map
		Map<String, List<String>> errors = new HashMap<>();

		for (ObjectError objectError : ex.getBindingResult().getAllErrors()) {
			FieldError err = (FieldError) objectError;
			if (errors.containsKey(err.getField())) {
				errors.get(err.getField()).add(objectError.getDefaultMessage());
			} else {
				ArrayList<String> list = new ArrayList<>();
				list.add(objectError.getDefaultMessage());
				errors.put(err.getField(), list);
			}
		}
		return new ResponseEntity<Map<String, List<String>>>(errors, HttpStatus.BAD_REQUEST);
	}

}

package com.iktakademija.FinalProject.exceptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.iktakademija.FinalProject.controllers.utils.RESTError;
import com.iktakademija.FinalProject.controllers.utils.enums.ERESTErrorCodes;
import com.iktakademija.FinalProject.services.LoggingService;

@ResponseBody
@ControllerAdvice
public class GlobalExceltionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private LoggingService loggingService;
	
	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<?> handleAccessDeniedException(HttpServletRequest req, DataIntegrityViolationException e) {

		loggingService.loggMessageWithoutHeader(" >>> ERROR: DataIntegrityViolationException", Level.ERROR);	
		loggingService.loggMessage(ERESTErrorCodes.CONSTRAINT_INVALID.toString(), Level.ERROR);
		loggingService.loggMessageWithoutHeader(" <<< -------------", Level.ERROR);	
		return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.CONSTRAINT_INVALID), HttpStatus.BAD_REQUEST);
	}
    
	// Global exception interceptor
	@ExceptionHandler(value = { Error.class })
	public void handleConflict(Error e) {
		logger.error("=============================================");
		logger.error("Unhandled exception that need your attention:");
		e.printStackTrace();
		logger.error("=============================================");
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		loggingService.loggMessageWithoutHeader(" >>> ERROR Handler", Level.ERROR);	
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
				loggingService.loggMessage(err.getField() +" : "+objectError.getDefaultMessage(), Level.ERROR);
			}
		}		
		loggingService.loggMessageWithoutHeader(" <<< -------------", Level.ERROR);	
		return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
	}

}

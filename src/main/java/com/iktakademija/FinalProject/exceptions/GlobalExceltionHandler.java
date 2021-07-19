package com.iktakademija.FinalProject.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.iktakademija.FinalProject.controllers.utils.RESTError;
import com.iktakademija.FinalProject.controllers.utils.enums.ERESTErrorCodes;


@ResponseBody
@ControllerAdvice
public class GlobalExceltionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<?> handleAccessDeniedException(HttpServletRequest req, DataIntegrityViolationException e) {		
		return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.ALREADY_EXISTS), HttpStatus.NOT_ACCEPTABLE);
	}
	    
//	@ExceptionHandler({ Exception.class })
//	public ResponseEntity<?> handleAll(Exception ex, WebRequest request) {
//		return new ResponseEntity<String>("error occurred", HttpStatus.BANDWIDTH_LIMIT_EXCEEDED);
//	}

}

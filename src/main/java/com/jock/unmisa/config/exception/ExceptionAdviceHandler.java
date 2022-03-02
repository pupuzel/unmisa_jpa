package com.jock.unmisa.config.exception;

import javax.validation.metadata.ConstraintDescriptor;

import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jock.unmisa.config.validate.SeverityGroup;
import com.jock.unmisa.utils.ResultMap;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice("com.jock.unmisa.controller")
@Slf4j
public class ExceptionAdviceHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResultMap> modelValid(MethodArgumentNotValidException e) {

		BindingResult bindingResult = e.getBindingResult();
		ObjectError erros = bindingResult.getAllErrors().get(0);
		
		ConstraintViolationImpl constraintViolation = erros.unwrap(ConstraintViolationImpl.class);
		ConstraintDescriptor descriptor = constraintViolation.getConstraintDescriptor(); 
		if(!descriptor.getPayload().isEmpty()) {
			var payload = descriptor.getPayload().stream().findFirst().get();
			
			if(payload == SeverityGroup.Error.class) {
				return new ResponseEntity<ResultMap>(HttpStatus.BAD_REQUEST);
			}
		}
		
		String message = erros.getDefaultMessage();
		ResultMap result = new ResultMap("N", message);
		return new ResponseEntity<ResultMap>(result, HttpStatus.OK);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResultMap> exception(Exception e) {
		log.info("ExceptionAdviceHandler ------->", e);
		
		return new ResponseEntity<ResultMap>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

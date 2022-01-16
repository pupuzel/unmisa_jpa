package com.jock.unmisa.config.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jock.unmisa.utils.ResultMap;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice("com.jock.unmisa.controller")
@Slf4j
public class ExceptionAdviceHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResultMap> modelValid(MethodArgumentNotValidException e) {
		BindingResult bindingResult = e.getBindingResult();
		List<ObjectError> erros = bindingResult.getAllErrors(); 
		
		String message = erros.get(0).getDefaultMessage();

		return new ResponseEntity<ResultMap>(HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResultMap> exception(Exception e) {
		log.info("ExceptionAdviceHandler ------->", e);
		ResultMap result = new ResultMap("N");
		result.put("error", "server error");
		
		return new ResponseEntity<ResultMap>(result, HttpStatus.OK);
	}
}

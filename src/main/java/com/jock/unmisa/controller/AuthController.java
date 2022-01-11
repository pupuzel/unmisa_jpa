package com.jock.unmisa.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jock.unmisa.service.AuthService;
import com.jock.unmisa.utils.ResultMap;
import com.jock.unmisa.vo.AuthVO;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@RestController
public class AuthController {
	
	private AuthService authService;

	@PostMapping("/authenticate/login")
	public ResponseEntity<ResultMap> login(@RequestBody @Valid AuthVO authVo, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		authService.login(authVo, response);
		
		return new ResponseEntity<ResultMap>(HttpStatus.OK);
	}
	
}

package com.jock.unmisa.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jock.unmisa.config.validate.AuthValidationGroup;
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

	/**
	 * ����� �α��� ó��
	 * @return ResponseEntity<ResultMap>
	 */
	@PostMapping("/api/authenticate/login")
	public ResponseEntity<ResultMap> login(@RequestBody @Validated(AuthValidationGroup.loginGroup.class) AuthVO authVo, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		var resultMap = authService.login(authVo, request, response);
		
		return new ResponseEntity<ResultMap>(resultMap, HttpStatus.OK);
	}
	
	/**
	 * ����� �α׾ƿ�
	 * @return ResponseEntity<ResultMap>
	 */
	@PostMapping("/api/authenticate/logout")
	public ResponseEntity<ResultMap> logout(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		authService.logout(request, response);
		
		return new ResponseEntity<ResultMap>(HttpStatus.OK);
	}
	
	/**
	 * ����� ȸ������ �� �α��� ó��
	 * @return ResponseEntity<ResultMap>
	 */
	@PostMapping("/api/authenticate/join")
	public ResponseEntity<ResultMap> Join(@RequestBody @Validated(AuthValidationGroup.JoinGroup.class) AuthVO authVo, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		var resultMap = authService.Join(authVo, request, response);
		
		return new ResponseEntity<ResultMap>(resultMap, HttpStatus.OK);
	}
	
	/**
	 * ����� �г��� �ߺ� üũ
	 * @return ResponseEntity<ResultMap>
	 */
	@PostMapping("/api/authenticate/checkName")
	public ResponseEntity<ResultMap> checkName(@RequestBody AuthVO authVo, HttpServletRequest request) throws Exception{
		
		var resultMap = authService.checkName(authVo);
		
		return new ResponseEntity<ResultMap>(resultMap, HttpStatus.OK);
	}
	
}

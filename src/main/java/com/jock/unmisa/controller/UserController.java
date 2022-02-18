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
import com.jock.unmisa.service.UserService;
import com.jock.unmisa.utils.ResultMap;
import com.jock.unmisa.vo.AuthVO;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@RestController
public class UserController {
	
	private UserService userService;
	
	/**
	 * 사용자 정보 조회
	 * @return ResponseEntity<ResultMap>
	 */
	@PostMapping("/api/user/info")
	public ResponseEntity<ResultMap> lnfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		if(response.getStatus() != response.SC_OK) {
			return new ResponseEntity<ResultMap>(HttpStatus.OK);
		}else {
			return new ResponseEntity<ResultMap>(userService.info(request), HttpStatus.OK);
		}
		
	}

}

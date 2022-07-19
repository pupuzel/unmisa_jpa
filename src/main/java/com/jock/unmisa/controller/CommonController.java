package com.jock.unmisa.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jock.unmisa.service.CommonService;
import com.jock.unmisa.utils.ResultMap;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@RestController
public class CommonController {
	
	private CommonService commonService;
	
	@PostMapping("/api/common/selectCategoryList")
	public ResponseEntity<ResultMap> selectCategoryList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		return new ResponseEntity<ResultMap>(commonService.selectCategoryList(request), HttpStatus.OK);
	}
	
	
}

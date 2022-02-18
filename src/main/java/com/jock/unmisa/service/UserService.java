package com.jock.unmisa.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.jock.unmisa.cmmn.UserSessionHndlr;
import com.jock.unmisa.entity.user.User;
import com.jock.unmisa.utils.ResultMap;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
	
	private final UserSessionHndlr userSessionHndlr;

	public ResultMap info(HttpServletRequest request) throws Exception {
		var resultMap = new ResultMap();
		
		User user = userSessionHndlr.getUserSession(request);
		resultMap.put("data", user);
		
		return resultMap;
		
	}
}

package com.jock.unmisa.service;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.jock.unmisa.cmmn.UserSessionHndlr;
import com.jock.unmisa.dao.UserQueryRepository;
import com.jock.unmisa.entity.user.User;
import com.jock.unmisa.utils.ResultMap;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
	
	private final UserSessionHndlr userSessionHndlr;
	
	private final UserQueryRepository userDAO;

	/**
	 * 사용자 세션 조회
	 * @return ResultMap
	 */
	public ResultMap session(HttpServletRequest request) throws Exception {
		var resultMap = new ResultMap();
		
		User user = userSessionHndlr.getUserSession(request);
		resultMap.put("data", user);
		
		return resultMap;
		
	}
	
	/**
	 * 사용자 정보 조회
	 * @return ResultMap
	 */
	public ResultMap info(User vo) throws Exception {
		var resultMap = new ResultMap();
		var userNm = URLDecoder.decode(vo.getUser_nm(), "UTF-8");
		
		User user = userDAO.selectUserDetail(null, userNm);
		if(user == null) {
			resultMap.put("result", "N");
		}else {
			resultMap.put("data", user);
		}
		return resultMap;
		
	}
}

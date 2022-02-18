package com.jock.unmisa.cmmn;

import java.net.http.HttpClient;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jock.unmisa.dao.UserQueryRepository;
import com.jock.unmisa.entity.domain.OauthType;
import com.jock.unmisa.entity.user.User;
import com.jock.unmisa.utils.JwtTokenUtil;

import lombok.RequiredArgsConstructor;

@Component
public class UserSessionHndlr {
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	public boolean setUserSession(HttpServletRequest request, String token) {
		// ��ū�� ��ȿ���� �ʴٸ�	
		if(!jwtTokenUtil.isValidateToken(token)) { 
			return false; 
		}else {
			Map<String,Object> body = jwtTokenUtil.getBobyFromToken(token);
			
			request.setAttribute("id", body.get("id"));
			request.setAttribute("oauth_type", body.get("oauth_type"));
			request.setAttribute("user_nm", body.get("user_nm"));
			
			return true;
		}
		
	}
	
	public User getUserSession(HttpServletRequest request) throws Exception {
	
		if(request.getAttribute("id") == null) {
			throw new Exception("getUserSession null");
		}else {
			User user = new User();
			user.setId(String.valueOf(request.getAttribute("id")));
			user.setOauth_type(OauthType.valueOf( String.valueOf(request.getAttribute("oauth_type")) ));
			user.setUser_nm(String.valueOf(request.getAttribute("user_nm")));
			
			return user;
		}
	
	}

	
}
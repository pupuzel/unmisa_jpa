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
import com.jock.unmisa.entity.user.UserMeta;
import com.jock.unmisa.utils.JwtTokenUtil;

import lombok.RequiredArgsConstructor;

@Component
public class UserSessionHndlr {
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	public boolean setUserSession(HttpServletRequest request, String token) {
		// 토큰이 유효하지 않다면	
		if(!jwtTokenUtil.isValidateToken(token)) { 
			return false; 
		}else {
			Map<String,Object> body = jwtTokenUtil.getBobyFromToken(token);
			
			request.setAttribute("user_id", body.get("user_id"));
			request.setAttribute("oauth_type", body.get("oauth_type"));
			request.setAttribute("user_nm", body.get("user_nm"));
			request.setAttribute("user_profile_img", body.get("user_profile_img"));
			
			Map<String,String> userMeta = (Map<String, String>) body.get("user_meta");
			request.setAttribute("last_diary_ymd", userMeta.get("last_diary_ymd"));
			
			return true;
		}
		
	}
	
	public User getUserSession(HttpServletRequest request) throws Exception {
	
		if(request.getAttribute("user_id") == null) {
			throw new Exception("getUserSession null");
		}else {
			User user = new User();
			user.setUser_id(String.valueOf(request.getAttribute("user_id")));
			user.setOauth_type(OauthType.valueOf( String.valueOf(request.getAttribute("oauth_type")) ));
			user.setUser_nm(String.valueOf(request.getAttribute("user_nm")));
			user.setUser_profile_img(String.valueOf(request.getAttribute("user_profile_img")));
			
			UserMeta meta = new UserMeta();
			meta.setLast_diary_ymd(String.valueOf(request.getAttribute("last_diary_ymd")));
			
			user.setUser_meta(meta);
			
			return user;
		}
	
	}

	
}

package com.jock.unmisa.config.filter;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jock.unmisa.cmmn.UserSessionHndlr;
import com.jock.unmisa.service.AuthService;
import com.jock.unmisa.utils.JwtTokenUtil;
import com.jock.unmisa.utils.StringUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebFilter(urlPatterns = "/api/*")
public class AuthFilter extends OncePerRequestFilter{
	
	@Resource(name = "redisTemplate") 
	private ValueOperations<String, String> valueOperations;
	
	@Resource(name = "authService") 
	private AuthService authService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;	
	
	@Autowired
	private UserSessionHndlr userSessionHndlr;
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		if(request.getRequestURI().contains("/api/authenticate")) {
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		// 인증 쿠키값 가져오기
		String[] cookies = getCookieValue(request);
		String uuid = cookies[0];
		String token = cookies[1];
		
		/* ********* 토큰,세션 인증 ********* */
		 // 토큰 && uuid 가 없다면 (401 인증실패)
		if(StringUtil.isEmpty(token) && StringUtil.isEmpty(uuid)){
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); System.out.println("토큰 && uuid 가 없다면");
			
		// 토큰이 있다면	
		}else if(!StringUtil.isEmpty(token)) {
			
			// 토큰이 유효하지 않다면	
			if(!jwtTokenUtil.isValidateToken(token)) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);  System.out.println("토큰이 유효하지 않다면");
			}
			
			// 토큰이 만료 되었다면
			if(jwtTokenUtil.isTokenExpired(token)){
				
				// uuid가 없다면 (401 인증실패)
				if(StringUtil.isEmpty(uuid)) {
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); System.out.println("토큰이 만료 && uuid 없음");
					
				// uuid가 있다면 세션 조회 & 토큰 재발급
				}else{
					if(StringUtil.isEmpty(valueOperations.get(uuid))) {
						response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); System.out.println("토큰 만료 && uuid redis 비어있음");
					}else {
						token = authService.updateAuthenticationToken(uuid, response); System.out.println("토큰 만료 uuid로 재발급");
					}
				}
			}
			
		// 토큰이 없다면 세션 조회 재발급
		}else {
			if(StringUtil.isEmpty(valueOperations.get(uuid))) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); System.out.println("uuid redis 비어있음");
			}else {
				token = authService.updateAuthenticationToken(uuid, response); System.out.println("토큰 uuid로 재발급");
			}
		}
		
		/* ********* Set Session ********* */
		if(response.getStatus() == HttpServletResponse.SC_OK) {
			userSessionHndlr.setUserSession(request, token);
		}
		
		filterChain.doFilter(request, response);
	}
	

	private String[] getCookieValue(HttpServletRequest request) {
		String[] cookies = new String[2];
		
		 if(request.getCookies() != null ){
			for(Cookie cookie : request.getCookies()){
				if(cookie.getName().equals("u_uuid")){
					cookies[0] = cookie.getValue();
					continue;
				}else if(cookie.getName().equals("authorization")){
					cookies[1] = cookie.getValue();
					continue;
				}else if(cookies[0] != null && cookies[1] != null) {
					break;
				}
			}
		 }
		 
		 return cookies;
	}


}

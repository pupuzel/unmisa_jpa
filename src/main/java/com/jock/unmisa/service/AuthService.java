package com.jock.unmisa.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jock.unmisa.config.AuthProviderConfig;
import com.jock.unmisa.config.AuthProviderConfig.Provider;
import com.jock.unmisa.dao.UserQueryRepository;
import com.jock.unmisa.entity.domain.OauthType;
import com.jock.unmisa.entity.domain.UserGender;
import com.jock.unmisa.entity.domain.UserState;
import com.jock.unmisa.entity.user.User;
import com.jock.unmisa.entity.user.UserMeta;
import com.jock.unmisa.utils.ClientUtils;
import com.jock.unmisa.utils.DateUtils;
import com.jock.unmisa.utils.FileUtils;
import com.jock.unmisa.utils.JwtTokenUtil;
import com.jock.unmisa.utils.ResultMap;
import com.jock.unmisa.utils.StringUtil;
import com.jock.unmisa.vo.AuthVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthService {
	
	private final HttpClient httpClient;
	private final ObjectMapper mapper;
	private final JwtTokenUtil jwtTokenUtil;
	
	private final UserQueryRepository userDAO;
	
	@Resource(name = "redisTemplate") 
	private ValueOperations<String, String> valueOperations;
	
	@Value("${profile.img.path}")
	private String profileImgPath;
	
	@Value("${spring.session.redis.namespace}")
	private String sessionKey;
	
	@Value("${spring.session.timeout}")
    private int session_timeout;

	/**
	 * ����� �α���
	 * @return ResultMap
	 */
	public ResultMap login(AuthVO authVo, HttpServletRequest request, HttpServletResponse response) throws Exception{
		var resultMap = new ResultMap();
		
		// auth Ÿ�Ժ� provider ��������
		AuthProviderConfig authConfig = new AuthProviderConfig(authVo.getCode(), authVo.getAuth_type());
		Provider provider = authConfig.getProvider();
		
		// auth ����� ���� ��ȸ
		AuthVO authUser = getAuth(provider);
		
		// DB ����� ���� ��ȸ
		User user = userDAO.selectUser(authUser.getUser_id(), null);
		
		// ȸ������ page redirect
		if(user == null) {
			authUser.setUser_id(null);
			authUser.setAuto_login_yn(authVo.isAuto_login_yn());
			
			resultMap.put("data", authUser);
			
		// ���� ����	
		}else {
			User info = setSession(user, response, authVo.isAuto_login_yn());
			
			resultMap.put("data", info);
		}
		
		return resultMap;
	}
	
	
	/**
	 * ȸ������ �� �α��� ó��
	 * @return ResultMap
	 */
	public ResultMap Join(AuthVO authVo, HttpServletRequest request, HttpServletResponse response) throws Exception{
		var resultMap = new ResultMap();
		
		// User insert
		User user = insertUser(authVo, request);
		
		// set session
		User info = setSession(user, response, authVo.isAuto_login_yn());
		
		resultMap.put("data", info);
		return resultMap;
	}
	
	/**
	 * ����� �г��� �ߺ� üũ
	 * @return ResultMap
	 */
	public ResultMap checkName(AuthVO authVo) throws Exception{
		var resultMap = new ResultMap();
		
		User user = userDAO.selectUser(null, authVo.getUser_nm());
		if(user != null) {
			resultMap.put("result", "N");
		}
		
		return resultMap;
	}
	
	/**
	 * ����� �α׾ƿ�
	 * @return void
	 */
	public void logout(HttpServletRequest request, HttpServletResponse response) throws Exception{
		 String uuid = "";
		
		 // uuid ��Ű ã��
		 if(request.getCookies() != null ){
			for(Cookie cookie : request.getCookies()){
				if(cookie.getName().equals("u_uuid")){
					uuid = cookie.getValue();
					break;
				}
			}
		 }
		 
		 if(!StringUtil.isEmpty(uuid)) {
			 // redis �ʱ�ȭ
			 valueOperations.set(uuid, "", Duration.ofSeconds(1));
			 
			 //��Ű �ʱ�ȭ
			 ClientUtils.setSessionCookie(response, "u_uuid", "", 0);
			 ClientUtils.setSessionCookie(response, "authorization", "", 0);
		 }
		 
	}
	
	
	/**
	 * ��ū ����
	 * @return void
	 */
	public String updateAuthenticationToken(String uuid, HttpServletResponse response) {
		String refresh_token = null;
		
		try {
			String token = valueOperations.get(uuid);
			Map<String,Object> info = jwtTokenUtil.getBobyFromToken(token);
			
			refresh_token = jwtTokenUtil.generateToken(info);
			
			//set cookie
			ClientUtils.setSessionCookie(response, "authorization", refresh_token);
			
		}catch (Exception e) {
			log.info("Exception : ", e);
		}
		
		return refresh_token;
	}
	
	
	
	/* @@@@@@@@@@@@@@@@@@ private @@@@@@@@@@@@@@@@@@ */
	
	
	/**
	 * ���� �����
	 * @return User
	 */
	private User setSession(User user, HttpServletResponse response, boolean auto_login_yn) throws Exception{
		User u = new User();
		u.setUser_id(user.getUser_id());
		u.setOauth_type(user.getOauth_type());
		u.setUser_nm(user.getUser_nm());
		u.setUser_profile_img(user.getUser_profile_img());
		
		u.setUser_meta(new UserMeta());
		u.getUser_meta().setLast_diary_ymd(user.getUser_meta().getLast_diary_ymd());
		
		//jwt token ����
		String token = jwtTokenUtil.<User>generateToken(u);
		
		//redis session ����
		String uuid = UUID.randomUUID().toString();
		
		// �ڵ� �α����� ��� 30�� ����
		if(auto_login_yn) {
			valueOperations.set(uuid, token, Duration.ofSeconds(session_timeout * 30));
			ClientUtils.setSessionCookie(response, "u_uuid", uuid, session_timeout * 30);
			ClientUtils.setSessionCookie(response, "authorization", token);
		}else {
			valueOperations.set(uuid, token, Duration.ofSeconds(session_timeout));
			ClientUtils.setSessionCookie(response, "u_uuid", uuid);
			ClientUtils.setSessionCookie(response, "authorization", token);
		}
		
		
		return u;
	}
	
	/** 
	 * ����� create
	 * @return User
	 */
	private User insertUser(AuthVO authVo, HttpServletRequest request) throws Exception{
		
		String request_ip = ClientUtils.getRemoteIP(request);
		
		UserMeta meta = new UserMeta();
		meta.setRegister_ip(request_ip);
		meta.setLast_login_ip(request_ip);
		meta.setRegister_date(DateUtils.now());
		meta.setLast_login_date(DateUtils.now());
		meta.setUser_state(UserState.Ordinary);
		
		userDAO.insert(meta);
		
		User user = new User();
		user.setOauth_client_id(authVo.getClient_id());
		user.setUser_nm(authVo.getUser_nm());
		user.setUser_email(authVo.getUser_email());
		user.setEmail_yn(authVo.isEmail_yn());
		user.setUser_simple_intro(authVo.getUser_simple_intro());
		user.setUser_area(authVo.getUser_area());
		user.setUser_sns(authVo.getUser_sns());
		user.setUser_site(authVo.getUser_site());
		user.setUser_meta(meta);
		
		// oauth set
		OauthType oauthType = OauthType.valueOf(authVo.getAuth_type());
		String oauth_num = String.format("%04d", oauthType.ordinal());
		user.setOauth_type(oauthType);
		
		// userId set
		user.setUser_id(oauth_num+authVo.getClient_id());
		
		// gender set
		if(!StringUtil.isEmpty(authVo.getUser_gender())) {
			String gender = authVo.getUser_gender().toLowerCase();
			if(gender.equals("m") || gender.equals("male")) {
				user.setUser_gender(UserGender.M);
			}else {
				user.setUser_gender(UserGender.F);
			}
		}
		
		// age set
		if(!StringUtil.isEmpty(authVo.getUser_age_range())) {
			user.setUser_age_range(authVo.getUser_age_range());
		}
		
		// profile image set
		if(!StringUtil.isEmpty(authVo.getUser_profile_img())) {
			authVo.setUser_id(user.getUser_id());
			
			String imgPath = FileUtils.uploadProfileImg(authVo, profileImgPath);
			user.setUser_profile_img(imgPath);
		}
		
		// insert user
		userDAO.insert(user);
		
		return user;
	}
	
	
	/**
	 * auth ����� ���� ��ȸ
	 * @return void
	 */
	private AuthVO getAuth(Provider provider) throws Exception{
		
		var token = getTokenString(provider);
		
		var info = getUserInfoString(provider, token);
		
		// ����� ���� JSON ��ȯ
		JSONObject userInfo = new JSONObject(info);
		
		// auth type ���� ������ �ٸ��� �˸°� VO�� �־��ֱ� ����
		AuthVO authUser = new AuthVO();
		authUser.setAuth_type(provider.getAuth_type());
		
		if(provider.getAuth_type().equals("kakao")) {
			
			var kakao_account = userInfo.getJSONObject("kakao_account");
			var profile = kakao_account.getJSONObject("profile");
			
			authUser.setClient_id(userInfo.getString("id"));
			authUser.setUser_nm(profile.getString("nickname"));
			authUser.setUser_age_range(kakao_account.getString("age_range"));
			authUser.setUser_gender(kakao_account.getString("gender"));
			authUser.setUser_email(kakao_account.getString("email"));
			
		}else if(provider.getAuth_type().equals("naver")) {
			
			var response = userInfo.getJSONObject("response");
			authUser.setClient_id(response.getString("id"));
			//authUser.setNickname(response.getString("nickname"));
			//authUser.setAge(response.getString("age"));
			//authUser.setGender(response.getString("gender"));
			//authUser.setEmail(response.getString("email"));
			
		}else if(provider.getAuth_type().equals("google")) {
			
			authUser.setClient_id(userInfo.getString("id"));
			authUser.setUser_nm(userInfo.getString("name"));
			authUser.setUser_email(userInfo.getString("email"));
		}
		
		// user_id format
		OauthType type = OauthType.valueOf(provider.getAuth_type());
		String oauth_num = String.format("%04d", type.ordinal());
		authUser.setUser_id(oauth_num+authUser.getClient_id());
		
		return authUser;
	}
	
	/**
	 * auth ��ū ���� ��ȸ
	 * @return String
	 */
	private String getTokenString(Provider provider) throws Exception{
	   StringBuilder builder = new StringBuilder(provider.getTokenUri());
	   builder.append("?code=").append(provider.getCode())
			   .append("&grant_type=").append(provider.getGrant_type())
			   .append("&client_id=").append(provider.getClient_id())
			   .append("&client_secret=").append(provider.getClient_secret())
			   .append("&redirect_uri=").append(provider.getRedirect_uri());

		HttpRequest request = HttpRequest.newBuilder()
			      .uri(URI.create(builder.toString()))
			      .timeout(Duration.ofSeconds(5))
			      .POST(BodyPublishers.ofString(""))
			      .build();
		
		var response = httpClient.send(request, BodyHandlers.ofString());
		
		var authVo = mapper.readValue(response.body(), AuthVO.class);
		return authVo.getAccess_token();
	}
	
	/**
	 * �߱޹��� ��ū���� ��������� ��ȸ
	 * @return String
	 */
	private String getUserInfoString(Provider provider, String token) throws Exception{
		   StringBuilder builder = new StringBuilder(provider.getUserInfoUri());

			HttpRequest request = HttpRequest.newBuilder()
				      .uri(URI.create(builder.toString()))
				      .timeout(Duration.ofSeconds(5))
				      .header("Authorization", "Bearer "+token)
				      .GET()
				      .build();
			
			var response = httpClient.send(request, BodyHandlers.ofString());
			
			return response.body();
		}
}

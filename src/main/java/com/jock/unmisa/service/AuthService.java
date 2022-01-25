package com.jock.unmisa.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jock.unmisa.config.AuthProviderConfig;
import com.jock.unmisa.config.AuthProviderConfig.Provider;
import com.jock.unmisa.dao.UserQueryRepository;
import com.jock.unmisa.entity.domain.OauthType;
import com.jock.unmisa.entity.domain.UserGender;
import com.jock.unmisa.entity.user.User;
import com.jock.unmisa.utils.ResultMap;
import com.jock.unmisa.utils.StringUtil;
import com.jock.unmisa.vo.AuthVO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AuthService {
	
	private HttpClient httpClient;
	private ObjectMapper mapper;
	
	private UserQueryRepository userDAO;

	/**
	 * ����� �α���
	 * @return ResultMap
	 */
	public ResultMap login(AuthVO authVo, HttpServletRequest request, HttpServletResponse response) throws Exception{
		var resultMap = new ResultMap("Y");
		
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
			resultMap.put("data", authUser);
			
		// ���� ����	
		}else {
			
			resultMap.put("data", user);
		}
		
		return resultMap;
	}
	
	
	/**
	 * ȸ������ �� �α��� ó��
	 * @return ResultMap
	 */
	public ResultMap Join(AuthVO authVo, HttpServletRequest request, HttpServletResponse response) throws Exception{
		var resultMap = new ResultMap("Y");
		
		// User ����
		User user = createUser(authVo);

		resultMap.put("data", user);
		return resultMap;
	}
	
	
	
	
	
	
	
	/* @@@@@@@@@@@@@@@@@@ private @@@@@@@@@@@@@@@@@@ */
	/** 
	 * ����� create
	 * @return User
	 */
	private User createUser(AuthVO authVo) throws Exception{
		
		User user = new User();
		user.setOauth_client_id(authVo.getClient_id());
		user.setUser_nm(authVo.getUser_nm());
		user.setUser_email(authVo.getUser_email());
		user.setEmail_yn(authVo.isEmail_yn());
		user.setUser_simple_intro(authVo.getUser_simple_intro());
		user.setUser_area(authVo.getUser_area());
		user.setUser_sns(authVo.getUser_sns());
		user.setUser_site(authVo.getUser_site());
		
		// oauth format
		OauthType oauthType = OauthType.valueOf(authVo.getAuth_type());
		String oauth_num = String.format("%04d", oauthType.ordinal());
		user.setOauth_type(oauthType);
		
		// userId format
		user.setId(oauth_num+"-"+authVo.getClient_id());
		
		// gender format
		if(!StringUtil.isEmpty(authVo.getUser_gender())) {
			String gender = authVo.getUser_gender().toLowerCase();
			if(gender.equals("m") || gender.equals("male")) {
				user.setUser_gender(UserGender.M);
			}else {
				user.setUser_gender(UserGender.F);
			}
		}
		
		// age format
		if(!StringUtil.isEmpty(authVo.getUser_age_range())) {
			user.setUser_age_range(authVo.getUser_age_range());
		}

		// insert user
		userDAO.insertUser(user);
		
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
		authUser.setUser_id(oauth_num+"-"+authUser.getClient_id());
		
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

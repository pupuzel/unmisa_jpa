package com.jock.unmisa.entity;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jock.unmisa.config.AuthProviderConfig;
import com.jock.unmisa.config.AuthProviderConfig.Provider;
import com.jock.unmisa.utils.ResultMap;
import com.jock.unmisa.vo.AuthVO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AuthService {
	
	private HttpClient httpClient;
	
	private ObjectMapper mapper;

	/**
	 * 사용자 로그인
	 * @return ResultMap
	 */
	public ResultMap login(AuthVO authVo, HttpServletResponse response) throws Exception{
		
		// auth 타입별 provider 가져오기
		AuthProviderConfig authConfig = new AuthProviderConfig(authVo.getCode(), authVo.getAuth_type());
		Provider provider = authConfig.getProvider();
		
		// auth 사용자 정보 조회
		AuthVO authUser = getAuth(provider);
		
		
		return new ResultMap("Y");
	}
	
	
	
	
	
	
	
	
	
	/* @@@@@@@@@@@@@@@@@@ private service module @@@@@@@@@@@@@@@@@@ */
	
	/**
	 * auth 사용자 정보 조회
	 * @return void
	 */
	private AuthVO getAuth(Provider provider) throws Exception{
		
		var token = getTokenString(provider);
		
		var info = getUserInfoString(provider, token);
		
		// 사용자 정보 JSON 변환
		JSONObject userInfo = new JSONObject(info);
		
		// auth type 별로 포맷이 다르니 알맞게 VO에 넣어주기 ㄱㄱ
		AuthVO authUser = new AuthVO();
		if(provider.getAuth_type().equals("kakao")) {
			
			var kakao_account = userInfo.getJSONObject("kakao_account");
			var profile = kakao_account.getJSONObject("profile");
			
			authUser.setId(userInfo.getString("id"));
			authUser.setNickname(profile.getString("nickname"));
			authUser.setAge(kakao_account.getString("age_range"));
			authUser.setGender(kakao_account.getString("gender"));
			authUser.setEmail(kakao_account.getString("email"));
			
		}else if(provider.getAuth_type().equals("naver")) {
			
			var response = userInfo.getJSONObject("response");
			authUser.setId(response.getString("id"));
			//authUser.setNickname(response.getString("nickname"));
			//authUser.setAge(response.getString("age"));
			//authUser.setGender(response.getString("gender"));
			//authUser.setEmail(response.getString("email"));
			
		}else if(provider.getAuth_type().equals("google")) {
			
			authUser.setId(userInfo.getString("id"));
			authUser.setNickname(userInfo.getString("name"));
			authUser.setEmail(userInfo.getString("email"));
		}
		
		return authUser;
	}
	
	/**
	 * auth 토큰 정보 조회
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
	 * 발급받은 토큰으로 사용자정보 조회
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

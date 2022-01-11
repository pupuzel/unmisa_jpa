package com.jock.unmisa.config;

public class AuthProviderConfig {
	
	private Provider provider;
	
	private AuthProviderConfig() {
		
	}
	
	public AuthProviderConfig(String code, String auth_type) throws Exception{
		provider = new Provider();
		provider.auth_type = auth_type;
		provider.grant_type = "authorization_code";
		provider.redirect_uri = "http://127.0.0.1:3000/auth/callback/"+auth_type;
		
		if(auth_type.equals("kakao")) {
			setKakao(code);
		}else if(auth_type.equals("naver")) {
			setNaver(code);
		}else if(auth_type.equals("google")) {
			setGoogle(code);
		}else{
			throw new Exception("No exist auth type");
		}
	}
	
	public Provider getProvider() {
		return provider;
	}
	
	private void setKakao(String code) {
		provider.code = code;
		provider.client_id = "865a4e3f0990f09ca5780259fd6d8687";
		provider.client_secret = "0A2Pmhslz1SQBEmkqMgyfgBIJITlhGs0";
		provider.tokenUri = "https://kauth.kakao.com/oauth/token";
		provider.tokenHostUri = "kauth.kakao.com";
		provider.tokenPathUri = "/oauth/token";
		provider.userInfoUri = "https://kapi.kakao.com/v2/user/me";
		provider.userInfoHostUri = "kapi.kakao.com";
		provider.userInfoPathUri = "/v2/user/me";
	}
	
	private void setNaver(String code) {
		provider.code = code;
		provider.client_id = "0dChYllU1ncvhUyN3E8R";
		provider.client_secret = "1YSu8roSc8";
		provider.tokenUri = "https://nid.naver.com/oauth2.0/token";
		provider.tokenHostUri = "nid.naver.com";
		provider.tokenPathUri = "/oauth2.0/token";
		provider.userInfoUri = "https://openapi.naver.com/v1/nid/me";
		provider.userInfoHostUri = "openapi.naver.com";
		provider.userInfoPathUri = "/v1/nid/me";
	}
	
	private void setGoogle(String code) {
		provider.code = code;
		provider.client_id = "465273390522-0baktehtbn76l5fm67bjolv79bkoek4i.apps.googleusercontent.com";
		provider.client_secret = "dY8ZAAXO09VBT7hAxgBCxgSa";
		provider.tokenUri = "https://oauth2.googleapis.com/token";
		provider.tokenHostUri = "oauth2.googleapis.com";
		provider.tokenPathUri = "/token";
		provider.userInfoUri = "https://www.googleapis.com/oauth2/v2/userinfo";
		provider.userInfoHostUri = "www.googleapis.com";
		provider.userInfoPathUri = "/oauth2/v2/userinfo";
	}
	
	public class Provider{
		private String code;
		private String auth_type;
		private String grant_type;
		private String client_id;
		private String client_secret;
		private String redirect_uri;
		private String tokenUri;
		private String tokenHostUri;
		private String tokenPathUri;
		private String userInfoUri;
		private String userInfoHostUri;
		private String userInfoPathUri;
		
		public String getCode() {
			return code;
		}
		public String getAuth_type() {
			return auth_type;
		}
		public String getGrant_type() {
			return grant_type;
		}
		public String getClient_id() {
			return client_id;
		}
		public String getClient_secret() {
			return client_secret;
		}
		public String getRedirect_uri() {
			return redirect_uri;
		}
		public String getTokenHostUri() {
			return tokenHostUri;
		}
		public String getTokenPathUri() {
			return tokenPathUri;
		}
		public String getUserInfoHostUri() {
			return userInfoHostUri;
		}
		public String getUserInfoPathUri() {
			return userInfoPathUri;
		}
		public String getTokenUri() {
			return tokenUri;
		}
		public String getUserInfoUri() {
			return userInfoUri;
		}
		
	}
}

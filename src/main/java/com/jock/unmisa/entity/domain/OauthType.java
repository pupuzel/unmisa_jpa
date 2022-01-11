package com.jock.unmisa.entity.domain;

public enum OauthType {
	kakao("카카오"),
	naver("네이버"),
	google("구글");
	
	private final String value;
	
	OauthType(String value){
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}

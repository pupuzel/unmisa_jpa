package com.jock.unmisa.entity.domain;

public enum OauthType {
	kakao("īī��"),
	naver("���̹�"),
	google("����");
	
	private final String value;
	
	OauthType(String value){
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}

package com.jock.unmisa.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthVO {
	private String id;
	private String auth_type;
	private String code;
	private String access_token;
	private String nickname;
	private String email;
	private String profile_image;
	private String age;
	private String gender;
	private String error;
}

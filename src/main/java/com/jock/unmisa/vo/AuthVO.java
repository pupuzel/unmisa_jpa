package com.jock.unmisa.vo;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.implementation.bind.annotation.Empty;

@Getter
@Setter
public class AuthVO {
	private String id;
	
	@NotEmpty
	private String auth_type;
	
	@NotEmpty
	private String code;
	
	private String access_token;
	private String nickname;
	private String email;
	private String profile_image;
	private String age;
	private String gender;
	private String error;
}

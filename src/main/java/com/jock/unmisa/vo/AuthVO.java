package com.jock.unmisa.vo;

import javax.validation.constraints.NotEmpty;

import com.jock.unmisa.entity.domain.OauthType;

import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.implementation.bind.annotation.Empty;

@Getter
@Setter
public class AuthVO {
	
	private String user_id;
	
	private String client_id;
	
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
	
	public String getUser_id() {
		OauthType type = OauthType.valueOf(auth_type);
		String oauth_num = String.format("%04d", type.ordinal());
		
		return oauth_num+"-"+client_id;
	}
}

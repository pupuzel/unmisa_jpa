package com.jock.unmisa.vo;

import javax.validation.constraints.NotEmpty;

import com.jock.unmisa.config.validate.AuthValidationGroup;
import com.jock.unmisa.config.validate.SeverityGroup;
import com.jock.unmisa.entity.domain.OauthType;

import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.implementation.bind.annotation.Empty;

@Getter
@Setter
public class AuthVO {
	
	private String client_id;
	
	private boolean auto_login_yn;
	
	@NotEmpty(groups = AuthValidationGroup.loginGroup.class, payload = SeverityGroup.Error.class)
	private String auth_type;
	
	@NotEmpty(groups = AuthValidationGroup.loginGroup.class, payload = SeverityGroup.Error.class)
	private String code;
	
	private String access_token;
	
	private String error;
	
	private String user_id;
	private String user_nm;
	private String user_email;
	private String user_profile_img;
	private String user_profile_img_type;
	private String user_age_range;
	private String user_birth;
	private String user_sns;
	private String user_site;
	private String user_gender;
	private String user_simple_intro;
	private String user_area;
	private boolean email_yn = true;
}

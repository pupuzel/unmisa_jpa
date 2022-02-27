package com.jock.unmisa.entity.user;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.jock.unmisa.entity.domain.OauthType;
import com.jock.unmisa.entity.domain.UserGender;

import lombok.Data;


@Entity
@Table(name = "T_USER")
@Data
public class User {
	
	@Id
	@Column
	private String user_id;

	@Column(length = 50)
	private String oauth_client_id;

	@Enumerated(EnumType.ORDINAL)
	private OauthType oauth_type;
	
	private String user_email;
	
	@Column(length = 100, unique = true)
	private String user_nm;
	
	@Column(length = 8)
	private String user_birth;
	
	@Column(length = 10)
	private String user_age_range;
	
	@Enumerated(EnumType.ORDINAL)
	private UserGender user_gender;
	
	private boolean email_yn;
	
	private String user_sns;
	
	@Column(columnDefinition = "TEXT")
	private String user_site;
	
	@Column(length = 100)
	private String user_simple_intro;
	
	@Column(columnDefinition = "TEXT")
	private String user_intro;
	
	@Column(length = 100)
	private String user_profile_img;
	
	@Column(length = 100)
	private String user_area;
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
	private UserMeta user_meta;
}

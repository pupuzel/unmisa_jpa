package com.jock.unmisa.entity.user;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import com.jock.unmisa.entity.domain.OauthType;

import lombok.Data;


@Entity
@Table(name = "T_USER")
@Data
public class User {
	
	@Id
	@Column(name="USER_ID")
	private String id;
	
	@Column(length = 50)
	private String oauth_client_id;

	@Enumerated(EnumType.ORDINAL)
	private OauthType oauth_type;
	
	private String user_email;
	
	@Column(length = 100)
	private String user_nm;
	
	@Column(length = 8)
	private String user_birth;
	
	private boolean user_gender;
	
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
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private UserMeta user_meta;
}

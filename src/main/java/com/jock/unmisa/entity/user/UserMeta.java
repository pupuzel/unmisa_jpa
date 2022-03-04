package com.jock.unmisa.entity.user;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.jock.unmisa.entity.common.CommonDateEntity;
import com.jock.unmisa.entity.domain.UserState;

import lombok.Data;

@Entity
@Table(name = "T_USER_META")
@Data
public class UserMeta extends CommonDateEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int user_meta_id;
	
	@Column(length = 50)
	private String register_ip;
	
	@Column(length = 50)
	private String last_login_ip;
	
	@Enumerated
	private UserState user_state;
	
	@Column(length = 8)
	private String last_diary_ymd;
	
	@OneToOne(mappedBy = "user_meta")
    private User user;
}

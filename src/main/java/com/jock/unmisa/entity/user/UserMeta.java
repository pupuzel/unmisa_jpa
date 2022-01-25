package com.jock.unmisa.entity.user;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.jock.unmisa.entity.domain.UserState;

import lombok.Data;

@Entity
@Table(name = "T_USER_META")
@Data
public class UserMeta {

	@Id
	@Column(name="USER_ID")
	private String id;
	
	@Column(length = 50)
	private String register_ip;
	
	private LocalDateTime register_date;
	
	@Enumerated
	private UserState user_state;
	
	@Column(length = 50)
	private String last_login_ip;
	
	private LocalDateTime  last_login_date;
	
	@Column(length = 8)
	private String last_diary_ymd;
	
	@OneToOne
    @MapsId
    @JoinColumn(name = "USER_ID")
    private User user;
}

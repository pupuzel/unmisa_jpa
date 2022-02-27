package com.jock.unmisa.entity.diary;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.jock.unmisa.entity.user.User;

import lombok.Data;

@Entity
@Table(name = "T_DIARY_CMT")
@Data
public class DiaryCmt {
	
	@Id
	@GeneratedValue
	private int cmt_id;
	
	@ManyToOne
	@JoinColumn(name = "DIARY_ID")
	private Diary diary;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;
	
	
	private String cmt_content;
	
	
	private int cmt_depth;
	
	
	private int bundle_cmt_id;
	
	
	private String link_user_id;
	
	
	private int bundle_cmt_cnt;
	
	
	private LocalDateTime cre_date;
	
	
	private LocalDateTime upd_date;
}

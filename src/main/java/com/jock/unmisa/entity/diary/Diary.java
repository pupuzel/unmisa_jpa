package com.jock.unmisa.entity.diary;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.jock.unmisa.entity.domain.DiaryDay;
import com.jock.unmisa.entity.user.User;

import lombok.Data;

@Entity
@Table(name = "T_DIARY")
@Data
public class Diary {

	@Id
	@GeneratedValue
	private int diary_id;
	
    @ManyToOne
    @JoinColumn(name ="USER_ID")
	private User user;
	
	private String diary_content;
	
	@Column(length = 8)
	private String diary_ymd;
	
	@Enumerated(EnumType.ORDINAL)
	private DiaryDay diary_day;
	
	@Column(length = 50)
	private String diary_place;
	
	
	private int diary_cmt_cnt;
	
	
	private int diary_like_cnt;
	
	
	private LocalDateTime cre_date;
	
	
	private LocalDateTime upd_date;
}

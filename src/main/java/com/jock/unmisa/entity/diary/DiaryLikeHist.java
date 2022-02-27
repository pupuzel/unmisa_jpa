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
@Table(name = "T_DIARY_LIKE_HIST")
@Data
public class DiaryLikeHist {

	@Id
	@GeneratedValue
	private int like_id;

	@ManyToOne
	@JoinColumn(name = "DIARY_ID")
	private Diary diary;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;
	
	private boolean like_yn;
	
	private LocalDateTime cre_date;
	
	private LocalDateTime upd_date;
}

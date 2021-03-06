package com.jock.unmisa.entity.diary;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.jock.unmisa.entity.common.CommonDateEntity;
import com.jock.unmisa.entity.user.User;

import lombok.Data;

@Entity
@Table(name = "T_DIARY_LIKE_HIST")
@Data
public class DiaryLikeHist extends CommonDateEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer like_id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DIARY_ID")
	private Diary diary;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	private User user;
	
	private boolean like_yn;
	
}

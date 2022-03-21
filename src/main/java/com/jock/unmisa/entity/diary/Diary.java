package com.jock.unmisa.entity.diary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.jock.unmisa.config.validate.DiaryValidationGroup;
import com.jock.unmisa.entity.common.CommonDateEntity;
import com.jock.unmisa.entity.domain.DiaryDay;
import com.jock.unmisa.entity.user.User;

import lombok.Data;

@Entity
@Table(name = "T_DIARY")
@Data
public class Diary extends CommonDateEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer diary_id;
	
	@JsonProperty(access = Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="USER_ID")
	private User user;
	
    @NotEmpty(groups = DiaryValidationGroup.createGroup.class, message = "일기 내용을 입력해주세요")
	private String diary_content;
	
    @NotEmpty(groups = DiaryValidationGroup.createGroup.class, message = "날짜가 비어있습니다")
	@Column(length = 8)
	private String diary_ymd;
	
    @NotNull(groups = DiaryValidationGroup.createGroup.class, message = "요일이 비어있습니다")
	@Enumerated(EnumType.ORDINAL)
	private DiaryDay diary_day;
	
	@Column(length = 50)
	private String diary_place;
	
	
	private int diary_cmt_cnt;
	
	
	private int diary_like_cnt;
	
	/* ************************ DTO ************************ */
	@Transient
	private boolean like_yn;
	
	@Transient
	private String user_nm;
	
	@Transient
	private String user_profile_img;
	
	
}

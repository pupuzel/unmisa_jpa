package com.jock.unmisa.entity.diary;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.jock.unmisa.config.validate.DiaryValidationGroup;
import com.jock.unmisa.entity.common.CommonDateEntity;
import com.jock.unmisa.entity.user.User;

import lombok.Data;

@Entity
@Table(name = "T_DIARY_CMT")
@Data
public class DiaryCmt extends CommonDateEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cmt_id;
	
	@ManyToOne
	@JoinColumn(name = "DIARY_ID")
	private Diary diary;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;
	
	@NotEmpty(groups = DiaryValidationGroup.createCommentGroup.class, message = "댓글 내용을 입력해주세요")
	private String cmt_content;
	
	@NotNull(groups = DiaryValidationGroup.createCommentGroup.class, message = "null depth")
	private Integer cmt_depth;
	
	
	private int bundle_cmt_id;
	
	
	private String link_user_id;
	
	
	private int bundle_cmt_cnt;
	

}

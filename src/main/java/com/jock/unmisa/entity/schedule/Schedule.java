package com.jock.unmisa.entity.schedule;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.jock.unmisa.entity.category.Category;
import com.jock.unmisa.entity.diary.Diary;
import com.jock.unmisa.entity.diary.DiaryCmt;
import com.jock.unmisa.entity.user.User;

import lombok.Data;

@Entity
@Table(name = "T_SCHEDULE")
@Data
public class Schedule {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer schedule_id;
	
	@JsonProperty(access = Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="USER_ID")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "CATEGORY_ID")
	private Category category;
	
	private String schedule_title;
	
	private String schedule_comment;
	
	private boolean open_yn;
	
	private boolean merge_yn;
	
	private String target_day1;
	
	private String target_day2;
	
	private String target_day3;
	
	private String target_day4;
	
	private String target_day5;
	
	private String target_day6;
	
	private String target_day7;
	
	private String workout_day1;
	
	private String workout_day2;
	
	private String workout_day3;
	
	private String workout_day4;
	
	private String workout_day5;
	
	private String workout_day6;
	
	private String workout_day7;
	
	private Integer merge_schedule1;
	
	private Integer merge_schedule2;
	
	private Integer merge_schedule3;
	
	private Integer merge_schedule4;
	
	private Integer merge_schedule5;

}

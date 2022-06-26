package com.jock.unmisa.entity.category;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jock.unmisa.entity.diary.Diary;
import com.jock.unmisa.entity.diary.DiaryCmt;
import com.jock.unmisa.entity.user.User;

import lombok.Data;

@Entity
@Table(name = "T_CATEGORY")
@Data
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer category_id;
	
	@Column(length = 50)
	private String category_nm;
	
	private int category_order;
	
	private boolean category_use_yn;
}

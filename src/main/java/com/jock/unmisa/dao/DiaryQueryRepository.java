package com.jock.unmisa.dao;

import static com.jock.unmisa.entity.diary.QDiary.diary;
import static com.jock.unmisa.entity.diary.QDiaryLikeHist.diaryLikeHist;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jock.unmisa.cmmn.QuerydslRepositoryCustom;
import com.jock.unmisa.entity.diary.Diary;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class DiaryQueryRepository extends QuerydslRepositoryCustom{
    
    DiaryQueryRepository(JPAQueryFactory queryFactory){
    	super(diary, queryFactory);
    }
	
    
    public List<Diary> selectDiaryList(String user_id, Integer diary_id, String search_user_id){
    	if(search_user_id == null) {
    		return queryFactory
    				.select(Projections.bean(
    						Diary.class
    						,diary.diary_id
    						,diary.diary_content
    						,diary.diary_ymd
    						,diary.diary_day
    						,diary.diary_place
    						,diary.diary_cmt_cnt
    						,diary.diary_like_cnt
    				))
    				.where( eq(diary.user.user_id, user_id)
    						 ,lt(diary.diary_id, diary_id)
    						)
    				.orderBy(diary.diary_id.desc())
    				.limit(5)
    				.fetch();
    	}else {
    		return queryFactory
    				.select(Projections.bean(
    						Diary.class
    						,diary.diary_id
    						,diary.diary_content
    						,diary.diary_ymd
    						,diary.diary_day
    						,diary.diary_place
    						,diary.diary_cmt_cnt
    						,diary.diary_like_cnt
    						,diaryLikeHist.like_yn
    				))
    				.from(diary)
    				.where( eq(diary.user.user_id, user_id)
    						 ,lt(diary.diary_id, diary_id)
    						)
    				.leftJoin(diaryLikeHist)
    					 .on( diary.diary_id.eq(diaryLikeHist.diary.diary_id) 
    						   ,eq(diaryLikeHist.user.user_id, search_user_id))
    				.orderBy(diary.diary_id.desc())
    				.limit(5)
    				.fetch();
    	}

    }
    
}

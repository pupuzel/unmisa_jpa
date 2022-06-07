package com.jock.unmisa.dao;

import static com.jock.unmisa.entity.diary.QDiary.diary;
import static com.jock.unmisa.entity.diary.QDiaryCmt.diaryCmt;
import static com.jock.unmisa.entity.diary.QDiaryLikeHist.diaryLikeHist;
import static com.jock.unmisa.entity.user.QUser.user;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jock.unmisa.cmmn.QuerydslRepositoryCustom;
import com.jock.unmisa.entity.diary.Diary;
import com.jock.unmisa.entity.diary.DiaryCmt;
import com.jock.unmisa.entity.diary.DiaryLikeHist;
import com.jock.unmisa.entity.user.QUser;
import com.querydsl.core.types.Projections;
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
    				.selectFrom(diary)
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
    						,diary.user.user_id
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
    
    
    public Diary selectDiary(Integer diary_id, String search_user_id) {
    	
    	if(search_user_id == null) {
        	return queryFactory
    				.selectFrom(diary)
    				.where( eq(diary.diary_id, diary_id) )
    				.fetchOne();
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
    						,diary.user.user_nm
    						,diary.user.user_profile_img
    				))
    				.from(diary)
    				.innerJoin(diary.user, user)
    				.leftJoin(diaryLikeHist)
    					 .on( diary.diary_id.eq(diaryLikeHist.diary.diary_id) 
    						   ,eq(diaryLikeHist.user.user_id, search_user_id))
					.where( eq(diary.diary_id, diary_id)	) 
    				.fetchOne();
    	}
    	
    }
    
    public DiaryLikeHist selectDiaryLikeHist(Integer diary_id, String user_id) {
    	return queryFactory
    				.selectFrom(diaryLikeHist)
    				.where( eq(diaryLikeHist.diary.diary_id, diary_id)
    						 ,eq(diaryLikeHist.user.user_id, user_id))
    				.fetchOne();
    }
    
    
    public void updateDiaryLikeCnt(Integer diary_id, boolean like_yn) {
    	queryFactory
    		.update(diary)
    		.set(diary.diary_like_cnt, diary.diary_like_cnt.add(like_yn ? 1 : -1))
    		.where( eq(diary.diary_id, diary_id))
    		.execute();
    		
    }
    
    public void updateDiaryCmtCnt(Integer diary_id, int cnt) {
    	queryFactory
    		.update(diary)
    		.set(diary.diary_cmt_cnt, diary.diary_cmt_cnt.add(cnt))
    		.where( eq(diary.diary_id, diary_id))
    		.execute();
    		
    }
    
    public List<DiaryCmt> selectDiaryCmtList(Integer diary_id){
    	QUser likeUser = new QUser("likeUser");
    	
		return queryFactory
				.select(Projections.bean(
						DiaryCmt.class
					   ,diaryCmt.cmt_id
					   ,diaryCmt.bundle_cmt_id
					   ,diaryCmt.cmt_content
					   ,diaryCmt.cmt_depth
					   ,diaryCmt.user.user_nm
					   ,likeUser.user_nm.as("like_user_nm")
					   ,diaryCmt.cre_date
				))
				.from(diaryCmt)
				.where( eq(diaryCmt.diary.diary_id, diary_id)
						)
				.innerJoin(diaryCmt.user, user)
				.leftJoin(likeUser)
					 .on( diaryCmt.link_user_id.eq(likeUser.user_id))
				.orderBy(diaryCmt.bundle_cmt_id.asc(), diaryCmt.cmt_id.asc())
				//.limit(5)
				.fetch();
    }
}

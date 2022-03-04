package com.jock.unmisa.dao;

import static com.jock.unmisa.entity.diary.QDiary.diary;
import static com.jock.unmisa.entity.user.QUser.user;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jock.unmisa.config.QuerydslRepositoryCustom;
import com.jock.unmisa.entity.diary.Diary;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class DiaryQueryRepository extends QuerydslRepositoryCustom{
    
    DiaryQueryRepository(JPAQueryFactory queryFactory){
    	super(diary, queryFactory);
    }
	
    
    public List<Diary> selectDiary(String user_id, int diary_id){
		return queryFactory
				.selectFrom(diary)
				.where( eq(user_id, "user_id")
						 ,diary.diary_id.gt(diary_id)
						)
				.orderBy(diary.diary_id.desc())
				.limit(5)
				.fetch();
    }
    

}

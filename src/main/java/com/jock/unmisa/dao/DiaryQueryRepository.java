package com.jock.unmisa.dao;

import static com.jock.unmisa.entity.diary.QDiary.diary;

import org.springframework.stereotype.Repository;

import com.jock.unmisa.config.QuerydslRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class DiaryQueryRepository extends QuerydslRepositoryCustom{
    
    DiaryQueryRepository(JPAQueryFactory queryFactory){
    	super(diary, queryFactory);
    }
	
    

}

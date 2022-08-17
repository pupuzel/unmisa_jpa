package com.jock.unmisa.dao;

import static com.jock.unmisa.entity.category.QCategory.category;
import static com.jock.unmisa.entity.schedule.QSchedule.schedule;
import static com.jock.unmisa.entity.diary.QDiary.diary;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jock.unmisa.cmmn.QuerydslRepositoryCustom;
import com.jock.unmisa.entity.category.Category;
import com.jock.unmisa.entity.diary.Diary;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class ScheduleQueryRepository extends QuerydslRepositoryCustom{
    
    ScheduleQueryRepository(JPAQueryFactory queryFactory){
    	super(schedule, queryFactory);
    }
    
    public List<Category> selectCategoryList(){
    		return queryFactory
    				.selectFrom(category)
    				.where( eq(category.category_use_yn, true)
    						)
    				.orderBy(category.category_order.asc())
    				.fetch();
    }
    
}

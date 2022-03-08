package com.jock.unmisa.cmmn;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.jock.unmisa.utils.StringUtil;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.extern.slf4j.Slf4j;
/* * 다양한 검색 조건 ex
member.username.eq("member1") // username = 'member1'   
member.username.ne("member1") //username != 'member1'  
member.username.eq("member1").not() // username != 'member1'
member.username.isNotNull() //이름이 is not null   
member.age.in(10, 20) // age in (10,20)  
member.age.notIn(10, 20) // age not in (10, 20)   
member.age.between(10,30) //between 10, 30
member.age.goe(30) // age >= 30 
member.age.gt(30) // age > 30   
member.age.loe(30) // age <= 30 
member.age.lt(30) // age < 30
member.username.like("member%") //like 검색 
member.username.contains("member") // like ‘%member%’ 검색 
member.username.startsWith("member") //like ‘member%’ 검색
 * */
@Slf4j
public class QuerydslRepositoryCustom extends QuerydslRepositorySupport{

	private final Object entity;
	
	protected final JPAQueryFactory queryFactory;
	
	public QuerydslRepositoryCustom(Object entity, JPAQueryFactory queryFactory) {
		super(entity.getClass());
		
		this.entity = entity;
		this.queryFactory = queryFactory;
	}

/*
	 protected BooleanExpression eq(Object value, String name) {
		 try {
			 	if(value == null) return null;
			 	
			 	if(value.getClass().getTypeName().equals("java.lang.String")) {
			 		
			 		if(StringUtil.isEmpty(String.valueOf(value))) {
			 			return null;
			 		}
			 		
			 	}
			 	
		    	Field field = entity.getClass().getDeclaredField(StringUtil.trim(name));
		    	
		    	Object fieldValue = field.get(entity);
		    	Method method = fieldValue.getClass().getMethod("eq", Object.class);
		    	BooleanExpression eq = (BooleanExpression) method.invoke(fieldValue, value);
		    	return eq ;
		 }catch (Exception e) {
			return null;
		}

    }
*/
	
	
	 protected BooleanExpression eq(Object field, Object value) {
		 try {
			 	if(value == null) return null;
			 	
			 	if(value.getClass().getTypeName().equals("java.lang.String")) {
			 		
			 		if(StringUtil.isEmpty(String.valueOf(value))) {
			 			return null;
			 		}
			 		
			 	}
			 	
		    	Method method = field.getClass().getMethod("eq", Object.class);
		    	BooleanExpression eq = (BooleanExpression) method.invoke(field, value);
		    	return eq ;
		 }catch (Exception e) {
			 log.info("Exception : ", e);
			return null;
		}

    }
	 
	 
	 protected BooleanExpression lt(Object field, Number value) {
		 try {
			 	if(value == null) return null;
			 	
		    	Method method = field.getClass().getMethod("lt", Number.class);
		    	BooleanExpression gt = (BooleanExpression) method.invoke(field, value);
		    	return gt ;
		 }catch (Exception e) {
			 log.info("Exception : ", e);
			return null;
		}

    }
	 
	 public void insert(Object o) {
		getEntityManager().persist(o);
	 }
	 

}

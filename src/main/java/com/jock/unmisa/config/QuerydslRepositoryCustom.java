package com.jock.unmisa.config;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.jock.unmisa.utils.StringUtil;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class QuerydslRepositoryCustom extends QuerydslRepositorySupport{

	private final Object entity;
	
	protected final JPAQueryFactory queryFactory;
	
	public QuerydslRepositoryCustom(Object entity, JPAQueryFactory queryFactory) {
		super(entity.getClass());
		
		this.entity = entity;
		this.queryFactory = queryFactory;
	}
	
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
	 
	 public void insert(Object o) {
		getEntityManager().persist(o);
	 }
	 

}

package com.jock.unmisa.dao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.jock.unmisa.config.QuerydslRepositoryCustom;
import com.jock.unmisa.entity.user.User;
import com.jock.unmisa.entity.user.UserMeta;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.jock.unmisa.entity.user.QUser.user;

@Slf4j
@Repository
public class UserQueryRepository extends QuerydslRepositoryCustom{
    
    UserQueryRepository(JPAQueryFactory queryFactory){
    	super(user, queryFactory);
    }
	 
	 /**
	 * 사용자 조회
	 * @return User
	 */
	public User selectUser(String user_id, String user_nm) {
		return queryFactory
					.selectFrom(user)
					.where( eq(user_id, "id")
							  ,eq(user_nm, "user_nm")
							)
					.fetchOne();
	 }
		

}

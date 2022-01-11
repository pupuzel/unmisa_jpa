package com.jock.unmisa.dao;

import org.springframework.stereotype.Repository;

import com.jock.unmisa.entity.user.QUser;
import com.jock.unmisa.entity.user.User;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class UserQueryRepository {
	
	 private final JPAQueryFactory queryFactory;
	 
	 QUser T_USER = QUser.user;
	 
	 public User selectUser(String id) {
		 return queryFactory.selectFrom(T_USER)
		 				 .where(T_USER.id.eq(id))
		 				 .fetchOne();
	 }
}

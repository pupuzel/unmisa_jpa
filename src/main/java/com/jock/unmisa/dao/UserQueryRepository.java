package com.jock.unmisa.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.jock.unmisa.entity.user.QUser;
import com.jock.unmisa.entity.user.User;
import com.jock.unmisa.entity.user.UserMeta;
import com.jock.unmisa.vo.AuthVO;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class UserQueryRepository {
	
    @PersistenceContext
    private EntityManager em;
    
    private final JPAQueryFactory queryFactory;
	 
	 QUser T_USER = QUser.user;
	 
	 public User selectUser(String id) {
		 return queryFactory.selectFrom(T_USER)
		 				 .where(T_USER.id.eq(id))
		 				 .fetchOne();
	 }
	 
	 public void insertUser(User o) {
		 em.persist(o);
	 }
	 
	 public void insertUserMeta(UserMeta o) {
		 em.persist(o);
	 }
}

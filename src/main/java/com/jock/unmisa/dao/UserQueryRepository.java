package com.jock.unmisa.dao;

import static com.jock.unmisa.entity.user.QUser.user;
import static com.jock.unmisa.entity.user.QUserMeta.userMeta;

import org.springframework.stereotype.Repository;

import com.jock.unmisa.config.QuerydslRepositoryCustom;
import com.jock.unmisa.entity.user.User;
import com.jock.unmisa.entity.user.UserMeta;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.extern.slf4j.Slf4j;

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
					.where( eq(user_id, "user_id")
							  ,eq(user_nm, "user_nm")
							)
					.fetchOne();
	 }
	
	/**
	 * 사용자 정보 디테일 조회
	 * @return User
	 */
	public User selectUserInfo(String user_id, String user_nm) {
		return queryFactory
					.select(Projections.bean(
							User.class
							,user.user_id
							,user.oauth_type
							,user.user_nm
							,user.user_profile_img
							,user.user_sns
							,user.user_site
							,user.user_simple_intro
							,user.user_area
					))
					.from(user)
					.where( eq(user_id, "user_id")
							  ,eq(user_nm, "user_nm")
							)
					.fetchOne();
	 }


}

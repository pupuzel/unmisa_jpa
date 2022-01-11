package com.jock.unmisa.dao;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class UserQueryRepository {
	 private final JPAQueryFactory queryFactory;
}

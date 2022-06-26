package com.jock.unmisa.service;

import org.springframework.stereotype.Service;

import com.jock.unmisa.cmmn.UserSessionHndlr;
import com.jock.unmisa.dao.ScheduleQueryRepository;
import com.jock.unmisa.dao.UserQueryRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ScheduleService {

	private final UserSessionHndlr userSessionHndlr;
	
	private final ScheduleQueryRepository scheduleDAO;
	
	private final UserQueryRepository userDAO;
	
	
}

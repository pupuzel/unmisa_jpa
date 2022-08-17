package com.jock.unmisa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.jock.unmisa.cmmn.UserSessionHndlr;
import com.jock.unmisa.dao.ScheduleQueryRepository;
import com.jock.unmisa.dao.UserQueryRepository;
import com.jock.unmisa.entity.category.Category;
import com.jock.unmisa.entity.diary.Diary;
import com.jock.unmisa.entity.schedule.Schedule;
import com.jock.unmisa.entity.user.User;
import com.jock.unmisa.utils.ResultMap;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ScheduleService {

	private final UserSessionHndlr userSessionHndlr;
	
	private final ScheduleQueryRepository scheduleDAO;
	
	private final UserQueryRepository userDAO;
	
	public ResultMap createSchedule(Schedule schedule, HttpServletRequest request) throws Exception {
		User session = userSessionHndlr.getUserSession(request);
		
		schedule.setUser(session);
		
		scheduleDAO.insert(schedule);
		
		return new ResultMap();
		
	}
}

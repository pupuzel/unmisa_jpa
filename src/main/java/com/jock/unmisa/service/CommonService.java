package com.jock.unmisa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.jock.unmisa.cmmn.UserSessionHndlr;
import com.jock.unmisa.dao.ScheduleQueryRepository;
import com.jock.unmisa.dao.UserQueryRepository;
import com.jock.unmisa.entity.category.Category;
import com.jock.unmisa.utils.ResultMap;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommonService {

	private final ScheduleQueryRepository scheduleDAO;
	
	public ResultMap selectCategoryList(HttpServletRequest request) throws Exception {
		
		List<Category> resultList = scheduleDAO.selectCategoryList();
		
		if(resultList == null || resultList.size() < 1) {
			return new ResultMap("N");
		}else {
			return new ResultMap("Y", resultList);
		}
		
			
	}
}

package com.jock.unmisa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.jock.unmisa.cmmn.UserSessionHndlr;
import com.jock.unmisa.dao.DiaryQueryRepository;
import com.jock.unmisa.dao.UserQueryRepository;
import com.jock.unmisa.entity.diary.Diary;
import com.jock.unmisa.entity.user.User;
import com.jock.unmisa.utils.DateUtils;
import com.jock.unmisa.utils.ResultMap;
import com.jock.unmisa.utils.StringUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class DiaryService {

	private final UserSessionHndlr userSessionHndlr;
	
	private final DiaryQueryRepository diaryDAO;
	
	private final UserQueryRepository userDAO;
	
	public ResultMap createDiary(Diary diary, HttpServletRequest request) throws Exception {
		User session = userSessionHndlr.getUserSession(request);
		
		// ����� ��ȸ
		User user = userDAO.selectUser(session.getUser_id(), null);
		
		// �ϱ� �ۼ�
		diary.setUser(user);
		diaryDAO.insert(diary);
		
		//������ �ϱ��ۼ� ��¥ ����
		user.getUser_meta().setLast_diary_ymd(diary.getDiary_ymd());
		
		return new ResultMap();
		
	}
	
	public ResultMap checkAvailable(Diary diary, HttpServletRequest request) throws Exception {
		User session = userSessionHndlr.getUserSession(request);
		
		// ����� ��ȸ
		User user = userDAO.selectUser(session.getUser_id(), null);
		
		// ������ �ϱ� �ۼ� ��¥
		String LastDiaryYMD = user.getUser_meta().getLast_diary_ymd();
		if(StringUtil.isEmpty(LastDiaryYMD)) {
			return new ResultMap("Y");
		}else {
			if( 0 > DateUtils.diffDay(LastDiaryYMD, diary.getDiary_ymd())){
				return new ResultMap("Y");
			}else {
				return new ResultMap("N");
			}
		}
		
	}
	
	
	public ResultMap selectDiaryList(Diary diary) throws Exception {
		
		List<Diary> list = diaryDAO.selectDiary(diary.getUser().getUser_id(), diary.getDiary_id());
		
		return new ResultMap("Y");
	}
	
	
}

package com.jock.unmisa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.jock.unmisa.cmmn.UserSessionHndlr;
import com.jock.unmisa.dao.DiaryQueryRepository;
import com.jock.unmisa.dao.UserQueryRepository;
import com.jock.unmisa.entity.diary.Diary;
import com.jock.unmisa.entity.diary.DiaryCmt;
import com.jock.unmisa.entity.diary.DiaryLikeHist;
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
	
	public ResultMap createDiary(Diary diaryVo, HttpServletRequest request) throws Exception {
		User session = userSessionHndlr.getUserSession(request);
		
		// ����� ��ȸ
		User user = userDAO.selectUser(session.getUser_id(), null);
		
		// �ϱ� �ۼ�
		diaryVo.setUser(user);
		diaryDAO.insert(diaryVo);
		
		//������ �ϱ��ۼ� ��¥ ����
		user.getUser_meta().setLast_diary_ymd(diaryVo.getDiary_ymd());
		
		return new ResultMap();
		
	}
	
	public ResultMap checkAvailable(Diary diaryVo, HttpServletRequest request) throws Exception {
		User session = userSessionHndlr.getUserSession(request);
		
		// ����� ��ȸ
		User user = userDAO.selectUser(session.getUser_id(), null);
		
		// ������ �ϱ� �ۼ� ��¥
		String LastDiaryYMD = user.getUser_meta().getLast_diary_ymd();
		if(StringUtil.isEmpty(LastDiaryYMD)) {
			return new ResultMap("Y");
		}else {
			if( 0 > DateUtils.diffDay(LastDiaryYMD, diaryVo.getDiary_ymd())){
				return new ResultMap("Y");
			}else {
				return new ResultMap("N");
			}
		}
		
	}
	
	
	public ResultMap selectDiaryList(HttpServletRequest request, Diary diaryVo) throws Exception {
		User session = userSessionHndlr.getUserSession(request);
		List<Diary> list = null;
		
		// ������ �ִٸ� ���ƿ� �̷±��� ��ȸ
		if(session != null) {
			list = diaryDAO.selectDiaryList( diaryVo.getUser().getUser_id()
											  , diaryVo.getDiary_id()
											  , session.getUser_id());
		}else {
			list = diaryDAO.selectDiaryList( diaryVo.getUser().getUser_id()
											  , diaryVo.getDiary_id()
											  , null);
		}
		
		if(list.size() == 0) {
			return new ResultMap("N");
		}else {
			return new ResultMap("Y", list);
		}
		
	}
	
	
	public ResultMap selectDiary(HttpServletRequest request, Diary diaryVo) throws Exception {
		User session = userSessionHndlr.getUserSession(request);
		Diary diary = null;
				
		// ������ �ִٸ� ���ƿ� �̷±��� ��ȸ
		if(session != null) {
			diary  = diaryDAO.selectDiary(diaryVo.getDiary_id(), session.getUser_id());
		}else {
			diary  = diaryDAO.selectDiary(diaryVo.getDiary_id(), null);
		}
		
		if(diary == null) {
			return new ResultMap("N");
		}else {
			User user = new User();
			user.setUser_nm(diary.getUser_nm());
			user.setUser_profile_img(diary.getUser_profile_img());
			
			ResultMap map = new ResultMap();
			map.put("user", user);
			map.put("diary", diary);
			
			return new ResultMap("Y", map);
		}
		
			
	}
	
	public ResultMap saveDiaryLike(HttpServletRequest request, Diary diaryVo) throws Exception {
		User session = userSessionHndlr.getUserSession(request);
		
		// ���ƿ� �̷� ��ȸ
		DiaryLikeHist diaryLikeHist = diaryDAO.selectDiaryLikeHist(diaryVo.getDiary_id(), session.getUser_id());
		
		// ���ƿ� �̷� save
		if(diaryLikeHist == null) {
			diaryLikeHist = new DiaryLikeHist();
			diaryLikeHist.setDiary(diaryVo);
			diaryLikeHist.setUser(session);
			diaryLikeHist.setLike_yn(diaryVo.isLike_yn());
			
			diaryDAO.insert(diaryLikeHist);
		}else {
			diaryLikeHist.setLike_yn(diaryVo.isLike_yn());
		}
		
		// ���ƿ� cnt save
		diaryDAO.updateDiaryLikeCnt(diaryVo.getDiary_id(), diaryVo.isLike_yn());
		
		return new ResultMap("Y");
	}
	
	
	public ResultMap createComment(DiaryCmt diaryCmtVo, HttpServletRequest request) throws Exception {
		User session = userSessionHndlr.getUserSession(request);
		
		// ����� ��ȸ
		User user = userDAO.selectUser(session.getUser_id(), null);
		
		// ��� �ۼ�
		diaryCmtVo.setUser(user);
		diaryDAO.insert(diaryCmtVo);
		diaryCmtVo.setBundle_cmt_id(diaryCmtVo.getCmt_id());
		
		// ��� cnt save
		diaryDAO.updateDiaryCmtCnt(diaryCmtVo.getDiary().getDiary_id(), 1);
		
		return new ResultMap();
		
	}
	
}

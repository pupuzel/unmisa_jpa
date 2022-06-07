package com.jock.unmisa.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jock.unmisa.config.validate.DiaryValidationGroup;
import com.jock.unmisa.entity.diary.Diary;
import com.jock.unmisa.entity.diary.DiaryCmt;
import com.jock.unmisa.service.DiaryService;
import com.jock.unmisa.utils.ResultMap;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@RestController
public class DiaryController {
	
	private DiaryService diaryService;

	/**
	 * 운동 일기 작성
	 * @return ResponseEntity<ResultMap>
	 */
	@PostMapping("/api/diary/create")
	public ResponseEntity<ResultMap> create(@RequestBody @Validated(DiaryValidationGroup.createGroup.class) Diary diaryVo, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
			
			if(response.getStatus() == HttpServletResponse.SC_UNAUTHORIZED) {
				return new ResponseEntity<ResultMap>(HttpStatus.UNAUTHORIZED);
			}else {
				return new ResponseEntity<ResultMap>(diaryService.createDiary(diaryVo, request), HttpStatus.OK);
			}
			
	}
	
	/**
	 * 운동 일기 작성 가능여부
	 * @return ResponseEntity<ResultMap>
	 */
	@PostMapping("/api/diary/checkAvailable")
	public ResponseEntity<ResultMap> checkAvailable(@RequestBody Diary diaryVo, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
			
			if(response.getStatus() == response.SC_UNAUTHORIZED) {
				return new ResponseEntity<ResultMap>(HttpStatus.UNAUTHORIZED);
			}else {
				return new ResponseEntity<ResultMap>(diaryService.checkAvailable(diaryVo, request), HttpStatus.OK);
			}
			
	}
	
	/**
	 * 사용자 운동 일기 리스트 조회
	 * @return ResponseEntity<ResultMap>
	 */
	@PostMapping("/api/diary/selectDiaryList")
	public ResponseEntity<ResultMap> selectDiaryList(@RequestBody Diary diaryVo, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
			
		return new ResponseEntity<ResultMap>(diaryService.selectDiaryList(request, diaryVo), HttpStatus.OK);
			
	}
	
	/**
	 * 사용자 운동 일기 조회
	 * @return ResponseEntity<ResultMap>
	 */
	@PostMapping("/api/diary/selectDiary")
	public ResponseEntity<ResultMap> selectDiary(@RequestBody Diary diaryVo, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
			
		return new ResponseEntity<ResultMap>(diaryService.selectDiary(request, diaryVo), HttpStatus.OK);
			
	}
	
	/**
	 * 운동 일기 좋아요
	 * @return ResponseEntity<ResultMap>
	 */
	@PostMapping("/api/diary/saveDiaryLike")
	public ResponseEntity<ResultMap> saveDiaryLike(@RequestBody Diary diaryVo, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
			if(response.getStatus() == HttpServletResponse.SC_UNAUTHORIZED) {
				return new ResponseEntity<ResultMap>(HttpStatus.UNAUTHORIZED);
			}else {
				return new ResponseEntity<ResultMap>(diaryService.saveDiaryLike(request, diaryVo), HttpStatus.OK);
			}
			
	}
	
	
	/**
	 *  운동 일기 댓글 작성
	 * @return ResponseEntity<ResultMap>
	 */
	@PostMapping("/api/diary/comment/create")
	public ResponseEntity<ResultMap> createComment(@RequestBody @Validated(DiaryValidationGroup.createCommentGroup.class) DiaryCmt diaryCmtVo, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
			
			if(response.getStatus() == HttpServletResponse.SC_UNAUTHORIZED) {
				return new ResponseEntity<ResultMap>(HttpStatus.UNAUTHORIZED);
			}else {
				return new ResponseEntity<ResultMap>(diaryService.createComment(diaryCmtVo, request), HttpStatus.OK);
			}
			
	}
	
	
	
}

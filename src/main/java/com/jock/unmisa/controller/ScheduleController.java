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
import com.jock.unmisa.service.ScheduleService;
import com.jock.unmisa.utils.ResultMap;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@RestController
public class ScheduleController {
	
	private ScheduleService scheduleService;

}

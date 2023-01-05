package com.yido.clubd.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yido.clubd.model.DrBayInfo;
import com.yido.clubd.model.DrBkHistory;
import com.yido.clubd.model.DrBkOpenTime;
import com.yido.clubd.model.ProNotice;
import com.yido.clubd.service.DrBayInfoService;
import com.yido.clubd.service.DrBkHistoryService;
import com.yido.clubd.service.DrBkOpenTimeService;
import com.yido.clubd.service.DrMsMaininfoService;
import com.yido.clubd.service.DrProScheduleService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/book")
public class BookController {

	@Autowired
	private DrBkHistoryService drBkHistoryService;
	
	@Autowired
	private DrBayInfoService drBayInfoService;
	
	@Autowired
	private DrBkOpenTimeService drBkOpenTimeService;
	
	/**
	 * 예약페이지
	 * 
	 * @param model
	 * @param req
	 * @return
	 */
	@RequestMapping("/bookMain")  
	public String bookMain(Model model, HttpServletRequest req) {
		
		try {
			
			// BAY 목록
			Map<String, Object> map = new HashMap<String, Object>();
			List<DrBayInfo> bayList = drBayInfoService.selectList(map);
			log.info("[bookMain] bayList : " + bayList);
			model.addAttribute("bayList", bayList);
			
			// 현재 시간 기준으로 가장 마지막 오픈날짜 구하기
			DrBkOpenTime drBkOpenTime = new DrBkOpenTime();
			drBkOpenTime.setMsLevel("11");
			drBkOpenTime.setBayCondi("001");
			drBkOpenTime = drBkOpenTimeService.getBkDay(drBkOpenTime);
			log.info("[bookMain] getBkDay : " + drBkOpenTime.getBkDay());
			model.addAttribute("maxBkDay", drBkOpenTime.getBkDay());
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return "/book/bookMain";
	}

	/**
	 * 예약 등록
	 * 
	 * @param req
	 * @param drBkHistory
	 * @return
	 */
	@RequestMapping("/insertBook")
	@ResponseBody
	public Map<String, Object> insertBook(HttpServletRequest req, DrBkHistory drBkHistory){

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			drBkHistoryService.insertDrBkHistory(drBkHistory);
			map.put("result",  true);
		} catch(Exception e) {
			e.printStackTrace();
			map.put("result",  false);
			map.put("message", "등록중 오류가 발생하였습니다.");
		}
		
		return map;
	}
	
}

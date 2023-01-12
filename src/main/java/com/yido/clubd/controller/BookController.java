package com.yido.clubd.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yido.clubd.common.utils.ResultVO;
import com.yido.clubd.common.utils.SessionVO;
import com.yido.clubd.common.utils.Utils;
import com.yido.clubd.model.DrBayInfo;
import com.yido.clubd.model.DrBkHistory;
import com.yido.clubd.model.DrBkMark;
import com.yido.clubd.model.DrBkOpenTime;
import com.yido.clubd.model.DrBkTime;
import com.yido.clubd.model.ProNotice;
import com.yido.clubd.service.BookService;
import com.yido.clubd.service.DrBayInfoService;
import com.yido.clubd.service.DrBkHistoryService;
import com.yido.clubd.service.DrBkMarkService;
import com.yido.clubd.service.DrBkOpenTimeService;
import com.yido.clubd.service.DrBkTimeService;
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
	
	@Autowired
	private DrBkTimeService drBkTimeService;
	
	@Autowired
	private DrBkMarkService drBkMarkService;

	@Autowired
	private DrMsMaininfoService drMsMaininfoService;
	
	@Autowired
	private BookService bookService;
	
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
			log.info("[bookMain] bayList 1: " + bayList);
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
	}*/
	
	/**
	 * 베이별 잔여시간 조회
	 * - 파라미터 : 베이, 날짜, 회원등급 등
	 * 
	 * @param req
	 * @param params
	 * @return
	 */
	@RequestMapping("/bookAvailableTime")
	@ResponseBody
	public List<DrBkTime> bookAvailableTime(HttpServletRequest req, @RequestParam Map<String, Object> params){
		
		List<DrBkTime> timeList = new ArrayList<DrBkTime>();
		log.info("[bookAvailableTime] params :" + params);
		
		try {
			timeList = drBkTimeService.bookAvailableTime(params);

			log.info("[bookAvailableTime] timeList :" + timeList);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return timeList;
	}

	/**
	 * 잔여수량 체크(=예약가능여부 체크) + 예약선점 처리
	 * 
	 * @param req
	 * @param params
	 * @return
	 */
	@RequestMapping("/chkBook")
	@ResponseBody
	public ResultVO chkBook(HttpServletRequest req, @RequestParam Map<String, Object> params){
		
		log.info("[chkBook] params : " + params);
		String ipAddr = Utils.getClientIpAddress(req);
    	ResultVO result = new ResultVO();
    	
		try {
			params.put("ipAddr", ipAddr);
			
			String grantYn = drMsMaininfoService.chkMsBkGrant(params);
			if (grantYn.equals("N")) {
				result.setCode("9999");
				result.setMessage("위약이 걸려있어 예약이 불가능합니다.");	
				return result;
			} 
			
			result = bookService.chkBookLogic(params);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 예약 선점된거 풀기 
	 * 
	 * @param req
	 * @param params
	 * @return
	 */
	@RequestMapping("/unBkMark")
	@ResponseBody
	public ResultVO unBkMark(HttpServletRequest req, @RequestParam Map<String, Object> params){
		
		log.info("[unBkMark] params : " + params);
    	ResultVO result = new ResultVO();
    	
		try {
			result = bookService.unBkMarkLogic(params);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}

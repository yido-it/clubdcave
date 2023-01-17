package com.yido.clubd.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.yido.clubd.common.utils.ResultVO;
import com.yido.clubd.common.utils.SessionVO;
import com.yido.clubd.common.utils.Utils;
import com.yido.clubd.model.BookInfoVO;
import com.yido.clubd.model.DrBayInfo;
import com.yido.clubd.model.DrBkOpenTime;
import com.yido.clubd.model.DrBkTime;
import com.yido.clubd.service.BookService;
import com.yido.clubd.service.ClDayInfoService;
import com.yido.clubd.service.DrBayInfoService;
import com.yido.clubd.service.DrBkHistoryService;
import com.yido.clubd.service.DrBkMarkService;
import com.yido.clubd.service.DrBkOpenTimeService;
import com.yido.clubd.service.DrBkTimeService;
import com.yido.clubd.service.DrMsMaininfoService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/book")
public class BookController {

	@Autowired
	private DrBayInfoService drBayInfoService;
	
	@Autowired
	private DrBkOpenTimeService drBkOpenTimeService;
	
	@Autowired
	private DrBkTimeService drBkTimeService;

	@Autowired
	private DrMsMaininfoService drMsMaininfoService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private ClDayInfoService clDayInfoService;
	
	
	/**
	 * 예약페이지
	 * 
	 * @param model
	 * @param req
	 * @return
	 */
	@RequestMapping("/bookMain/{coDiv}")  
	public String bookMain(Model model, HttpServletRequest req, @PathVariable String coDiv) {
		HttpSession session = req.getSession();
		SessionVO sessionVO = (SessionVO) session.getAttribute("msMember");
		
		try {
			if (sessionVO != null) {
				// BAY 목록
				Map<String, Object> map = new HashMap<String, Object>();
				List<DrBayInfo> bayList = drBayInfoService.selectList(map);
				model.addAttribute("bayList", bayList);
				log.info("bayList size : " + bayList.size());
				
				// 현재 시간 기준으로 가장 마지막 오픈날짜 구하기
				DrBkOpenTime drBkOpenTime = new DrBkOpenTime();
				drBkOpenTime.setMsLevel(sessionVO.getMsLevel());
				drBkOpenTime.setBayCondi("001");
				drBkOpenTime = drBkOpenTimeService.getBkDay(drBkOpenTime);
				model.addAttribute("maxBkDay", drBkOpenTime.getBkDay());
			} 
			
			model.addAttribute("coDiv", coDiv);
		} catch(Exception e) {
			e.printStackTrace();
		}

		return "/book/bookMain";
	}
	
	/**
	 * 예약 캘린더에 표출할 데이터 조회 
	 * 
	 * @param model
	 * @param req
	 * @return
	 */
	@RequestMapping("/getCalendar")  
	@ResponseBody
	public List<Map<String, Object>> getCalendar(Model model, HttpServletRequest req) {
		HttpSession session = req.getSession();
		SessionVO sessionVO = (SessionVO) session.getAttribute("msMember");
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		try {
			if (sessionVO != null) {
				
				// 달력 조회
				Map<String, Object> cMap = new HashMap<String, Object>();
				cMap.put("coDiv", req.getParameter("coDiv"));
				cMap.put("selYM", req.getParameter("selYM"));
				cMap.put("msLevel", req.getParameter("msLevel"));
				list = clDayInfoService.selectList(cMap); 
				
				log.info("[getCalendar] list : " + list);
			} 
			
		} catch(Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	/**
	 * 예약내역 조회
	 * 
	 * @param model
	 * @param req
	 * @return
	 */
	@RequestMapping("/bookList")  
	public String bookList(Model model, HttpServletRequest req) {
		HttpSession session = req.getSession();
		SessionVO sessionVO = (SessionVO) session.getAttribute("msMember");

		log.info("bookList");
		try {
			//
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return "/book/bookList";
	}

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
	 * 회원위약체크
	 * 
	 * @param req
	 * @param params
	 * @return
	 */
	@RequestMapping("/chkGrant")
	@ResponseBody
	public ResultVO chkGrant(HttpServletRequest req, BookInfoVO bookInfo){
		
		log.info("[chkGrant] bookInfo : " + bookInfo);
    	ResultVO result = new ResultVO();
    	
		try {
				
			String grantYn = drMsMaininfoService.chkMsBkGrant(bookInfo);
			if (grantYn.equals("N")) {
				result.setCode("9999");
				result.setMessage("위약이 걸려있어 예약이 불가능합니다.");	
				return result;
			} 
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
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
	public ResultVO chkBook(HttpServletRequest req, BookInfoVO bookInfo){
		
		log.info("[chkBook] bookInfo : " + bookInfo);
		String ipAddr = Utils.getClientIpAddress(req);
    	ResultVO result = new ResultVO();
    	
		try {
			bookInfo.setIpAddr(ipAddr);
					
			// 잔여수량 체크(=예약가능여부 체크) + 예약선점 처리
			result = bookService.chkBookLogic(bookInfo);
			
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
	public ResultVO unBkMark(HttpServletRequest req, BookInfoVO bookInfo){
		
		log.info("[unBkMark] params : " + bookInfo);
    	ResultVO result = new ResultVO();
    	
		try {
			result = bookService.unBkMarkLogic(bookInfo);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}

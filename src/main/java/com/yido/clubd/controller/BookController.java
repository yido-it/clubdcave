package com.yido.clubd.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import com.yido.clubd.model.CoPlace;
import com.yido.clubd.model.DrBayInfo;
import com.yido.clubd.model.DrBkHistory;
import com.yido.clubd.model.DrBkHistoryTemp;
import com.yido.clubd.model.DrBkOpenTime;
import com.yido.clubd.model.DrBkTime;
import com.yido.clubd.model.DrVoucherCode;
import com.yido.clubd.model.DrVoucherList;
import com.yido.clubd.service.BookService;
import com.yido.clubd.service.ClDayInfoService;
import com.yido.clubd.service.CoPlaceService;
import com.yido.clubd.service.DrBayInfoService;
import com.yido.clubd.service.DrBkHistoryService;
import com.yido.clubd.service.DrBkHistoryTempService;
import com.yido.clubd.service.DrBkOpenTimeService;
import com.yido.clubd.service.DrBkTimeService;
import com.yido.clubd.service.DrCostInfoService;
import com.yido.clubd.service.DrMsMaininfoService;
import com.yido.clubd.service.DrVoucherCodeService;
import com.yido.clubd.service.DrVoucherListService;
import com.yido.clubd.service.DrVoucherSaleService;

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
	
	@Autowired
	private DrBkHistoryTempService drBkHistoryTempService;
	
	@Autowired
	private DrBkHistoryService drBkHistoryService;
	
	@Autowired
	private CoPlaceService coPlaceService;
	
	@Autowired
	private DrCostInfoService drCostInfoService;
	
	@Autowired
	private DrVoucherSaleService drVoucherSaleService;
	
	@Autowired
	private DrVoucherListService drVoucherListService;
	
	/**
	 * [예약] 예약페이지
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
	 * [예약] 예약 2단계 페이지로 이동 (사용자가 담아둔 예약 정보를 임시테이블에 넣기)
	 * 
	 * @param req
	 * @param bookInfo
	 * @param coDiv
	 * @return
	 */
	@RequestMapping("/book2/{coDiv}")
	@ResponseBody
	public ResultVO tempBook2(HttpServletRequest req, BookInfoVO bInfo, @PathVariable String coDiv){
		
		log.info("[tempBook2] bookInfo : " + bInfo);
    	ResultVO result = new ResultVO();
		LocalDateTime nowDt = LocalDateTime.now();
		
		HttpSession session = req.getSession();
		SessionVO sessionVO = (SessionVO) session.getAttribute("msMember");
		String ipAddr = Utils.getClientIpAddress(req);
		
		String serialNo = "";
		
		try {

			if (sessionVO == null) {
				return result;
			}
				
			// 시리얼번호 생성 (YYMMDDHHMMSS)
			// 임시 테이블 PK : 지점코드 + 시리얼번호 + 회원번호
			serialNo = nowDt.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
			
			// 예약시간 
			List<Map<String, Object>> bkList = bInfo.getBkList();
			String bkTime = "";
			String bkTime2 = "";
			for (Map<String, Object> bk : bkList) {
				String tmpTime = bk.get("bkTime").toString(); // 1100
				if (bkTime.equals("")) {
					bkTime = tmpTime.substring(0, 2) + ":" + tmpTime.substring(2);
					bkTime2 = tmpTime;
				} else {
					bkTime += ", " + tmpTime.substring(0, 2) + ":" + tmpTime.substring(2);
					bkTime2 += ", " + tmpTime;
				}
			}
			
			DrBkHistoryTemp temp = new DrBkHistoryTemp();
			temp.setCoDiv(coDiv);
			temp.setSerialNo(serialNo);
			temp.setMsNum(sessionVO.getMsNum());
			temp.setBayCd(bInfo.getBayCondi());
			temp.setBkDay(bInfo.getBkDay());
			temp.setBkTime(bkTime);
			temp.setBkTime2(bkTime2);
			temp.setInputIp(ipAddr);
			
			// 임시 테이블에 데이터 저장
			drBkHistoryTempService.insertDrBkHistoryTemp(temp);
			result.setData(serialNo);
			
		} catch(Exception e) {
			e.printStackTrace();
			result.setCode("9999");
			result.setData("");
		}
		
		return result;
	}
	
	/**
	 * [예약] 예약 2단계 페이지 (예약 임시 데이터 조회 해서 화면에 표출해주기)
	 * 
	 * @param model
	 * @param req
	 * @param serialNo
	 * @return
	 */
	@RequestMapping("/book2/{coDiv}/{serialNo}")  
	public String book2(Model model, HttpServletRequest req, @PathVariable String coDiv, @PathVariable String serialNo) {
		HttpSession session = req.getSession();
		SessionVO sessionVO = (SessionVO) session.getAttribute("msMember");
		String returnPage = "/book/book2";

		log.info("sessionVO: {}", sessionVO);
		try {
			if (coDiv == null || serialNo == null || sessionVO == null 
					|| coDiv.equals("") || serialNo.equals("") || sessionVO.getMsId().equals("")) {
				return returnPage;
			}
			
			// 예약 임시 테이블 조회
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("coDiv"		, coDiv);
			map.put("serialNo"	, serialNo);
			map.put("msNum"		, sessionVO.getMsNum());
			DrBkHistoryTemp temp = drBkHistoryTempService.getHistory(map);

			log.info("예약 임시 데이터: {}", temp);
			
			if (temp != null) {
				model.addAttribute("bkHis", temp);
				
				// 날짜 포맷 변경
				String tmpDay = temp.getBkDay();	// 20230131			
				String bkDay = tmpDay.substring(0, 4) + "년 " 
							+ tmpDay.substring(4, 6) + "월 " 
							+ tmpDay.substring(6) + "일" ;
				model.addAttribute("bkDay", bkDay);
				// end.
								
				// 지점 정보 조회 
				List<CoPlace> place = coPlaceService.selectList(map); 
				model.addAttribute("place", place.get(0));
				// end.
				
				// 베이 정보 조회
				map.put("bayCondi",temp.getBayCd());
				List<DrBayInfo> bay = drBayInfoService.selectList(map);
				model.addAttribute("bay", bay.get(0));
				// end.
				
				// 해당 날짜의 요금 조회
				Integer amount = 0;
				map.put("bkDay", temp.getBkDay());
				Map<String, Object> cost = drCostInfoService.getCostInfo(map);

				model.addAttribute("timeAmt", cost.get("DR_AMOUNT").toString());	// 시간당 금액 (이용권1장 = 시간당금액)
				if (temp.getBkTime().indexOf(",") > 0) {
					String[] strArr = temp.getBkTime().split(",");	
					amount = Integer.parseInt(cost.get("DR_AMOUNT").toString());
					amount = amount * strArr.length;
					model.addAttribute("amount", amount);
				} else {
					amount = Integer.parseInt(cost.get("DR_AMOUNT").toString());
					model.addAttribute("amount", amount);
				}
				// end.
				
				// 이용권 내역 조회
				List<Map<String, Object>> vList = drVoucherSaleService.selectList(map);
				model.addAttribute("vcList", vList);
				// end.
				
				// log.info("지점 정보: {}", place.get(0));
				// log.info("베이 정보: {}", bay.get(0));
				// log.info("요금: {}", cost);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

		return returnPage;
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
	 * [예약] 예약내역 조회
	 * 
	 * @param model
	 * @param req
	 * @return
	 */
	@RequestMapping("/bookList/{coDiv}")  
	public String bookList(Model model, HttpServletRequest req, @PathVariable String coDiv) {
		HttpSession session = req.getSession();
		SessionVO sessionVO = (SessionVO) session.getAttribute("msMember");

		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("msNum", sessionVO.getMsNum());
			map.put("coDiv", coDiv);
			List<Map<String, Object>> list = drBkHistoryService.selectBkHis(map);			
			model.addAttribute("list", list);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return "/book/bookList";
	}

	/**
	 * [예약] 결제 완료 페이지 
	 * 
	 * @param model
	 * @param req
	 * @param calcSerialNo
	 * @return
	 */
	@RequestMapping("/bookConfirm")  
	public String bookConfirm(Model model, HttpServletRequest req) {
		
		try {
			if (req.getParameter("calcSerialNo") != null) {
				// 대표 예약고유번호로 데이터 조회 
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("calcSerialNo", req.getParameter("calcSerialNo"));
				List<Map<String, Object>> list = drBkHistoryService.selectBkHis(map);
				model.addAttribute("bk", list.get(0));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return "/book/bookConfirm";
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

		HttpSession session = req.getSession();
		SessionVO sessionVO = (SessionVO) session.getAttribute("msMember");
		try {
				
			bookInfo.setMsId(sessionVO.getMsId());
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
    	
		HttpSession session = req.getSession();
		SessionVO sessionVO = (SessionVO) session.getAttribute("msMember");

		try {
			bookInfo.setIpAddr(ipAddr);
			bookInfo.setMsId(sessionVO.getMsId());
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

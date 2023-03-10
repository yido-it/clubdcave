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

import com.yido.clubd.common.service.CommonService;
import com.yido.clubd.common.utils.ResultVO;
import com.yido.clubd.common.utils.SessionVO;
import com.yido.clubd.common.utils.Utils;
import com.yido.clubd.model.BookInfoVO;
import com.yido.clubd.model.CdCommon;
import com.yido.clubd.model.CoPlace;
import com.yido.clubd.model.DrBayInfo;
import com.yido.clubd.model.DrBkHistoryTemp;
import com.yido.clubd.model.DrBkMark;
import com.yido.clubd.model.DrBkOpenTime;
import com.yido.clubd.model.DrBkTime;
import com.yido.clubd.service.BookService;
import com.yido.clubd.service.ClDayInfoService;
import com.yido.clubd.service.CoPlaceService;
import com.yido.clubd.service.DrBayInfoService;
import com.yido.clubd.service.DrBkHistoryService;
import com.yido.clubd.service.DrBkHistoryTempService;
import com.yido.clubd.service.DrBkMarkService;
import com.yido.clubd.service.DrBkOpenTimeService;
import com.yido.clubd.service.DrBkTimeService;
import com.yido.clubd.service.DrCostInfoService;
import com.yido.clubd.service.DrVoucherListService;
import com.yido.clubd.service.DrVoucherSaleService;
import com.yido.clubd.service.MemberService;

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
	private MemberService memberService;
	
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
	
	@Autowired
	private CommonService commonService;

	@Autowired
	private DrBkMarkService drBkMarkService;
	
	/**
	 * [??????] ???????????????
	 * 
	 * @param model
	 * @param req
	 * @return
	 */
	@RequestMapping("/bookMain/{coDiv}")  
	public String bookMain(Model model, HttpServletRequest req, @PathVariable String coDiv) {
		HttpSession session = req.getSession();
		SessionVO sessionVO = (SessionVO) session.getAttribute("msMember");
		String returnPage = "/book/bookMain";
		
		try {
			if (sessionVO == null) return returnPage;
			
			// BAY ??????
			Map<String, Object> map = new HashMap<String, Object>();
			List<DrBayInfo> bayList = drBayInfoService.selectList(map);
			model.addAttribute("bayList", bayList);
			log.info("bayList size : " + bayList.size());
			
			// ?????? ?????? ???????????? ?????? ????????? ???????????? ?????????
			DrBkOpenTime drBkOpenTime = new DrBkOpenTime();
			drBkOpenTime.setMsLevel(sessionVO.getMsLevel());
			drBkOpenTime.setBayCondi("001");
			drBkOpenTime = drBkOpenTimeService.getBkDay(drBkOpenTime);
			model.addAttribute("maxBkDay", drBkOpenTime.getBkDay());
		
			model.addAttribute("coDiv", coDiv);
			
			// ?????? ?????? ( grantYn > N : ???????????? )
			Map<String, Object> msMap = new HashMap<String, Object>();
			msMap.put("msId", sessionVO.getMsId());
			String grantYn = memberService.chkMsBkGrant(msMap);
			model.addAttribute("grantYn", grantYn);			
			
			// ?????? ?????? ?????? (?????? : ???????????? & ?????? : ??????,??????,???????????? ?????? ???)
			int bkCnt = drBkHistoryService.getBkCnt(sessionVO.getMsNum());
			model.addAttribute("bkCnt", bkCnt);		
			
			// ?????? ?????? ?????? 
			CdCommon common = new CdCommon();
			common.setCdDivision("002");
			common.setCdCode(sessionVO.getMsLevel());	// ??????
			common = commonService.getCommonCode(common);
			int maxBkCnt = common != null && common.getCdLength() != null && !common.getCdLength().equals("") 
					? Integer.parseInt(common.getCdLength()) : 4;				
			model.addAttribute("maxBkCnt", maxBkCnt);
			// end.
			
		} catch(Exception e) {
			e.printStackTrace();
		}

		return returnPage;
	}

	/**
	 * [??????] ?????? 2?????? ???????????? ?????? (???????????? ????????? ?????? ????????? ?????????????????? ??????)
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
		
		HttpSession session = req.getSession();
		SessionVO sessionVO = (SessionVO) session.getAttribute("msMember");
		String ipAddr = Utils.getClientIpAddress(req);
		
		try {
			if (sessionVO == null) {
				return result;
			}

			bInfo.setMsId(sessionVO.getMsId());
			bInfo.setMsNum(sessionVO.getMsNum());
			bInfo.setCoDiv(coDiv);
			bInfo.setIpAddr(ipAddr);
			/**
			 * ?????????????????? ?????? 
			 * --> ?????? ??????????????? ???????????? ?????? + ?????????????????? ????????? ??????
			 * --> ?????? ??????????????? ?????? ?????? 
			 */
			result = bookService.chkBookLogic(bInfo);
				
		} catch(Exception e) {
			e.printStackTrace();
			result.setCode("9999");
			result.setMessage("????????? ????????? ?????????????????????.");
		}
		
		return result;
	}
	
	/**
	 * [??????] ?????? 2?????? ????????? (?????? ?????? ????????? ?????? ?????? ????????? ???????????????)
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
		
		try {
			if (coDiv == null || serialNo == null || sessionVO == null 
					|| coDiv.equals("") || serialNo.equals("") || sessionVO.getMsId().equals("")) {
				return returnPage;
			}

			// ?????? ?????? ????????? ??????
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("coDiv"		, coDiv);
			map.put("serialNo"	, serialNo);
			map.put("msNum"		, sessionVO.getMsNum());
			DrBkHistoryTemp temp = drBkHistoryTempService.getHistory(map);

			if (temp != null) {
	
				model.addAttribute("bkHis", temp);
				
				// ?????? ?????? ??????
				String tmpDay = temp.getBkDay();	// 20230131			
				String bkDay = tmpDay.substring(0, 4) + "??? " 
							+ tmpDay.substring(4, 6) + "??? " 
							+ tmpDay.substring(6) + "???" ;
				model.addAttribute("bkDay", bkDay);
				// end.
								
				// ?????? ?????? ?????? 
				List<CoPlace> place = coPlaceService.selectList(map); 
				model.addAttribute("place", place.get(0));
				// end.
				
				// ?????? ?????? ??????
				map.put("bayCondi",temp.getBayCd());
				List<DrBayInfo> bay = drBayInfoService.selectList(map);
				model.addAttribute("bay", bay.get(0));
				// end.
				
				// ?????? ????????? ?????? ??????
				Integer amount = 0;
				map.put("bkDay", temp.getBkDay());
				Map<String, Object> cost = drCostInfoService.getCostInfo(map);

				model.addAttribute("timeAmt", cost.get("DR_AMOUNT").toString());	// ????????? ?????? (?????????1??? = ???????????????)
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
				
				// ????????? ?????? ??????
				List<Map<String, Object>> vList = drVoucherSaleService.selectList(map);
				model.addAttribute("vcList", vList);
				// end.
				
				// ???????????? ?????? ??????
				BookInfoVO bkInfo = new BookInfoVO();
				bkInfo.setCoDiv(coDiv);
				bkInfo.setBkDay(temp.getBkDay());
				if (temp.getBkTime2().indexOf(",") > 0) {
					String tmpBkTime[] = temp.getBkTime2().split(",");
					bkInfo.setBkTime(tmpBkTime[0]);
				} else {
					bkInfo.setBkTime(temp.getBkTime2());
				}
				bkInfo.setMsId(sessionVO.getMsId());
				List<DrBkMark> mList = drBkMarkService.selectList(bkInfo);
				
				if (mList.size() > 0) {
					model.addAttribute("entryDatetime", mList.get(0).getEntryDatetime());	
				}			
				// end.
				
				// ?????? ????????????&???????????? ??????
				BookInfoVO bkInfo2 = new BookInfoVO();
				bkInfo2.setCoDiv(coDiv);
				bkInfo2.setBayCondi(temp.getBayCd());
				bkInfo2.setBkDay(temp.getBkDay());
				List<DrBkTime> bkTimeList = drBkTimeService.getBkTime(bkInfo2);
				model.addAttribute("useTime", bkTimeList.get(0).getBkUseTime());	// ????????????
				model.addAttribute("gapTime", bkTimeList.get(0).getBkGapTime());	// ????????????
				// end.
			}

		} catch(Exception e) {
			e.printStackTrace();
		}

		return returnPage;
	}
	
	/**
	 * ?????? ???????????? ????????? ????????? ?????? 
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
			if (sessionVO == null) return list;
				
			Map<String, Object> cMap = new HashMap<String, Object>();

			cMap.put("coDiv"		, req.getParameter("coDiv"));
			cMap.put("bayCondi"		, req.getParameter("bayCondi"));
			cMap.put("selYM"		, req.getParameter("selYM"));
			cMap.put("msLevel"		, req.getParameter("msLevel"));
			
			if (req.getParameter("bayCondi").toString().equals("")) {				
				// ?????? ??????
				list = clDayInfoService.selectBasicList(cMap); 
			} else {
				// ??????, ??????, ??????????????? ?????? ?????? ?????? 
				list = clDayInfoService.selectList(cMap); 
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	/**
	 * [??????] ???????????? ?????????
	 * 
	 * @param model
	 * @param req
	 * @return
	 */
	@RequestMapping("/bookList/{coDiv}")  
	public String bookList(Model model, HttpServletRequest req, @PathVariable String coDiv) {
		HttpSession session = req.getSession();
		SessionVO sessionVO = (SessionVO) session.getAttribute("msMember");
		String returnPage =  "/book/bookList";
		
		try {
			if (sessionVO == null) return returnPage;

			model.addAttribute("coDiv", coDiv);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return returnPage;
	}
	

	/**
	 * [??????] ???????????? ?????? + ????????? 
	 * 
	 * @param model
	 * @param req
	 * @param coDiv
	 * @return
	 */
	@RequestMapping("/searchBookList/{coDiv}")  
	@ResponseBody
	public List<Map<String, Object>> searchBookList(Model model, HttpServletRequest req, @PathVariable String coDiv) {
		HttpSession session = req.getSession();
		SessionVO sessionVO = (SessionVO) session.getAttribute("msMember");
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			if (req.getParameter("listSize") != null) {
				// listSize : ????????? ?????? ????????????
				int listSize = Integer.parseInt(req.getParameter("listSize").toString());
				// ????????? ??????
				int limit = 5; 
				map.put("limit", limit);
				map.put("offset", listSize);
			}
			
			if (req.getParameter("srchPeriod") != null && !req.getParameter("srchPeriod").equals("") 
					&& req.getParameter("strtDt") != null && req.getParameter("endDt") != null) {
				String strtDt = req.getParameter("strtDt").toString();
				String endDt = req.getParameter("endDt").toString();
				
				map.put("strtDt", strtDt.replace("-", ""));
				map.put("endDt", endDt.replace("-", ""));
				map.put("srchPeriod", req.getParameter("srchPeriod"));
			}
			
			// srchUseYn > ?????? : A, ?????????????????? : Y, ?????????????????? : N
			if (req.getParameter("srchUseYn") != null) map.put("srchUseYn", req.getParameter("srchUseYn"));
			// srchPeriod > ??????1??????, ??????3??????, ??????1???, ????????????
			// if (req.getParameter("srchPeriod") != null) map.put("srchPeriod", req.getParameter("srchPeriod"));
			
			map.put("msNum", sessionVO.getMsNum());
			map.put("coDiv", coDiv);
			list = drBkHistoryService.selectBkHis(map);		
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	
	/**
	 * [??????] ?????? ?????? ????????? 
	 * 
	 * @param model
	 * @param req
	 * @param calcSerialNo
	 * @return
	 */
	@RequestMapping("/bookConfirm")  
	public String bookConfirm(Model model, HttpServletRequest req) {
		HttpSession session = req.getSession();
		SessionVO sessionVO = (SessionVO) session.getAttribute("msMember");
		String returnPage = "/book/bookConfirm";
		
		try {
			if (sessionVO == null) return returnPage;

			Map<String, Object> param = new HashMap<String, Object>();
			param.put("msNum", sessionVO.getMsNum());
			String calcSerialNo = drBkHistoryService.selectCalcSNo(param);
			
			// ?????? ????????????????????? ????????? ?????? 
			param.put("calcSerialNo", calcSerialNo);
			
			// ???????????? 
			List<Map<String, Object>> list = drBkHistoryService.selectBkHis(param);
			model.addAttribute("bk", list.get(0));

		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return returnPage;
	}
	
	/**
	 * ????????? ???????????? ??????
	 * - ???????????? : ??????, ??????, ???????????? ???
	 * 
	 * @param req
	 * @param params
	 * @return
	 */
	@RequestMapping("/bookAvailableTime")
	@ResponseBody
	public List<DrBkTime> bookAvailableTime(HttpServletRequest req, @RequestParam Map<String, Object> params){
		
		List<DrBkTime> timeList = new ArrayList<DrBkTime>();
		
		try {
			timeList = drBkTimeService.bookAvailableTime(params);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return timeList;
	}
	
	/**
	 * ??????(??????)??????  
	 * 
	 * @param model
	 * @param req
	 * @param bInfo
	 * @return
	 */
	@RequestMapping("/bookCancel")  
	@ResponseBody
	public ResultVO bookCancel(Model model, HttpServletRequest req, BookInfoVO bInfo) {

    	ResultVO result = new ResultVO();
		String ipAddr = Utils.getClientIpAddress(req);
    
		try {
			bInfo.setIpAddr(ipAddr);
			result = bookService.bookCancel(bInfo);
		} catch(Exception e) {
			e.printStackTrace();
			result.setCode("9999");
			result.setMessage("????????? ????????? ?????????????????????.");
		}

		return result;
	}
	
	/**
	 * ?????? ????????????
	 * 
	 * @param req
	 * @param params
	 * @return
	 */
	@RequestMapping("/unBkMark")
	@ResponseBody
	public ResultVO unBkMark(HttpServletRequest req, BookInfoVO bookInfo){
		
    	ResultVO result = new ResultVO();
    	
		try {
			result = bookService.unBkMarkLogic(bookInfo);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * ???????????? ????????? ??????
	 * 
	 * @param req
	 * @param params
	 * @return
	 */
	@RequestMapping("/getMark")
	@ResponseBody
	public DrBkMark getMark(HttpServletRequest req, BookInfoVO bkInfo){
		HttpSession session = req.getSession();
		SessionVO sessionVO = (SessionVO) session.getAttribute("msMember");
		DrBkMark drBkMark = new DrBkMark();
		
		try {
			// ???????????? ????????? ??????
			bkInfo.setMsId(sessionVO.getMsId());
			List<DrBkMark> list = drBkMarkService.selectList(bkInfo);
			
			if (list.size() > 0) drBkMark = list.get(0);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return drBkMark;
	}	
	
}

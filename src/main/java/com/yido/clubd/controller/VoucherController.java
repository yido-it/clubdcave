package com.yido.clubd.controller;

import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.yido.clubd.common.utils.ResultVO;
import com.yido.clubd.common.utils.SessionVO;
import com.yido.clubd.common.utils.Utils;
import com.yido.clubd.model.BookInfoVO;
import com.yido.clubd.model.CoPlace;
import com.yido.clubd.model.DrBkHistory;
import com.yido.clubd.model.DrVoucherCode;
import com.yido.clubd.model.VouInfoVO;
import com.yido.clubd.service.CoPlaceService;
import com.yido.clubd.service.DrBkHistoryService;
import com.yido.clubd.service.DrVoucherCodeService;
import com.yido.clubd.service.DrVoucherListService;
import com.yido.clubd.service.DrVoucherSaleService;
import com.yido.clubd.service.DrVoucherUseService;
import com.yido.clubd.service.VoucherService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/voucher")
public class VoucherController {

	@Autowired
	private DrVoucherCodeService drVoucherCodeService;

	@Autowired
	private DrVoucherSaleService drVoucherSaleService;

	@Autowired
	private DrVoucherListService drVoucherListService;
	
	@Autowired
	private DrVoucherUseService drVoucherUseService;
	
	@Autowired
	private VoucherService voucherService;
	
	@Autowired
	private DrBkHistoryService drBkHistoryService;
	
	@Autowired
	private CoPlaceService coPlaceService;
	
	/**
	 * [이용권] 구매/보유내역 페이지
	 * 
	 * @param model
	 * @param req
	 * @return
	 */
	@RequestMapping("/voucherMain/{coDiv}")  
	public String voucherMain(Model model, HttpServletRequest req, @PathVariable String coDiv) {
		HttpSession session = req.getSession();
		SessionVO sessionVO = (SessionVO) session.getAttribute("msMember");
		String returnPage = "/voucher/voucherMain";

		try {
			if (sessionVO == null) return returnPage;
			
			if (req.getParameter("tab") != null) model.addAttribute("tab", req.getParameter("tab"));
			
			// 이용권 조회
			List<DrVoucherCode> list = new ArrayList<DrVoucherCode>();
			DrVoucherCode drVoucherCode = new DrVoucherCode(); 
			list = drVoucherCodeService.selectList(drVoucherCode);
			model.addAttribute("vocList", list);
				
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return returnPage;
	}
	
	/**
	 * [이용권] 구매내역 조회 + 더보기 
	 * 
	 * @param model
	 * @param req
	 * @param coDiv
	 * @return
	 */
	@RequestMapping("/vouSaleList/{coDiv}")  
	@ResponseBody
	public List<Map<String, Object>> vouSaleList(Model model, HttpServletRequest req, @PathVariable String coDiv) {
		HttpSession session = req.getSession();
		SessionVO sessionVO = (SessionVO) session.getAttribute("msMember");
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			if (req.getParameter("listSize") != null) {
				// listSize : 어디서 부터 가져올지
				int listSize = Integer.parseInt(req.getParameter("listSize").toString());
				// 조회할 건수
				int limit = 10; 
				map.put("limit", limit);
				map.put("offset", listSize);
			}
			
			if (req.getParameter("strtDt") != null && req.getParameter("endDt") != null) {
				String strtDt = req.getParameter("strtDt").toString();
				String endDt = req.getParameter("endDt").toString();
				
				map.put("strtDt", strtDt.replace("-", ""));
				map.put("endDt", endDt.replace("-", ""));
			
			}
			
			// srchUseYn > 전체 : A, 사용완료 : Y, 사용중 : N
			if (req.getParameter("srchUseYn") != null) map.put("srchUseYn", req.getParameter("srchUseYn"));
			// srchPeriod > 최근1개월, 최근3개월, 최근1년, 기간설정
			// if (req.getParameter("srchPeriod") != null) map.put("srchPeriod", req.getParameter("srchPeriod"));
			
			map.put("msNum", sessionVO.getMsNum());
			map.put("coDiv", coDiv);
			list = drVoucherSaleService.selectSaleList(map);		
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * [이용권] 결제 페이지
	 * 
	 * @param model
	 * @param req
	 * @param coDiv
	 * @param vcCd
	 * @return
	 */
	@RequestMapping("/voucherPay/{vcCd}")  
	public String voucherPay(Model model, HttpServletRequest req, @PathVariable String vcCd) {
		HttpSession session = req.getSession();
		SessionVO sessionVO = (SessionVO) session.getAttribute("msMember");
		String returnPage = "/voucher/voucherPay";
		
		try {
			if (sessionVO == null) return returnPage;
			
			// 이용권 조회
			new ArrayList<DrVoucherCode>();
			DrVoucherCode drVoucherCode = new DrVoucherCode(); 
			drVoucherCode.setVcCd(vcCd);
			List<DrVoucherCode> list = drVoucherCodeService.selectList(drVoucherCode);
			drVoucherCode = list.get(0);
			model.addAttribute("voc", drVoucherCode);
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("coDiv" , drVoucherCode.getCoDiv());
			
			// 지점 정보 조회 
			List<CoPlace> place = coPlaceService.selectList(map); 
			model.addAttribute("place", place.get(0));
			// end.
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return returnPage;
	}

	/**
	 * 이용권 사용내역
	 * 
	 * @param model
	 * @param req
	 * @return
	 */
	@RequestMapping("/useList")  
	@ResponseBody
	public List<Map<String, Object>> useList(Model model, HttpServletRequest req) {
		List<Map<String, Object>> returnMap = new ArrayList<Map<String, Object>>();

		HttpSession session = req.getSession();
		SessionVO sessionVO = (SessionVO) session.getAttribute("msMember");
		
		try {
			
			if (sessionVO == null || 
					req.getParameter("saleDay") == null || req.getParameter("saleSeq") == null || req.getParameter("vcCd") == null) {
				return returnMap;
			}
			String saleDay = req.getParameter("saleDay");
			int saleSeq = Integer.parseInt(req.getParameter("saleSeq"));
			String vcCd = req.getParameter("vcCd");
			log.info("[이용권 사용내역] {}, {}, {}", saleDay, saleSeq, vcCd);
			
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("saleDay", saleDay);
			param.put("saleSeq", saleSeq);
			param.put("vcCd", vcCd);
			param.put("msNum", sessionVO.getMsNum());
			returnMap = drVoucherUseService.selectUseList(param);
			
		} catch(Exception e) {
			e.printStackTrace();
		}

		return returnMap;
	}
	
	/**
	 * [예약] 이용권 결제 (결제금액 0원인 경우) 
	 * 
	 * @param model
	 * @param req
	 * @param bInfo
	 * @return
	 */
	@RequestMapping("/vPay")  
	@ResponseBody
	public ResultVO vPay(Model model, HttpServletRequest req, BookInfoVO bInfo) {
		HttpSession session = req.getSession();
		SessionVO sessionVO = (SessionVO) session.getAttribute("msMember");
    	ResultVO result = new ResultVO();
		String ipAddr = Utils.getClientIpAddress(req);
    	log.info("[vPay] bInfo: " + bInfo);
    	
		try {
			bInfo.setIpAddr(ipAddr);
			voucherService.vPay(bInfo, sessionVO);
		} catch(Exception e) {
			e.printStackTrace();
			result.setCode("9999");
			result.setMessage("처리중 오류가 발생하였습니다.");
		}

		return result;
	}

	/**
	 * [예약] 이용권 결제취소 (결제금액 0원인 경우) 
	 * 
	 * @param model
	 * @param req
	 * @param bInfo
	 * @return
	 */
	@RequestMapping("/vCancel")  
	@ResponseBody
	public ResultVO vCancel(Model model, HttpServletRequest req, BookInfoVO bInfo) {

    	ResultVO result = new ResultVO();
		String ipAddr = Utils.getClientIpAddress(req);
    	log.info("[vCancel] bInfo: " + bInfo);

		try {
			bInfo.setIpAddr(ipAddr);
			result = voucherService.vCancel(bInfo);
		} catch(Exception e) {
			e.printStackTrace();
			result.setCode("9999");
			result.setMessage("처리중 오류가 발생하였습니다.");
		}

		return result;
	}
	
	/**
	 * [이용권] 결제 완료 페이지
	 * 
	 * @param model
	 * @param req
	 * @return
	 */
	@RequestMapping("/voucherConfirm")  
	public String voucherConfirm(Model model, HttpServletRequest req) {
		HttpSession session = req.getSession();
		SessionVO sessionVO = (SessionVO) session.getAttribute("msMember");
		String returnPage = "/voucher/voucherConfirm";
		
		try {
			if (sessionVO == null) return returnPage;

			Map<String, Object> param = new HashMap<String, Object>();
			param.put("msNum", sessionVO.getMsNum());
			Map<String, Object> sale = drVoucherSaleService.getLastSale(param);
			model.addAttribute("sale", sale);
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return returnPage;
	}
	
	/**
	 * [이용권] 이용권 구매취소  
	 * 
	 * @param model
	 * @param req
	 * @param bInfo
	 * @return
	 */
	@RequestMapping("/cancel")  
	@ResponseBody
	public ResultVO cancel(Model model, HttpServletRequest req, VouInfoVO vInfo) {

    	ResultVO result = new ResultVO();
		String ipAddr = Utils.getClientIpAddress(req);
    	log.info("[cancel] vInfo : " + vInfo);

		try {
			vInfo.setIpAddr(ipAddr);
			result = voucherService.cancel(vInfo);
		} catch(Exception e) {
			e.printStackTrace();
			result.setCode("9999");
			result.setMessage("처리중 오류가 발생하였습니다.");
		}

		return result;
	}
}

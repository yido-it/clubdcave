package com.yido.clubd.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yido.clubd.common.utils.ResultVO;
import com.yido.clubd.common.utils.Utils;
import com.yido.clubd.model.BookInfoVO;
import com.yido.clubd.model.DrBkHistory;
import com.yido.clubd.model.DrVoucherCode;
import com.yido.clubd.service.DrBkHistoryService;
import com.yido.clubd.service.DrVoucherCodeService;
import com.yido.clubd.service.DrVoucherListService;
import com.yido.clubd.service.DrVoucherSaleService;
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
	private VoucherService voucherService;
	
	@Autowired
	private DrBkHistoryService drBkHistoryService;
	
	
	/**
	 * 이용권 구매 페이지
	 * 
	 * @param model
	 * @param req
	 * @return
	 */
	@RequestMapping("/voucherMain")  
	public String voucherMain(Model model, HttpServletRequest req, DrVoucherCode drVoucherCode) {

		DateTimeFormatter dateFm = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		try {
			
			// 이용권 조회
			List<DrVoucherCode> list = new ArrayList<DrVoucherCode>();
			list = drVoucherCodeService.selectList(drVoucherCode);
			model.addAttribute("vocList", list);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return "/voucher/voucherMain";
	}
	
	/**
	 * 이용권 결제 (결제금액 0원) 
	 * 
	 * @param model
	 * @param req
	 * @param bInfo
	 * @return
	 */
	@RequestMapping("/vPay")  
	@ResponseBody
	public ResultVO vPay(Model model, HttpServletRequest req, BookInfoVO bInfo) {

    	ResultVO result = new ResultVO();
		String ipAddr = Utils.getClientIpAddress(req);
    	log.info("[vPay] bInfo: " + bInfo);
    	
		try {
			bInfo.setIpAddr(ipAddr);
			result = voucherService.vPay(bInfo);
		} catch(Exception e) {
			e.printStackTrace();
			result.setCode("9999");
			result.setMessage("처리중 오류가 발생하였습니다.");
		}

		return result;
	}

	/**
	 * 이용권 결제취소 (결제금액 0원) 
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
}

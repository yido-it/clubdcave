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
import com.yido.clubd.model.DrVoucherCode;
import com.yido.clubd.service.DrVoucherCodeService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/voucher")
public class VoucherController {

	@Autowired
	private DrVoucherCodeService drVoucherCodeService;
	
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

}

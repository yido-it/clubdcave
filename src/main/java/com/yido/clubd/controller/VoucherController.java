package com.yido.clubd.controller;

import java.util.ArrayList;
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

import com.yido.clubd.model.DrVoucherCode;
import com.yido.clubd.service.DrMsBasicService;
import com.yido.clubd.service.DrMsMaininfoService;
import com.yido.clubd.service.DrVoucherCodeService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/voucher")
public class VoucherController {

	@Autowired
	private DrVoucherCodeService drVoucherCodeService;
	
	/**
	 * 이용권 조회
	 * 
	 * @param model
	 * @param req
	 * @return
	 */
	@RequestMapping("/voucherList")  
	public String voucherList(Model model, HttpServletRequest req, DrVoucherCode drVoucherCode) {
		
		List<DrVoucherCode> list = new ArrayList<DrVoucherCode>();
		list = drVoucherCodeService.selectList(drVoucherCode);
		model.addAttribute("voucherList", list);
		
		return "/voucher/voucherList";
	}

}

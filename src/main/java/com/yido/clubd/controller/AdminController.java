package com.yido.clubd.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import com.yido.clubd.model.DrBkHistory;
import com.yido.clubd.model.DrBkHistoryTemp;
import com.yido.clubd.model.DrBkOpenTime;
import com.yido.clubd.model.DrBkTime;
import com.yido.clubd.model.DrVoucherCode;
import com.yido.clubd.model.DrVoucherList;
import com.yido.clubd.repository.MemberMapper;
import com.yido.clubd.service.BookService;
import com.yido.clubd.service.ClDayInfoService;
import com.yido.clubd.service.CoPlaceService;
import com.yido.clubd.service.DrBayInfoService;
import com.yido.clubd.service.DrBkHistoryService;
import com.yido.clubd.service.DrBkHistoryTempService;
import com.yido.clubd.service.DrBkOpenTimeService;
import com.yido.clubd.service.DrBkTimeService;
import com.yido.clubd.service.DrCostInfoService;
import com.yido.clubd.service.DrVoucherCodeService;
import com.yido.clubd.service.DrVoucherListService;
import com.yido.clubd.service.DrVoucherSaleService;
import com.yido.clubd.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/admin")
public class AdminController {

	@Autowired
    private MemberMapper memberMapper;
	
	/**
	 * 사용자 로그인 페이지 (관리자가 사용하는 기능) 
	 * 
	 * @param model
	 * @param req
	 * @return
	 */
	@RequestMapping("/userLogin")  
	public String userLogin(HttpServletRequest req) {
		return "/admin/userLogin";
	}
	
	/**
	 * 사용자 로그인 (관리자가 사용하는 기능) 
	 * 
	 * @param model
	 * @param req
	 * @param msNum
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/userLogin/{msNum}")  
	@ResponseBody
	public ResultVO userLogin(HttpSession session, @PathVariable String msNum) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		ResultVO result = new ResultVO();
		
		try {
			param.put("msNum", msNum);
	
			SessionVO sessionVO = memberMapper.selectMsSession(param);
			session.setAttribute("msMember", sessionVO);
			session.setMaxInactiveInterval(30 * 60);
			
		} catch (Exception e) {
			result.setCode("9999");
			e.printStackTrace();
		}
		return result;
	}
}

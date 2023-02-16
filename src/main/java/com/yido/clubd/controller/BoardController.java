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
import com.yido.clubd.model.BBS;
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
import com.yido.clubd.service.BBSService;
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
@RequestMapping("/")
public class BoardController {

	@Autowired
	private BBSService bbsService;
	
	/**
	 * [게시판] 목록 페이지
	 * - bbsType > 게시판종류 (공지사항 : 1, 이벤트 : 2, 프로모션 : 3) 
	 * 
	 * @param model
	 * @param req
	 * @return
	 */
	@RequestMapping("/boardList/{coDiv}/{bbsType}")  
	public String boardList(Model model, HttpServletRequest req, @PathVariable String coDiv, @PathVariable int bbsType) {
		
		model.addAttribute("bbsType", bbsType);
		model.addAttribute("coDiv", coDiv);
		
		return "/board/boardList";
	}
	
	/**
	 * [게시판] 목록 조회 + 더보기 
	 * - bbsType > 게시판종류 (공지사항 : 1, 이벤트 : 2, 프로모션 : 3) 
	 * 
	 * @param model
	 * @param req
	 * @param coDiv
	 * @return
	 */
	@RequestMapping("/searchList/{coDiv}")  
	@ResponseBody
	public List<BBS> searchList(Model model, HttpServletRequest req, @PathVariable String coDiv) {
		
		List<BBS> list = new ArrayList<BBS>();
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			if (req.getParameter("listSize") != null) {
				// listSize : 어디서 부터 가져올지
				int listSize = Integer.parseInt(req.getParameter("listSize").toString());
				// 조회할 건수
				int limit = 5; 
				map.put("limit", limit);
				map.put("offset", listSize);
			}

			map.put("bbsType", req.getParameter("bbsType"));
			map.put("coDiv", coDiv);
			list = bbsService.selectList(map);		
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	/**
	 * [게시판] 상세
	 * - bbsType > 게시판종류 (공지사항 : 1, 이벤트 : 2, 프로모션 : 3) 
	 * 
	 * @param model
	 * @param req
	 * @param coDiv
	 * @return
	 */
	@RequestMapping("/boardView/{idx}")  
	public String boardView(Model model, HttpServletRequest req, @PathVariable int idx) {
		
		BBS bbs = new BBS();
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			
			map.put("idx", idx);
			bbs = bbsService.getNotice(map);		
			model.addAttribute("bbs", bbs);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return "/board/boardView";
	}


}

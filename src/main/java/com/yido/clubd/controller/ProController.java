package com.yido.clubd.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yido.clubd.common.utils.ResultVO;
import com.yido.clubd.common.utils.Utils;
import com.yido.clubd.model.DrMsMaininfo;
import com.yido.clubd.model.DrProSchedule;
import com.yido.clubd.model.ProLicense;
import com.yido.clubd.model.ProNotice;
import com.yido.clubd.service.DrMsMaininfoService;
import com.yido.clubd.service.DrProScheduleService;
import com.yido.clubd.service.ProLicenseService;
import com.yido.clubd.service.ProNoticeService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/pro")
public class ProController {

	@Autowired
	private ProNoticeService proNoticeService;
	
	@Autowired
	private ProLicenseService proLicenseService;
	
	@Autowired
	private DrProScheduleService drProScheduleService;
	
	@Autowired
	private DrMsMaininfoService drMsMaininfoService;
	
	/**
	 * 레슨프로 특이사항 등록
	 * 
	 * @param req
	 * @param proNotice
	 * @return
	 */
	@RequestMapping("/insertNotice")
	@ResponseBody
	public Map<String, Object> insertNotice(HttpServletRequest req, ProNotice proNotice){

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			proNoticeService.insertProNotice(proNotice);
			map.put("result",  true);
		} catch(Exception e) {
			e.printStackTrace();
			map.put("result",  false);
			map.put("message", "등록중 오류가 발생하였습니다.");
		}
		
		return map;
	}
	
	/**
	 * 레슨프로 특이사항 수정
	 * 
	 * @param req
	 * @param proNotice
	 * @return
	 */
	@RequestMapping("/updateNotice")
	@ResponseBody
	public Map<String, Object> updateNotice(HttpServletRequest req, ProNotice proNotice){

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			proNoticeService.updateProNotice(proNotice);
			map.put("result",  true);
		} catch(Exception e) {
			e.printStackTrace();
			map.put("result",  false);
			map.put("message", "수정중 오류가 발생하였습니다.");
		}
		
		return map;
	}
	
	/**
	 * 레슨프로 자격사항 등록
	 * 
	 * @param req
	 * @param proLicense
	 * @return
	 */
	@RequestMapping("/insertLicense")
	@ResponseBody
	public Map<String, Object> insertLicense(HttpServletRequest req, ProLicense proLicense){

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			proLicenseService.insertProLicense(proLicense);
			map.put("result",  true);
		} catch(Exception e) {
			e.printStackTrace();
			map.put("result",  false);
			map.put("message", "등록중 오류가 발생하였습니다.");
		}
		
		return map;
	}
	
	/**
	 * 레슨프로 자격사항 수정
	 * 
	 * @param req
	 * @param proLicense
	 * @return
	 */
	@RequestMapping("/updateLicense")
	@ResponseBody
	public Map<String, Object> updateLicense(HttpServletRequest req, ProLicense proLicense){

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			proLicenseService.updateProLicense(proLicense);
			map.put("result",  true);
		} catch(Exception e) {
			e.printStackTrace();
			map.put("result",  false);
			map.put("message", "수정중 오류가 발생하였습니다.");
		}
		
		return map;
	}
	
	/**
	 * 레슨프로 일정 등록
	 * 
	 * @param req
	 * @param proLicense
	 * @return
	 */
	@RequestMapping("/insertSchedule")
	@ResponseBody
	public Map<String, Object> insertSchedule(HttpServletRequest req, DrProSchedule drProSchedule){

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			drProScheduleService.insertDrProSchedule(drProSchedule);
			map.put("result",  true);
		} catch(Exception e) {
			e.printStackTrace();
			map.put("result",  false);
			map.put("message", "등록중 오류가 발생하였습니다.");
		}
		
		return map;
	}
	
	/**
	 * 레슨프로 일정 수정
	 * 
	 * @param req
	 * @param proLicense
	 * @return
	 */
	@RequestMapping("/updateSchedule")
	@ResponseBody
	public Map<String, Object> updateSchedule(HttpServletRequest req, DrProSchedule drProSchedule){

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			drProScheduleService.updateDrProSchedule(drProSchedule);
			map.put("result",  true);
		} catch(Exception e) {
			e.printStackTrace();
			map.put("result",  false);
			map.put("message", "등록중 오류가 발생하였습니다.");
		}
		
		return map;
	}
	
	/**
	 * 레슨프로 목록 페이지
	 * 
	 */	
	@RequestMapping("/proMain")  
	public String goProMain() {		
		return "/pro/proMain";
	}
	
	/**
	 * 레슨프로 목록 페이지
	 * 
	 * @param model
	 * @param req
	 * @return
	 */	
	@RequestMapping("/getProList")
	@ResponseBody
	public Map<String, Object> getProList(HttpServletResponse response) {
		Map<String, Object> map = new HashMap<>();		
		
		try {
			List<DrMsMaininfo> drMsMaininfoList = drMsMaininfoService.selectProList();
			
			if(drMsMaininfoList.isEmpty()) {
				throw new Exception("조회 가능한 프로가 없습니다");
			}
			map.put("result", true);
			map.put("proList", drMsMaininfoList);			
		} catch (Exception e) {
			map.put("result", false);
			map.put("message", e.getMessage());
		}
		return map;
	}
	
	/**
	 * 레슨프로 상세 페이지
	 * 
	 * @param model
	 * @return
	 */	
	@RequestMapping("/proDetail")  
	public String goProDetail(Model model, HttpServletRequest req, @RequestParam String msNum) {
		Map<String, Object> map = new HashMap<>();
		map.put("msNum", msNum);
		
		DrMsMaininfo drMsMaininfo = drMsMaininfoService.selectDrMsMaininfo(map);
		List<ProNotice> proNoticeList = proNoticeService.selectProNoticeList(map);
		List<ProNotice> proImageList = proNoticeService.selectProImageList(map);
		
		model.addAttribute("proInfo", drMsMaininfo);
		model.addAttribute("proNoticeList", proNoticeList);
		model.addAttribute("proPictureList", proImageList);
		
		return "/pro/proDetail";
	}
}

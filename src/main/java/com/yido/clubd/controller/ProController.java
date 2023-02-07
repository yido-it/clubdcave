package com.yido.clubd.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.yido.clubd.common.service.CommonService;
import com.yido.clubd.common.utils.SessionVO;
import com.yido.clubd.common.utils.Utils;
import com.yido.clubd.model.CdCommon;
import com.yido.clubd.model.CoPlace;
import com.yido.clubd.model.DrMsCoInfo;
import com.yido.clubd.model.DrProSchedule;
import com.yido.clubd.model.MemberVO;
import com.yido.clubd.model.ProVO;
import com.yido.clubd.service.CoPlaceService;
import com.yido.clubd.service.DrMsCoInfoService;
import com.yido.clubd.service.DrProScheduleService;
import com.yido.clubd.service.MemberService;
import com.yido.clubd.service.ProService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/pro")
public class ProController {

	@Autowired
	private ProService proService;	
	
	@Autowired
	private DrProScheduleService drProScheduleService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private CoPlaceService coPlaceService;
	
	@Autowired
	private DrMsCoInfoService drMsCoInfoService;
	
	/**
	 * 레슨프로 목록 페이지
	 * 
	 */	
	@RequestMapping("/proMain")  
	public String goProMain(Model model) {
		try {
			List<MemberVO> drMsMaininfoList = memberService.selectProList();
			
			if(drMsMaininfoList.isEmpty()) {
				throw new Exception("조회 가능한 프로가 없습니다");
			}
			model.addAttribute("result", true);			
			model.addAttribute("proList", drMsMaininfoList);			
		} catch (Exception e) {
			model.addAttribute("result", false);
			model.addAttribute("message", e.getMessage());
		}
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
			List<MemberVO> drMsMaininfoList = memberService.selectProList();
			
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
		
		MemberVO member = memberService.selectMember(map);		
		
		List<ProVO> proNoticeList = proService.selectProNoticeList(map);
		List<ProVO> proLicList = proService.selectProLicenseList(map);
		List<ProVO> proImageList = proService.selectProImageList(map);
		
		model.addAttribute("proInfo", member);
		model.addAttribute("proNoticeList", proNoticeList);
		model.addAttribute("proLicList", proLicList);
		model.addAttribute("proImageList", proImageList);
		
		return "/pro/proDetail";
	}
	
	/**
	 * 레슨프로 수정 페이지
	 * 
	 * @param model
	 * @return
	 */	
	@RequestMapping("/proForm")  
	public String goProForm(Model model, HttpSession session) {
		CdCommon cdCommon = new CdCommon();
		cdCommon.setCoDiv("001");
		cdCommon.setCdDivision("025");
		
		List<CdCommon> jobList = commonService.getCommonCodeList(cdCommon);
		List<CoPlace> placeList = coPlaceService.selectPlaceList();
		
		cdCommon.setCdDivision("018");
		List<CdCommon> licenseList = commonService.getCommonCodeList(cdCommon);
		
		SessionVO member = (SessionVO)session.getAttribute("msMember");
		String msNum = member.getMsNum();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("msNum", msNum);
		
		List<MemberVO> carList = memberService.selectMemberCarList(msNum);
		List<ProVO> noticeList = proService.selectProNoticeList(map);
		/*
		 * for(ProVO notice : noticeList) { String proRemark =
		 * notice.getProRemark().replace("<br/>", "\r\n");
		 * notice.setProRemark(proRemark);
		 * 
		 * }
		 */
		List<ProVO> proLicList = proService.selectProLicenseList(map);
		DrMsCoInfo drMsInfo = drMsCoInfoService.selectFirstPick(msNum);
		
		/* 공통 코드 */
		model.addAttribute("jobList", jobList);
		model.addAttribute("placeList", placeList);
		model.addAttribute("licenseList", licenseList);
		
		/* 프로 프로필 정보 */
		model.addAttribute("carList", carList);
		model.addAttribute("noticeList", noticeList);
		model.addAttribute("proLicList", proLicList);
		if(drMsInfo != null) {
			model.addAttribute("msFirstPick", drMsInfo.getCoDiv());			
		}else {			
			model.addAttribute("msFirstPick", "");			
		}		
		
		return "/pro/proForm";
	}
	
	@RequestMapping("/saveProForm")
	@ResponseBody
	public Map<String, Object> saveProForm(HttpServletRequest req, Map<String, Object> params){

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			memberService.updateMember(params);
			memberService.saveMemberCar(params);
			memberService.saveFirstPick(params);			
			
			proService.updateProNotice((ProVO)Utils.convertMaptoVO(params));
			proService.updateProLicense((ProVO)Utils.convertMaptoVO(params));
			map.put("result",  true);
		} catch(Exception e) {
			e.printStackTrace();
			map.put("result",  false);
			map.put("message", "수정중 오류가 발생하였습니다.");
		}
		
		return map;
	}
	
		@RequestMapping("/pro/uploadProImage")
	@ResponseBody
	public HashMap<String, Object> uploadProImage(MultipartHttpServletRequest mreq, HttpServletRequest req, MemberVO memberVO) 
		throws IllegalStateException, IOException {
	
			HashMap<String, Object> map = new HashMap<String, Object>();

			String yearMonth = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMM"));			
			String path = "001" + "/" + "picture" + "/" + yearMonth;
			memberVO.setMsImgData(path);
			
			try {
				//memberService.uploadMemberImage(memberVO, mreq);
				map.put("result",  true);

			} catch (Exception e) {
					map.put("result",  false);
				    map.put("message", "파일 업로드중 오류가 발생하였습니다.");
					e.printStackTrace();
			}
			return map;	
	}
	
	@RequestMapping("/proGallery")  
	public String goProGallery(Model model, HttpSession session) {
		return "/pro/proGallery";
	}
	
	/**
	 * 레슨프로 특이사항 등록
	 * 
	 * @param req
	 * @param proVO
	 * @return
	 */
	@RequestMapping("/insertProNotice")
	@ResponseBody
	public Map<String, Object> insertProNotice(HttpServletRequest req, ProVO proVO){

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			proService.insertProNotice(proVO);
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
	 * @param proVO
	 * @return
	 */
	@RequestMapping("/updateProNotice")
	@ResponseBody
	public Map<String, Object> updateProNotice(HttpServletRequest req, ProVO proVO){

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			proService.updateProNotice(proVO);
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
	 * @param proVO
	 * @return
	 */
	@RequestMapping("/insertProLicense")
	@ResponseBody
	public Map<String, Object> insertProLicense(HttpServletRequest req, ProVO proVO){

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			proService.insertProLicense(proVO);
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
	 * @param proVO
	 * @return
	 */
	@RequestMapping("/updateProLicense")
	@ResponseBody
	public Map<String, Object> updateProLicense(HttpServletRequest req, ProVO proVO){

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			proService.updateProLicense(proVO);
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
	 * @param proVO
	 * @return
	 */
	@RequestMapping("/inserProSchedule")
	@ResponseBody
	public Map<String, Object> insertProSchedule(HttpServletRequest req, DrProSchedule drProSchedule){

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
	 * @param proVO
	 * @return
	 */
	@RequestMapping("/updateProSchedule")
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
	
}

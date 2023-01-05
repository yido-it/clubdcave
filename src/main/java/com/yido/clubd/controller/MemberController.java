package com.yido.clubd.controller;

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

import com.google.gson.JsonObject;
import com.yido.clubd.common.utils.Globals;
import com.yido.clubd.common.utils.KakaoRestAPI;
import com.yido.clubd.common.utils.SessionVO;
import com.yido.clubd.model.DrMsBasic;
import com.yido.clubd.model.DrMsMaininfo;
import com.yido.clubd.service.DrMsBasicService;
import com.yido.clubd.service.DrMsMaininfoService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private DrMsMaininfoService drMsMaininfoService;
	
	@Autowired
	private DrMsBasicService drMsBasicService;
	
	/**
	 * 회원가입 첫 페이지
	 * 
	 * @param model
	 * @param req
	 * @return
	 */
	@RequestMapping("/join")  
	public String bookMain(Model model, HttpServletRequest req) {
		return "/member/join";
	}
	
	/**
	 * 회원가입 > 기본정보 입력 페이지
	 * 
	 * @param model
	 * @param req
	 * @return
	 */
	@RequestMapping("/join02")  
	public String testJoin(Model model, HttpServletRequest req) {
		return "/member/join02";
	}
	
	/**
	 * 회원가입 > 기본정보 등록
	 *  
	 * @param req
	 * @param drMsMaininfo
	 * @return
	 */
	@RequestMapping("/insertMainInfo")
	@ResponseBody
	public Map<String, Object> insertMainInfo(HttpServletRequest req, DrMsMaininfo drMsMaininfo){

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			drMsMaininfoService.insertDrMsMaininfo(drMsMaininfo);
			map.put("result",  true);
		} catch(Exception e) {
			e.printStackTrace();
			map.put("result",  false);
			map.put("message", "가입중 오류가 발생하였습니다.");
		}
		
		return map;
	}
	
	/**
	 * 회원추가정보 등록
	 * 
	 * @param req
	 * @param drMsBasic
	 * @return
	 */
	@RequestMapping("/insertBasicInfo")
	@ResponseBody
	public Map<String, Object> insertBasicInfo(HttpServletRequest req, DrMsBasic drMsBasic){

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			drMsBasicService.insertDrMsBasic(drMsBasic);
			map.put("result",  true);
		} catch(Exception e) {
			e.printStackTrace();
			map.put("result",  false);
			map.put("message", "추가정보 등록중 오류가 발생하였습니다.");
		}
		
		return map;
	}
	
	/**
	 * 회원추가정보 수정
	 * 
	 * @param req
	 * @param drMsBasic
	 * @return
	 */
	@RequestMapping("/updateBasicInfo")
	@ResponseBody
	public Map<String, Object> updateBasicInfo(HttpServletRequest req, DrMsBasic drMsBasic){

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			drMsBasicService.updateDrMsBasic(drMsBasic);
			map.put("result",  true);
		} catch(Exception e) {
			e.printStackTrace();
			map.put("result",  false);
			map.put("message", "추가정보 수정중 오류가 발생하였습니다.");
		}
		
		return map; 
	}
	
	/**
	 * 네이버 로그인 동의하기 후 호출되는 함수
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
    @RequestMapping(value = "/succNaverLogin")
    public String getNaverCallbackPage(HttpServletRequest request, HttpServletResponse response) {
    	// 프로필 조회 페이지로 이동
        return "/member/succNaverLogin";
    }
    
    /**
     * 카카오 로그인 동의하기 후 호출되는 함수
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/oauth_kakao")
    public String oauthKakao(@RequestParam(value = "code", required = false) String code, Model model) throws Exception {

            String access_Token = KakaoRestAPI.getAccessToken(code);
            HashMap<String, Object> userInfo = KakaoRestAPI.getKakaoUserInfo(access_Token);

            model.addAttribute("type", "KAKAO");
            model.addAttribute("name", userInfo.get("nickname"));
            
            return "/member/succKakaoLogin";
    }
    
    /**
     * 소셜 회원가입
     * @param request
     * @param response
     * @param session
     * @param params ( 로그인타입 : msLoginCd, 이름 : msName )
     * @return
     */
    @RequestMapping(value = "/actionLoginForSocial")
    @ResponseBody
    public Map<String, Object> actionLoginForSocial(HttpServletRequest request, HttpServletResponse response, HttpSession session, @RequestParam Map<String, Object> params) {

		Map<String, Object> map = new HashMap<String, Object>();

    	try {
    		params.put("ipAddr", Globals.serverIpAddress);

    		if(drMsMaininfoService.duplicateCheckSocialId(params) == 0) {
    			log.info("최초가입자 간편회원가입 페이지로 이동");
    			// 최초가입자 -> 간편회원가입 페이지로 이동
        		map.put("result",  "1000");
				return map;
    		} else {
    			// 기존가입자 -> 로그인처리
    			String msNum = drMsMaininfoService.getMsNum(params);

        		params.put("msNum", msNum);

        		SessionVO sessionVO = drMsMaininfoService.getMsSession(params);

        		session.setAttribute("msMember", sessionVO);
        		// session.setAttribute("quick", "Y");
        		session.setMaxInactiveInterval(30 * 60);
        		// result.setData(msNum);

        		//로그인 log - USER_LOGIN_LOG 테이블 ?
        
        		/*
        		String app = (String) session.getAttribute("app");
        		String platform = null;
        		String ua = null;

        		if(params.get("ua") != null) {
					ua = params.get("ua").toString();
				}else {
					ua = request.getHeader("user-agent");
					if (ua.indexOf("Android") != -1) {
						ua = "Android";
					} else if( ua.indexOf("iPad") != -1 || ua.indexOf("iPhone") != -1 || ua.indexOf("iOS") != -1 || ua.indexOf("MAC") != -1 ) {
						ua = "iPhone";
					} else if( ua.indexOf("Windows") != -1) {
						ua = "windows";
					} else {
						ua = request.getHeader("user-agent");
					}
				}
        		
				if(app != null) {
					platform = "APP";
				}else if (ua.indexOf("iPhone") != -1 || ua.indexOf("iPad") != -1 ||
				   ua.indexOf("Android") != -1  || ua.indexOf("BlackBerry") != -1  ||
				   ua.indexOf("sony") != -1  || ua.indexOf("Mobile") != -1 ) {
					  platform = "MOBILE";
				}else {
					platform = "PC";
				}

				params.put("msNum", msNum);
				params.put("platform", platform);
				params.put("userAgent", ua);
				params.put("loginAuto", "N");
				if (sessionVO !=null) {
					params.put("inputStaff", sessionVO.getMsId());
				}
				params.put("ipAddr", Utils.getClientIpAddress(request));

				commonService.loginLog(params);*/
        		

    			map.put("result",  "0000");
    		}
    	} catch (Exception e) {
    		log.info(e.getMessage());
    		map.put("result",  "9999");
			map.put("message", "오류가 발생하였습니다.");
    	}
    	
		return map;
    }
    
    /**
     * 간편 회원가입 페이지
     * 
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/join05")
    public String join05(HttpServletRequest request, HttpServletResponse response, Model model) {
    	
    	model.addAttribute("msLoginCd", request.getParameter("msLoginCd"));
    	model.addAttribute("msName", request.getParameter("msName"));
    	
        return "/member/join05";
    }
    
}

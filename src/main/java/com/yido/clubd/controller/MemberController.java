package com.yido.clubd.controller;

import java.io.IOException;
import java.util.HashMap;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yido.clubd.common.service.CommonService;
import com.yido.clubd.common.utils.Globals;
import com.yido.clubd.common.utils.KakaoRestAPI;
import com.yido.clubd.common.utils.SessionVO;
import com.yido.clubd.common.utils.Utils;
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
	
	@Autowired
	private CommonService commonService;
	
	/**
	 * 회원가입 첫 페이지 (약관 동의)
	 * 
	 * @param model
	 * @param req
	 * @return
	 */
	@RequestMapping("/join01")  
	public String goJoin01(HttpServletRequest req, Model model) {
		model.addAttribute("msLoginCd", req.getParameter("msLoginCd"));
		return "/member/join01";
	}
	
	/**
	 * 회원가입 > 기본정보 입력
	 * 
	 * @param model
	 * @param req
	 * @return
	 */
	@RequestMapping("/join02")  
	public String goJoin02(HttpServletRequest req, Model model) {
		model.addAttribute("msMktAgreeYn", req.getParameter("msMktAgreeYn"));
		model.addAttribute("msLoginCd", req.getParameter("msLoginCd"));
		return "/member/join02";
	}
	
	/**
	 * 회원가입 > 선택사항 입력
	 * 
	 * @param model
	 * @param req
	 * @return
	 */
	@RequestMapping("/join03")  
	public String goJoin03(Model model, HttpServletRequest req) {
		return "/member/join03";
	}
	
	/**
	 * 회원가입 > 가입 완료 메시지
	 * 
	 * @param model
	 * @param req
	 * @return
	 */
	@RequestMapping("/join04")  
	public String goJoin04(Model model, HttpServletRequest req) {
		return "/member/join04";
	}
	
	@RequestMapping("/checkIdExist")
	@ResponseBody
	public Map<String, Object> checkIdExist (@RequestParam(required = false) String msId) {
		Map<String, Object> map = new HashMap<>();
		map.put("msId", msId);
		
		if (drMsMaininfoService.findDuplicateUser(map) != null) {
			map.put("message",  "이미 가입된 아이디입니다.");
			map.put("result", false);			
		} else {
			map.put("message",  "사용 가능한 아이디입니다.");
			map.put("result", true);
		}
		return map;
	}
	
	@RequestMapping(value = "/verifybyCode")
	@ResponseBody
	public Map<String, Object> verifybyCode(@RequestParam Map<String, Object> params) {
		Map<String, Object> map = new HashMap<>();
		String[] msPhone = ((String)params.get("msPhone")).split("-");
		
		params.put("msFirstPhone1", msPhone[0]);
		params.put("msMidPhone1", msPhone[1]);
		params.put("msLastPhone1", msPhone[2]);
				
		try {
			if(drMsMaininfoService.findDuplicateUser(params) != null) {				
				map.put("result", false);
				map.put("message", "이미 가입된 번호입니다.");				
			} else {
				//commonService.sendSms(params);
				map.put("result", true);
				map.put("message", "문자로 인증번호를 전송했습니다.");
			}
		} catch (Exception e) {
			map.put("message", "인증번호 전송에 실패했습니다");
		}
		return map;
	}	
	
	/**
	 * 회원가입 > 기본정보 입력
	 *  
	 * @param req
	 * @param drMsMaininfo
	 * @return
	 */
	@RequestMapping("/doSignUp")
	@ResponseBody
	public Map<String, Object> doSignUp (HttpServletRequest req, HttpSession session, DrMsMaininfo drMsMaininfo){
		
		drMsMaininfo.setIpAddr(Globals.serverIpAddress);
		Map<String, Object> map = new HashMap<String, Object>();
		log.debug(session.getAttribute("msMember").toString());
		try {
			drMsMaininfoService.insertDrMsMaininfo(drMsMaininfo);
			this.loginUserInfo(session, Utils.changeVotoMap(drMsMaininfo));
			
			map.put("result",  true);
			map.put("userInfo",  drMsMaininfo);
		} catch(Exception e) {
			e.printStackTrace();
			map.put("result",  false);
			map.put("message", "가입중 오류가 발생하였습니다.");
		}
		
		return map;
	}
	
	/**
     * 일반 로그인
     * @param request
     * @param response
     * @param session
     * @param params
     * @return
	 * @throws JsonProcessingException 
     */
    @RequestMapping(value = "/doLogin")
    @ResponseBody
    public String doLogin (HttpServletRequest req, HttpSession session
    		, @RequestParam Map<String, Object> params) throws JsonProcessingException {

		Map<String, Object> map = new HashMap<String, Object>();
		params.put("ipAddr", Globals.serverIpAddress);

    	try {
    		DrMsMaininfo userInfo = drMsMaininfoService.loginUser(params);    		
    		if(userInfo == null) {
    			// 유저 정보 불일치
        		map.put("resultCode",  "100");
    		} else {
    			this.loginUserInfo(session, params);        		
        		map.put("resultCode",  "200");
        		map.put("userInfo",  userInfo);
        		
    		}
    	} catch (Exception e) {
    		log.debug(e.getMessage());
    		map.put("resultCode",  "999");
			map.put("message", "오류가 발생하였습니다.");
    	}
    	
    	ObjectMapper mapper = new ObjectMapper();
    	return mapper.writeValueAsString(map);
    }
    
    /**
     * 소셜 로그인
     * @param request
     * @param response
     * @param session
     * @param params ( 로그인타입 : msLoginCd, 이름 : msName )
     * @return
     */
    @RequestMapping(value = "/doLoginForSocial")
    @ResponseBody
    public Map<String, Object> doLoginForSocial (HttpServletRequest req , HttpSession session
    		, @RequestParam Map<String, Object> params) {

		Map<String, Object> map = new HashMap<String, Object>();
    	try {
    		params.put("ipAddr", Globals.serverIpAddress);
    		
    		if(drMsMaininfoService.findDuplicateUser(params) == null) {
    			// 최초가입 -> 간편회원가입 페이지로 이동
    			session.setAttribute("joinInfo", params);
    			session.setAttribute("msLoginCd", params.get("msLoginCd"));
        		session.setMaxInactiveInterval(30 * 60);
        		
        		map.put("resultCode",  "100");
				return map;
				
    		} else {
    			// 기존가입 -> 로그인처리
    			this.loginUserInfo(session, params);  

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
        		

    			map.put("resultCode",  "200");
    		}
    	} catch (Exception e) {
    		log.debug(e.getMessage());
    		map.put("resultCode",  "999");
			map.put("message", "오류가 발생하였습니다.");
    	}
    	
		return map;
    }
    
    /**
	 * 네이버 로그인 동의하기 콜백 함수
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
    @RequestMapping(value = "/succNaverLogin")
    public String goNaverCallbackPage(HttpServletRequest request, HttpServletResponse response) {
    	// 프로필 조회 페이지로 이동
        return "/member/succNaverLogin";
    }
    
    /**
     * 카카오 로그인 동의하기 콜백 함수
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/succKakaoLogin")
    public String oauthKakao(@RequestParam(value = "code", required = false) String code, HttpServletRequest req, Model model) throws Exception {
            try {
            	String access_Token = KakaoRestAPI.getAccessToken(code);
            	HashMap<String, Object> joinInfo = KakaoRestAPI.getKakaoUserInfo(access_Token);
            	model.addAttribute("joinInfo", joinInfo);               
            } catch(Exception e) {
            	log.debug(e.getMessage());
            	model.addAttribute("errorMessage", "카카오 가입 정보를 불러올 수 없습니다.");  
            }
            return "/member/succKakaoLogin"; 
    }
    
	public void loginUserInfo(HttpSession session, Map<String, Object> params) {
		String msNum = drMsMaininfoService.getMsNum(params);
		params.put("msNum", msNum);
		SessionVO sessionVO = drMsMaininfoService.getMsSession(params);			
		session.setAttribute("msMember", sessionVO);
		session.setMaxInactiveInterval(30 * 60);
		
		// 로그인 시간 기록 (아직)
		//자동 로그인 (아직)
		//로그인 log (아직)		
	}
    
    @RequestMapping(value = "/doLogout")
    @ResponseBody
    public Map<String, Object> doLogout (HttpServletRequest req, HttpSession session){
    	Map<String, Object> map = new HashMap<String, Object>();

		session.removeAttribute("msMember");
		
		map.put("result", true);
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
}

package com.yido.clubd.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yido.clubd.common.service.CommonService;
import com.yido.clubd.common.utils.Globals;
import com.yido.clubd.common.utils.KakaoRestAPI;
import com.yido.clubd.common.utils.ResultVO;
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
	@RequestMapping("/agree")  
	public String goAgree(HttpServletRequest req, Model model) {
		model.addAttribute("msLoginCd", req.getParameter("msLoginCd"));
		return "/member/agree";
	}
	
	/**
	 * 회원가입 > 기본정보 입력
	 * 
	 * @param model
	 * @param req
	 * @return
	 */
	@RequestMapping("/memberForm")  
	public String goMemberForm(HttpServletRequest req, Model model) {
		model.addAttribute("msMktAgreeYn", req.getParameter("msMktAgreeYn"));
		model.addAttribute("msLoginCd", req.getParameter("msLoginCd"));
		return "/member/memberForm";
	}
	
	/**
	 * 회원가입 > 선택사항 입력
	 * 
	 * @param model
	 * @param req
	 * @return
	 */
	@RequestMapping("/memberAddForm")  
	public String goMemberAddForm(Model model, HttpServletRequest req) {
		return "/member/memberAddForm";
	}
	
	/**
	 * 회원가입 > 가입 완료 메시지
	 * 
	 * @param model
	 * @param req
	 * @return
	 */
	@RequestMapping("/joinComplete")  
	public String goJoinComplete(Model model, HttpServletRequest req) {
		return "/member/joinComplete";
	}
	
	@RequestMapping("/checkIdExist")
	@ResponseBody
	public Map<String, Object> checkIdExist (@RequestParam(required = false) String msId) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("msId", msId);
		
		if (drMsMaininfoService.selectFindUser(map) != null) {
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
			if(drMsMaininfoService.selectFindUser(params) != null) {				
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
	 * @throws Exception 
	 */
	@RequestMapping("/doSignUp")
	@ResponseBody
	public Map<String, Object> doSignUp (HttpServletRequest req, HttpServletResponse res, HttpSession session, DrMsMaininfo drMsMaininfo) throws Exception{
		
		Map<String, Object> map = new HashMap<String, Object>();
		drMsMaininfo.setIpAddr(Globals.serverIpAddress);
		log.debug(String.valueOf(session.getAttribute("msMember")));
		
		if(drMsMaininfo.getMsLoginCd() != "APP") {
			map.put("msId", drMsMaininfo.getMsId());
			if(drMsMaininfoService.selectMsNum(map) != null) {				
				map.put("result",  false);
				map.put("message", "이미 소셜 아이디로 가입된 회원입니다.");
				return map;
			}
		}
		try {
			drMsMaininfoService.insertDrMsMaininfo(drMsMaininfo);
			drMsMaininfoService.loginUserInfo(req, res, session, Utils.changeVotoMap(drMsMaininfo));
			
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
    public Map<String, Object> doLogin (HttpServletRequest req, HttpServletResponse res, HttpSession session
    		, @RequestParam Map<String, Object> params) throws JsonProcessingException {

		Map<String, Object> map = new HashMap<String, Object>();
		params.put("ipAddr", Globals.serverIpAddress);
		params.put("msLoginCd", "APP");

    	try {
    		DrMsMaininfo userInfo = drMsMaininfoService.selectLoginUser(params);    		
    		if(userInfo == null) {
    			// 유저 정보 불일치
        		map.put("result",  false);
        		map.put("message",  "해당하는 사용자 정보가 없습니다.");
    		} else {
    			drMsMaininfoService.loginUserInfo(req, res, session, params);  
    			  		
    			map.put("result",  true);
        		map.put("userInfo",  userInfo);
        		
    		}
    	} catch (Exception e) {
    		map.put("result",  false);
    		map.put("message",  "로그인 중 에러가 발생했습니다.");
    	}
    	
    	return map;
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
    public Map<String, Object> doLoginForSocial (HttpServletRequest req, HttpServletResponse res, HttpSession session
    		, @RequestParam Map<String, Object> params) {

		Map<String, Object> map = new HashMap<String, Object>();
    	try {
    		params.put("ipAddr", Globals.serverIpAddress);
    		
    		if(drMsMaininfoService.selectFindUser(params) == null) {
    			// 최초가입 -> 간편회원가입 페이지로 이동
    			session.setAttribute("joinInfo", params);
    			session.setAttribute("msLoginCd", params.get("msLoginCd"));
        		session.setMaxInactiveInterval(30 * 60);
        		
        		map.put("resultCode",  "1000");
				return map;
				
    		} else {
    			// 기존가입 -> 로그인처리
    			drMsMaininfoService.loginUserInfo(req, res, session, params);
    			map.put("resultCode",  "0000");
    		}
    	} catch (Exception e) {
    		log.debug(e.getMessage());
    		map.put("resultCode",  "9999");
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
        	model.addAttribute("message", "카카오 가입 정보를 불러올 수 없습니다.");  
        }
        return "/member/succKakaoLogin"; 
    }
    
	@RequestMapping("/memberModify")  
	public String goMemberModify(Model model, HttpServletRequest req) {
		return "/member/memberModify";
	}	
	
	@RequestMapping("/saveMemberModify")
	@ResponseBody
	public Map<String, Object> saveMemberModify(HttpSession session, @RequestParam Map<String, Object> params) {
		Map<String, Object> map = new HashMap<String, Object>();
		SessionVO member = new SessionVO();
		
		try {
			if(params.containsKey("newMsPassword")) {
				DrMsMaininfo userInfo = drMsMaininfoService.selectLoginUser(params);    		
				if(userInfo == null) {
					// 유저 정보 불일치
		    		map.put("result",  false);
		    		map.put("message",  "비밀번호가 일치하지 않습니다.");
		    		return map;
				}
			}
			member = drMsMaininfoService.updateDrMsMaininfo(params);
    		session.setAttribute("msMember", member);
			map.put("result", true);
    	} catch (Exception e) {
    		map.put("result", false);
    		map.put("message", "수정 중 오류가 발생했습니다.");
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
	 * 아이디/비밀번호 찾기
	 * 
	 * @param model
	 * @param req
	 * @return
	 */
	@RequestMapping("/find")  
	public String goFind(Model model, HttpServletRequest req) {
		return "/member/find";
	}
	
	@RequestMapping(value = "/doFindId")
	public void doFindId(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam Map<String, Object> params) {
			ResultVO result = new ResultVO();

		try {
			DrMsMaininfo drMsMaininfo = drMsMaininfoService.selectFindUser(params);

			if (drMsMaininfo == null) {
				throw new Exception("등록 된 아이디가 없습니다.");
			}
			
			if(drMsMaininfo.getMsLoginCd().equals("KAKAO")) {
				throw new Exception("KAKAO 간편 로그인 회원 가입자입니다. 간편로그인으로 로그인 부탁드립니다.");
			}

			if(drMsMaininfo.getMsLoginCd().equals("NAVER")) {
				throw new Exception("NAVER 간편 로그인 회원 가입자입니다. 간편로그인으로 로그인 부탁드립니다.");
			}
			params.put("ipAddr", Utils.getClientIpAddress(request));
			params.put("tplCd", Utils.getProperties("Globals.findId.tplCd", "00001"));
			params.put("msId", drMsMaininfo.getMsId());

			// commonService.sendSms(params); (나중)
		} catch (Exception e) {
			result.setCode("9999");
			result.setMessage(e.getMessage());
		} finally {
			// 실패(9999) 또는 기본(0000)를 보냄
			Utils.sendData(response, Utils.makeJsonString(result));
		}
	}

	@RequestMapping(value = "/doFindPw")
	public void doFindPw(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam Map<String, Object> params) {
		ResultVO result = new ResultVO();

		try {
			DrMsMaininfo drMsMaininfo = drMsMaininfoService.selectFindUser(params);

			if (drMsMaininfo == null) {
				throw new Exception("등록 된 정보가 없습니다.");
			}

			StringBuffer newMsPassword = new StringBuffer();
			Random rnd = new Random();
			for (int i = 0; i < 6; i++) {
				int rIndex = rnd.nextInt(2);
				switch (rIndex) {
				case 0:
					newMsPassword.append((char) ((int) (rnd.nextInt(26)) + 97));
					break;
				case 1:
					newMsPassword.append((rnd.nextInt(10)));
					break;
				}
			}

			params.put("msNum", drMsMaininfo.getMsNum());
			params.put("newMsPassword", newMsPassword.toString());

			drMsMaininfoService.updatePw(params);

			params.put("ipAddr", Utils.getClientIpAddress(request));
			params.put("tplCd", Utils.getProperties("Globals.findPw.tplCd", "00001"));
			params.put("msPhone", (params.get("msPhone")).toString().replace("-", ""));
			params.put("msPassword", newMsPassword);
			
			// commonService.sendSms(params); (나중)
		} catch (Exception e) {
			result.setCode("9999");
			result.setMessage(e.getMessage());
		} finally {
			Utils.sendData(response, Utils.makeJsonString(result));
		}
	}

	/*@RequestMapping(value = "/checkPhoneNumber")
	public void checkPhoneNumber(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam Map<String, Object> params) {
		ResultVO result = new ResultVO();

		try {
			if(drMsMaininfoService.selectPhoneNumber(params) > 0) {
				params.put("mergeYn", "Y");
				if(drMsMaininfoService.selectPhoneNumber(params) > 0) {
					result.setCode("1000");
	        		result.setMessage("이미 가입된 핸드폰번호 입니다.");
    			}else {
    				result.setCode("2000");
    				result.setMessage("이미 가입한 이력이 있습니다. 통합회원으로 새로 가입하시겠습니까?");
    			}
			}
		} catch (Exception e) {
			result.setCode("9999");
			result.setMessage(e.getMessage());
		} finally {
			Utils.sendData(response, Utils.makeJsonString(result));
		}
	}
	*/
}

package com.yido.clubd.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yido.clubd.common.utils.Globals;
import com.yido.clubd.common.utils.SessionVO;
import com.yido.clubd.common.utils.Utils;
import com.yido.clubd.model.BookInfoVO;
import com.yido.clubd.model.MemberVO;
import com.yido.clubd.repository.MemberMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 회원정보 (회원/프로)
 * 
 * @author YOO
 *
 */
@Slf4j
@Service
public class MemberService {

	@Autowired
    private MemberMapper memberMapper;
	
	@Autowired
	private MemberService memberService;
	
	public MemberVO selectMember(Map<String, Object> params) {
    	return memberMapper.selectMember(params);
	}	
    
    /**
	 * 로그인
	 * 
	 * @param params
	 * @return
     * @throws Exception 
	 */
    public MemberVO selectLoginUser(Map<String, Object> params) throws Exception {
    	return memberMapper.selectLoginUser(params);
    }
    
    public String selectMsNum(Map<String, Object> params) throws Exception {
    	return memberMapper.selectMsNum(params);
    }
    
    /**
     * 회원/프로 정보 가져와서 세션에 저장
     * 
     * @param params
     * @return
     * @throws Exception 
     */
    public SessionVO selectMsSession(Map<String, Object> params) throws Exception {
    	return memberMapper.selectMsSession(params);
    }
    
    public void insertLoginLog(Map<String, Object> params) throws Exception {
    	memberMapper.insertLoginLog(params);
    }
    
    /**
     * 회원/프로 등록 
     * 
     * @param member
     * @return
     * @throws Exception 
     */
	public void insertMember(MemberVO member) throws Exception {
		
		/*
		 * String msNum = memberService.makeMsNum(member);
		 * member.setMsNum(msNum);
		 */
		
		member.setMsStatus("Y");
		member.setMsBkGrant("Y");
		member.setMsDivision("01");
		member.setMsLevel("11");
		member.setMsBlank("N");
		member.setMsEmailYn("N");		
		member.setMsDormant("N");			
		member.setInputStaff("APP");			
		member = this.seperateMsPhone(member);
		
		memberMapper.insertMember(member);
	}
	
	public MemberVO seperateMsPhone(MemberVO member) {
		String[] msPhone = member.getMsPhone().split("-");
		member.setMsFirstPhone1(msPhone[0]);
		member.setMsMidPhone1(msPhone[1]);
		member.setMsLastPhone1(msPhone[2]);	
		return member;
	}
	
	public Map<String, Object> seperateMsPhone2(Map<String, Object> params) {
		String[] msPhone = ((String)params.get("msPhone")).split("-");
		params.put("msFirstPhone1", msPhone[0]);
		params.put("msMidPhone1", msPhone[1]);
		params.put("msLastPhone1", msPhone[2]);	
		return params;
	}
	
	/**
     * 회원/프로 수정 
     * 
     * @param member
     * @return
	 * @throws Exception 
     */
	public SessionVO updateMember(Map<String, Object> params) throws Exception {
		
		params.put("updateIp", Globals.serverIpAddress);
		params.put("updateStaff", "APP");
		if((String)params.get("msPhone") != null && (String)params.get("msPhone") != "") {
			params = this.seperateMsPhone2(params);			
		}
		
		memberMapper.updateMember(params);
		
		SessionVO member = this.selectMsSession(params);			
		return member;
	}
	
	public void saveMemberBasic(Map<String, Object> params) {
		
		if((String)params.get("msLessonTrem") != null && (String)params.get("msLessonTrem") != "") {			
			String msLessonTrem = ((String)params.get("msLessonTrem")).replace("회", "");
			String msLessonUnit = msLessonTrem.substring(0,1);
			msLessonTrem = msLessonTrem.substring(1);
			
			params.put("msLessonTrem", msLessonTrem);
			params.put("msLessonUnit", msLessonUnit);
		}
		
		if((String)params.get("msLessonMinute") != null && (String)params.get("msLessonMinute") != "") {
			String msLessonMinute = ((String)params.get("msLessonMinute")).replace("분", "");
			params.put("msLessonMinute", msLessonMinute);			
		}
		
		if((String)params.get("msRoundCnt") != null && (String)params.get("msRoundCnt") != "") {		
			String msRoundCnt = ((String)params.get("msRoundCnt")).replace("회", "");
			String msRoundUnit = msRoundCnt.substring(0,1);
			msRoundCnt = msRoundCnt.substring(1);
			
			params.put("msRoundCnt", msRoundCnt);
			params.put("msRoundUnit", msRoundUnit);
		}
		
		if((String)params.get("msHandcap") != null && (String)params.get("msHandcap") != "") {			
			String msHandcap = ((String)params.get("msHandcap")).replace("개", "");
			params.put("msHandcap", msHandcap);
		}		
		
		MemberVO member = memberMapper.selectMemberBasic((String)params.get("msNum"));
		if(member != null) {
			memberMapper.updateMemberBasic(params);
		} else {
			memberMapper.insertMemberBasic(params);
		}
	}
	
	public MemberVO selectMemberBasic(String msNum) {
		return memberMapper.selectMemberBasic(msNum);
	}

	public void saveMemberCar(Map<String, Object> params) {
		
		List<Map<String,Object>> carList = new ArrayList<Map<String,Object>>();
		String msNum = (String) params.get("msNum");
		
		if((String)params.get("msCarNo1") != null && (String)params.get("msCarNo1") != "") {
			Map<String,Object> car = new HashMap<String,Object>();
			car.put("carSeq", 1);
			car.put("msNum", msNum);
			car.put("msCarNo", params.get("msCarNo1"));
			carList.add(car);
		}
		if((String)params.get("msCarNo2") != null && (String)params.get("msCarNo2") != "") {
			Map<String,Object> car = new HashMap<String,Object>();
			car.put("carSeq", 2);
			car.put("msNum", msNum);
			car.put("msCarNo", params.get("msCarNo2"));
			carList.add(car);
		}
		if((String)params.get("msCarNo3") != null && (String)params.get("msCarNo3") != "") {
			Map<String,Object> car = new HashMap<String,Object>();
			car.put("carSeq", 3);
			car.put("msNum", msNum);
			car.put("msCarNo", params.get("msCarNo3"));
			carList.add(car);
		}
		
		if(!carList.isEmpty()) {			
			List<MemberVO> result = memberMapper.selectMemberCarList(msNum);
			if(!result.isEmpty()) {
				memberMapper.deleteMemberCar(msNum);
			}
			for(Map<String, Object> item : carList) {				
				memberMapper.insertMemberCar(item);
			}
		} else {
			return;
		}
		
	}
	
	public List<MemberVO> selectMemberCarList(String msNum) {
		return memberMapper.selectMemberCarList(msNum);
	}

	/**
	 * 아이디/전화번호 중복체크, 아이디 찾기 
	 * 
	 * @param params
	 * @return
	 * @throws Exception 
	 */
	public MemberVO selectFindUser(Map<String, Object> params) throws Exception {
		return memberMapper.selectFindUser(params);
	}

	public MemberVO selectFindPw(Map<String, Object> params) throws Exception {
		return memberMapper.selectFindPw(params);
	}

	
	/**
	 * 회원 위약체크 
	 * - 회원테이블 > DR_BK_GRANT
	 * 
	 * @param params
	 * @return
	 */
	public String chkMsBkGrant(Map<String, Object> params) {
		return memberMapper.chkMsBkGrant(params);
	}
	
	public void updatePw(Map<String, Object> params) throws Exception {
		memberMapper.updatePw(params);
	}

	public List<MemberVO> selectProList() {
		return memberMapper.selectProList();
	}

	public void updateMsSessionKey(Map<String, Object> params) {
		memberMapper.updateMsSessionKey(params);
		
	}

	public SessionVO selectSessionLoginUser(Map<String, Object> params) {
		return memberMapper.selectSessionLoginUser(params);
	}

	public void loginUserInfo(HttpServletRequest req, HttpServletResponse res, HttpSession session,
			Map<String, Object> params) throws Exception {
		String msNum = this.selectMsNum(params);
		params.put("msNum", msNum);
		
		SessionVO member = this.selectMsSession(params);			
		session.setAttribute("msMember", member);
		session.setMaxInactiveInterval(30 * 60);
		
		// 자동 로그인
		String loginAuto = "";
		if(params.containsKey("loginAuto")) {
			loginAuto = "Y".equals((String) params.get("loginAuto"))? "Y" : "N";
			
			if(loginAuto == "Y") {				
				Cookie loginCookie = new Cookie("sessionKey", session.getId());
				loginCookie.setPath("/");
				loginCookie.setMaxAge(60*60*24*30);
				res.addCookie(loginCookie);
				
				params.put("msSessionKey", session.getId());
				memberService.updateMsSessionKey(params);
			}
		}
				
		// 로그인 로그 입력
		String ua = null;

		if(params.get("ua") != null) {
			ua = params.get("ua").toString();
		}else {
			ua = req.getHeader("user-agent");
			if (ua.indexOf("Android") != -1) {
				ua = "Android";
			} else if( ua.indexOf("iPad") != -1 || ua.indexOf("iPhone") != -1 || ua.indexOf("iOS") != -1 || ua.indexOf("MAC") != -1 ) {
				ua = "iPhone";
			} else if( ua.indexOf("Windows") != -1) {
				ua = "windows";
			} else {
				ua = req.getHeader("user-agent");
			}
		}

		params.put("platform", "APP");
		params.put("userAgent", ua);
		params.put("loginAuto", loginAuto);
		params.put("inputStaff", "APP");
		params.put("ipAddr", Utils.getClientIpAddress(req));

		this.insertLoginLog(params);
		
	}

}

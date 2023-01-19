package com.yido.clubd.service;

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
import com.yido.clubd.model.DrMsMaininfo;
import com.yido.clubd.repository.DrMsMaininfoMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 회원정보 (회원/프로)
 * 
 * @author bae, YOO
 *
 */
@Slf4j
@Service
public class DrMsMaininfoService {

	@Autowired
    private DrMsMaininfoMapper drMsMaininfoMapper;
	
	@Autowired
	private DrMsMaininfoService drMsMaininfoService;
	
	public DrMsMaininfo selectDrMsMaininfo(Map<String, Object> params) {
    	return drMsMaininfoMapper.selectDrMsMaininfo(params);
	}	
    
    /**
	 * 로그인
	 * 
	 * @param params
	 * @return
     * @throws Exception 
	 */
    public DrMsMaininfo selectLoginUser(Map<String, Object> params) throws Exception {
    	return drMsMaininfoMapper.selectLoginUser(params);
    }
    
    public String selectMsNum(Map<String, Object> params) throws Exception {
    	return drMsMaininfoMapper.selectMsNum(params);
    }
    
    /**
     * 회원/프로 정보 가져와서 세션에 저장
     * 
     * @param params
     * @return
     * @throws Exception 
     */
    public SessionVO selectMsSession(Map<String, Object> params) throws Exception {
    	return drMsMaininfoMapper.selectMsSession(params);
    }
    
    public void insertLoginLog(Map<String, Object> params) throws Exception {
    	drMsMaininfoMapper.insertLoginLog(params);
    }
    
    /**
     * 회원/프로 등록 
     * 
     * @param drMsMaininfo
     * @return
     * @throws Exception 
     */
	public void insertDrMsMaininfo(DrMsMaininfo drMsMaininfo) throws Exception {
		
		/*
		 * String msNum = drMsMaininfoService.makeMsNum(drMsMaininfo);
		 * drMsMaininfo.setMsNum(msNum);
		 */
		
		drMsMaininfo.setMsStatus("Y");
		drMsMaininfo.setMsBkGrant("Y");
		drMsMaininfo.setMsDivision("01");
		drMsMaininfo.setMsLevel("11");
		drMsMaininfo.setMsBlank("N");
		drMsMaininfo.setMsEmailYn("N");		
		drMsMaininfo.setMsDormant("N");			
		drMsMaininfo.setInputStaff("APP");			
		drMsMaininfo = this.seperateMsPhone(drMsMaininfo);
		
		drMsMaininfoMapper.insertDrMsMaininfo(drMsMaininfo);
	}
	
	public DrMsMaininfo seperateMsPhone(DrMsMaininfo drMsMaininfo) {
		String[] msPhone = drMsMaininfo.getMsPhone().split("-");
		drMsMaininfo.setMsFirstPhone1(msPhone[0]);
		drMsMaininfo.setMsMidPhone1(msPhone[1]);
		drMsMaininfo.setMsLastPhone1(msPhone[2]);	
		return drMsMaininfo;
	}
	
	public Map<String, Object> seperateMsPhone2(Map<String, Object> params) {
		String[] msPhone = ((String)params.get("MsPhone")).split("-");
		params.put("msFirstPhone1", msPhone[0]);
		params.put("msMidPhone1", msPhone[1]);
		params.put("msLastPhone1", msPhone[2]);	
		return params;
	}
	
	/**
     * 회원/프로 수정 
     * 
     * @param drMsMaininfo
     * @return
	 * @throws Exception 
     */
	public SessionVO updateDrMsMaininfo(Map<String, Object> params) throws Exception {
		
		params.put("updateIp", Globals.serverIpAddress);
		params.put("updateStaff", "APP");
		if(params.containsKey("msPhone")) {
			params = this.seperateMsPhone2(params);			
		}
		
		drMsMaininfoMapper.updateDrMsMaininfo(params);
		
		SessionVO member = this.selectMsSession(params);			
		return member;
	}

	/**
	 * 아이디/전화번호 중복체크, 아이디 찾기 
	 * 
	 * @param params
	 * @return
	 * @throws Exception 
	 */
	public DrMsMaininfo selectFindUser(Map<String, Object> params) throws Exception {
		return drMsMaininfoMapper.selectFindUser(params);
	}

	public DrMsMaininfo selectFindPw(Map<String, Object> params) throws Exception {
		return drMsMaininfoMapper.selectFindPw(params);
	}

	
	/**
	 * 회원 위약체크 
	 * - 회원테이블 > DR_BK_GRANT
	 * 
	 * @param params
	 * @return
	 */
	public String chkMsBkGrant(BookInfoVO bookInfo) {
		return drMsMaininfoMapper.chkMsBkGrant(bookInfo);
	}
	
	public void updatePw(Map<String, Object> params) throws Exception {
		drMsMaininfoMapper.updatePw(params);
	}

	public List<DrMsMaininfo> selectProList() {
		return drMsMaininfoMapper.selectProList();
	}

	public void updateMsSessionKey(Map<String, Object> params) {
		drMsMaininfoMapper.updateMsSessionKey(params);
		
	}

	public SessionVO selectSessionLoginUser(Map<String, Object> params) {
		return drMsMaininfoMapper.selectSessionLoginUser(params);
	}

	public void loginUserInfo(HttpServletRequest req, HttpServletResponse res, HttpSession session,
			Map<String, Object> params) throws Exception {
		String msNum = this.selectMsNum(params);
		params.put("msNum", msNum);
		
		SessionVO member = this.selectMsSession(params);			
		session.setAttribute("msMember", member);
		session.setMaxInactiveInterval(30 * 60);
		
		String loginAuto = (((String) params.get("loginAuto")).equals("Y"))? "Y" : "N";
		
		// 자동 로그인
		if(params.containsKey("loginAuto") && loginAuto.equals("Y")) {
			Cookie loginCookie = new Cookie("sessionKey", session.getId());
			loginCookie.setPath("/");
			loginCookie.setMaxAge(60*60*24*30);
			res.addCookie(loginCookie);
			
			params.put("msSessionKey", session.getId());
			drMsMaininfoService.updateMsSessionKey(params);
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

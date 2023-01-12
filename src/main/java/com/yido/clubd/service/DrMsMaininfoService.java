package com.yido.clubd.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.yido.clubd.common.utils.SessionVO;
import com.yido.clubd.common.utils.Utils;
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
	
	@Autowired
    private PasswordEncoder passwordEncoder;

    public DrMsMaininfo selectDrMsMaininfo(String msId) {
    	return drMsMaininfoMapper.selectDrMsMaininfo(msId);
    }
    
	/**
	 * 아이디 중복체크 
	 * 
	 * @param params
	 * @return
	 */
    public DrMsMaininfo findDuplicateUser(Map<String, Object> params) {
    	return drMsMaininfoMapper.selectDuplicateUser(params);
    }
    
    /**
	 * 로그인
	 * 
	 * @param params
	 * @return
	 */
    public DrMsMaininfo loginUser(Map<String, Object> params) {
    	return drMsMaininfoMapper.selectLoginUser(params);
    }
    
    public String selectMsNum(Map<String, Object> params) {
    	return drMsMaininfoMapper.selectMsNum(params);
    }
    
    /**
     * 회원/프로 정보 가져와서 세션에 저장
     * 
     * @param params
     * @return
     */
    public SessionVO selectMsSession(Map<String, Object> params) {
    	return drMsMaininfoMapper.selectMsSession(params);
    }
    
    /**
     * 회원/프로 등록 
     * 
     * @param drMsMaininfo
     * @return
     */
	public void insertDrMsMaininfo(DrMsMaininfo drMsMaininfo) {
		
		/*
		 * String msNum = drMsMaininfoService.makeMsNum(drMsMaininfo);
		 * drMsMaininfo.setMsNum(msNum);
		 */
		
		drMsMaininfo.setMsStatus("Y");
		drMsMaininfo.setMsBkGrant("Y");
		drMsMaininfo.setMsDivision("01");
		drMsMaininfo.setMsLevel("11");
		drMsMaininfo.setMsLoginCd("APP");
		drMsMaininfo.setMsBlank("N");
		drMsMaininfo.setMsEmailYn("N");		
		drMsMaininfo.setMsDormant("N");			
		drMsMaininfo = drMsMaininfoService.seperateMsPhone(drMsMaininfo);
		
		drMsMaininfoMapper.insertDrMsMaininfo(drMsMaininfo);
	}
	
	public DrMsMaininfo seperateMsPhone(DrMsMaininfo drMsMaininfo) {
		String[] msPhone = drMsMaininfo.getMsPhone().split("-");
		drMsMaininfo.setMsFirstPhone1(msPhone[0]);
		drMsMaininfo.setMsMidPhone1(msPhone[1]);
		drMsMaininfo.setMsLastPhone1(msPhone[2]);	
		return drMsMaininfo;
	}
	
	/**
     * 회원/프로 수정 
     * 
     * @param drMsMaininfo
     * @return
     */
	public int updateDrMsMaininfo(DrMsMaininfo drMsMaininfo) {
		return drMsMaininfoMapper.updateDrMsMaininfo(drMsMaininfo);
	}

	public DrMsMaininfo checkKakaoUserYn(HashMap<String, Object> userInfo) {
		return drMsMaininfoMapper.selectDuplicateUser(userInfo);
	}
	
	/**
	 * 회원 위약체크 
	 * - 회원테이블 > DR_BK_GRANT
	 * 
	 * @param params
	 * @return
	 */
	public String chkMsBkGrant(Map<String, Object> params) {
		return drMsMaininfoMapper.chkMsBkGrant(params);
	}
	
}

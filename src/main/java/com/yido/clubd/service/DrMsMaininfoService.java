package com.yido.clubd.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.yido.clubd.common.utils.SessionVO;
import com.yido.clubd.model.DrMsMaininfo;
import com.yido.clubd.repository.DrMsMaininfoMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 회원정보 (회원/프로)
 * 
 * @author bae
 *
 */
@Slf4j
@Service
public class DrMsMaininfoService {

	@Autowired
    private DrMsMaininfoMapper drMsMaininfoMapper;
	@Autowired
    private PasswordEncoder passwordEncoder;

    public DrMsMaininfo selectDrMsMaininfo(String msId) {
    	return drMsMaininfoMapper.selectDrMsMaininfo(msId);
    }
    
    public DrMsMaininfo selectOneUser(String msId) {
    	return drMsMaininfoMapper.selectOne(msId);
    }
    
    public String getMsNum(Map<String, Object> params) {
    	return drMsMaininfoMapper.getMsNum(params);
    }
    
    /**
     * 회원/프로 정보 가져와서 세션에 저장하기 위한 용도
     * 
     * @param params
     * @return
     */
    public SessionVO getMsSession(Map<String, Object> params) {
    	return drMsMaininfoMapper.getMsSession(params);
    }
    
    /**
     * 회원/프로 등록 
     * 
     * @param drMsMaininfo
     * @return
     */
	public int insertDrMsMaininfo(DrMsMaininfo drMsMaininfo) {
		return drMsMaininfoMapper.insertDrMsMaininfo(drMsMaininfo);
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

	/**
	 * 아이디 중복체크 
	 * 
	 * @param params
	 * @return
	 */
	public int duplicateCheckSocialId(Map<String, Object> params) {
		return drMsMaininfoMapper.duplicateCheckSocialId(params);
	}
}

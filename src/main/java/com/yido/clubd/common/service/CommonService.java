package com.yido.clubd.common.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yido.clubd.common.repository.CommonMapper;


@Service("CommonService")
public class CommonService {
	
	@Autowired
	private CommonMapper commonMapper;
	
	public List<Map<String, Object>> getCommonCode(Map<String, Object> params) throws Exception {
		return commonMapper.getCommonCode(params);
	}

	public Map<String, Object> doFindId(Map<String, Object> params) throws Exception {
		return commonMapper.doFindId(params);
	}

	public int checkPhoneNumber(Map<String, Object> params) throws Exception {
		return commonMapper.checkPhoneNumber(params);
	}

	public Map<String, Object> doFindPw(Map<String, Object> params) throws Exception {
		return commonMapper.doFindPw(params);
	}

	public void updatePw(Map<String, Object> params) throws Exception {
		commonMapper.updatePw(params);
	}

	public void savePushKey(Map<String, Object> params) throws Exception {
		commonMapper.savePushKey(params);
	}

	public void sendSms(Map<String, Object> params) throws Exception {
		commonMapper.sendSms(params);
	}

	public void actionAgree(Map<String, Object> params) throws Exception {
		commonMapper.actionAgree(params);
	}
}

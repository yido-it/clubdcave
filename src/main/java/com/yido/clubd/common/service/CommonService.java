package com.yido.clubd.common.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yido.clubd.common.repository.CommonMapper;
import com.yido.clubd.model.CdCommon;


@Service("CommonService")
public class CommonService {
	
	@Autowired
	private CommonMapper commonMapper;
	
//	public List<Map<String, Object>> getCommonCode(Map<String, Object> params) throws Exception {
//		return commonMapper.getCommonCode(params);
//	}
	
    public CdCommon getCommonCode(CdCommon cdCommon) {
    	return commonMapper.getCommonCode(cdCommon);
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

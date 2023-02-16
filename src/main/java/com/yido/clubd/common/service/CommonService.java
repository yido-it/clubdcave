package com.yido.clubd.common.service;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.yido.clubd.common.repository.CommonMapper;
import com.yido.clubd.component.FileUtil;
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

    public List<CdCommon> getCommonCodeList(CdCommon cdCommon) {
    	return commonMapper.getCommonCodeList(cdCommon);
    }
    
    public List<CdCommon> getCommonCodeDetailList(CdCommon cdCommon) {
    	return commonMapper.getCommonCodeDetailList(cdCommon);
    }
    
	public List<Map<String, Object>> getAddrList(Map<String, Object> params) {
		return commonMapper.getAddrList(params);
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

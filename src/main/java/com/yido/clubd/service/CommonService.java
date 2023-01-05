package com.yido.clubd.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yido.clubd.repository.CommonMapper;
import com.yido.clubd.repository.PgHistoryMapper;

@Service("CommonService")
public class CommonService {

	@Autowired
    private CommonMapper commonMapper;
	
	public void sendSms(Map<String, Object> params) throws Exception {
		commonMapper.sendSms(params);
	}
}

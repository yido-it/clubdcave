package com.yido.clubd.common.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.yido.clubd.model.CdCommon;

@Mapper
@Repository
public interface CommonMapper {

	// public List<Map<String, Object>> getCommonCode(Map<String, Object> params) throws Exception;
	
	public CdCommon getCommonCode(CdCommon cdCommon);
	
	public List<CdCommon> getCommonCodeList(CdCommon cdCommon);
	
	public List<Map<String, Object>> getAddrList(Map<String, Object> params);
	
	public void savePushKey(Map<String, Object> params) throws Exception;
	
	public void sendSms(Map<String, Object> params) throws Exception;
	
	public void actionAgree(Map<String, Object> params) throws Exception;

	public List<CdCommon> getCommonCodeDetailList(CdCommon cdCommon);
}




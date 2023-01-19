package com.yido.clubd.common.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CommonMapper {

	public List<Map<String, Object>> getCommonCode(Map<String, Object> params) throws Exception;		
	public void savePushKey(Map<String, Object> params) throws Exception;
	public void sendSms(Map<String, Object> params) throws Exception;
	public void actionAgree(Map<String, Object> params) throws Exception;
}




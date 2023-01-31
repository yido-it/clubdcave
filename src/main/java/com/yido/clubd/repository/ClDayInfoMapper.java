package com.yido.clubd.repository;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.yido.clubd.model.ClDayInfo;

/**
 * 월력
 * 
 * @author bae
 *
 */
@Mapper
@Repository
public interface ClDayInfoMapper {

	// 지점, 베이, 회원등급에 따른 달력 조회 
	public List<Map<String, Object>> selectList(Map<String, Object> map);
	
	// 기본달력
	public List<Map<String, Object>> selectBasicList(Map<String, Object> map);

}

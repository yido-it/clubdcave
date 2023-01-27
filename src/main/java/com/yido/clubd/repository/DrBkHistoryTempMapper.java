package com.yido.clubd.repository;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.yido.clubd.model.DrBkHistoryTemp;

/**
 * 예약내역 임시테이블
 * 
 * @author bae
 *
 */
@Mapper
@Repository
public interface DrBkHistoryTempMapper {
	
	public DrBkHistoryTemp getHistory(Map<String, Object> params);
	
	public int insertDrBkHistoryTemp(DrBkHistoryTemp drBkHistoryTemp);
	
	public int deleteHistoryTemp(DrBkHistoryTemp drBkHistoryTemp);
	
}

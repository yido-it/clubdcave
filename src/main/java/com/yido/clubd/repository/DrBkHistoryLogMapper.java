package com.yido.clubd.repository;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.yido.clubd.model.DrBkHistory;
import com.yido.clubd.model.DrBkHistoryLog;

/**
 * 예약내역 로그
 * 
 * @author bae
 *
 */
@Mapper
@Repository
public interface DrBkHistoryLogMapper {

	// 로그 등록
	public int insertDrBkHistoryLog(DrBkHistoryLog drBkHistoryLog);
}

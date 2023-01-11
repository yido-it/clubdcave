package com.yido.clubd.repository;

import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


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
	public int insertDrBkHistoryLog(Map<String, Object> params);
}

package com.yido.clubd.repository;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.yido.clubd.model.DrProSchedule;
import com.yido.clubd.model.DrProScheduleLog;

/**
 * 레슨프로일정 로그
 * 
 * @author bae
 *
 */
@Mapper
@Repository
public interface DrProScheduleLogMapper {

	// 로그 등록
	public int insertDrProScheduleLog(DrProScheduleLog drProScheduleLog);


}

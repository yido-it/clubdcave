package com.yido.clubd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yido.clubd.model.DrProSchedule;
import com.yido.clubd.model.DrProScheduleLog;
import com.yido.clubd.repository.DrProScheduleLogMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * 레슨프로일정 로그
 * 
 * @author bae
 *
 */
@Slf4j
@Service
public class DrProScheduleLogService {

	@Autowired
    private DrProScheduleLogMapper drProScheduleLogMapper;

	/**
	 * 로그 등록
	 * 
	 * @param drProScheduleLog
	 * @return
	 */
	public int insertDrProScheduleLog(DrProScheduleLog drProScheduleLog) {
		return drProScheduleLogMapper.insertDrProScheduleLog(drProScheduleLog);
	}
}

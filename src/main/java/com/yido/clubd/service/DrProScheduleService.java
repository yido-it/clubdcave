package com.yido.clubd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yido.clubd.model.DrProSchedule;
import com.yido.clubd.model.ProNotice;
import com.yido.clubd.repository.DrProScheduleMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * 레슨프로일정
 * 
 * @author bae
 *
 */
@Slf4j
@Service
public class DrProScheduleService {

	@Autowired
    private DrProScheduleMapper drProScheduleMapper;

	/**
	 * 레슨프로일정 등록
	 * 
	 * @param drProSchedule
	 * @return
	 */
	public int insertDrProSchedule(DrProSchedule drProSchedule) {
		return drProScheduleMapper.insertDrProSchedule(drProSchedule);
	}
	
	/**
	 * 레슨프로일정 수정
	 * 
	 * @param drProSchedule
	 * @return
	 */
	public int updateDrProSchedule(DrProSchedule drProSchedule) {
		return drProScheduleMapper.updateDrProSchedule(drProSchedule);
	}
}

package com.yido.clubd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yido.clubd.model.DrBkHistoryLog;
import com.yido.clubd.repository.DrBkHistoryLogMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * 예약내역 로그
 * 
 * @author bae
 *
 */
@Slf4j
@Service
public class DrBkHistoryLogService {

	@Autowired
    private DrBkHistoryLogMapper drBkHistoryLogMapper;

	/**
	 * 로그 등록
	 * 
	 * @param drBkHistory
	 * @return
	 */
	public int insertDrBkHistoryLog(DrBkHistoryLog drBkHistoryLog) {
		return drBkHistoryLogMapper.insertDrBkHistoryLog(drBkHistoryLog);
	}
}

package com.yido.clubd.service;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yido.clubd.model.DrBkHistory;
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
	public int insertDrBkHistoryLog(DrBkHistory drBkHistory) {
		return drBkHistoryLogMapper.insertDrBkHistoryLog(drBkHistory);
	}
	
	/**
	 * 금액 변경
	 * 
	 * @param drBkHistory
	 * @return
	 */
	public int updateBkAmount(DrBkHistory drBkHistory) {
		return drBkHistoryLogMapper.updateBkAmount(drBkHistory);
	}

}

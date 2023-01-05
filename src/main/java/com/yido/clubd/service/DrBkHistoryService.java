package com.yido.clubd.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.yido.clubd.model.DrBkHistory;
import com.yido.clubd.repository.DrBkHistoryMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 예약내역
 * 
 * @author bae
 *
 */
@Slf4j
@Service
public class DrBkHistoryService {

	@Autowired
    private DrBkHistoryMapper drBkHistoryMapper;

	/**
	 * 예약내역 등록
	 * 
	 * @param drBkHistory
	 * @return
	 */
	public int insertDrBkHistory(DrBkHistory drBkHistory) {
		return drBkHistoryMapper.insertDrBkHistory(drBkHistory);
	}
}

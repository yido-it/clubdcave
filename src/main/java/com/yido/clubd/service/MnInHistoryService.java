package com.yido.clubd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yido.clubd.model.MnInHistory;
import com.yido.clubd.repository.MnInHistoryMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 입금내역
 * 
 * @author bae
 *
 */
@Slf4j
@Service
public class MnInHistoryService {

	@Autowired
    private MnInHistoryMapper mnInHistoryMapper;

	public int insertMnInHistory(MnInHistory mnInHistory) {
		return mnInHistoryMapper.insertMnInHistory(mnInHistory);
	}
	
}

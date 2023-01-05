package com.yido.clubd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yido.clubd.model.DrMsBasicLog;
import com.yido.clubd.repository.DrMsBasicLogMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * 회원추가정보 로그
 * 
 * @author bae
 *
 */
@Slf4j
@Service
public class DrMsBasicLogService {

	@Autowired
    private DrMsBasicLogMapper drMsBasicLogMapper;
	
    /**
     * 로그 등록 
     * 
     * @param drMsBasicLog
     * @return
     */
	public int insertDrMsBasicLog(DrMsBasicLog drMsBasicLog) {
		return drMsBasicLogMapper.insertDrMsBasicLog(drMsBasicLog);
	}
}

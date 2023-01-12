package com.yido.clubd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yido.clubd.model.DrMsMainInfoLog;
import com.yido.clubd.model.DrMsMaininfo;
import com.yido.clubd.repository.DrMsMainInfoLogMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * 회원정보 (회원/프로) 로그
 * 
 * @author bae
 *
 */
@Slf4j
@Service
public class DrMsMainInfoLogService {

	@Autowired
    private DrMsMainInfoLogMapper drMsMainInfoLogMapper;
    
    /**
     * 로그 등록 
     * - logDiv : 로그종류(I,U,D)
     * 
     * @param drMsMaininfo
     * @return
     */
	public int insertDrMsMainInfoLog(DrMsMainInfoLog drMsMainInfoLog) {
		return drMsMainInfoLogMapper.insertDrMsMainInfoLog(drMsMainInfoLog);
	}

}

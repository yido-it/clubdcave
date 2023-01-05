package com.yido.clubd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yido.clubd.model.DrMsCoInfoLog;
import com.yido.clubd.repository.DrMsCoInfoLogMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * 회원지점연결정보 LOG
 * 
 * @author bae
 *
 */
@Slf4j
@Service
public class DrMsCoInfoLogService {

	@Autowired
    private DrMsCoInfoLogMapper drMsCoInfoLogMapper;

    public DrMsCoInfoLog selectList(DrMsCoInfoLog drMsCoInfoLog) {
    	return drMsCoInfoLogMapper.selectList(drMsCoInfoLog);
    }
}

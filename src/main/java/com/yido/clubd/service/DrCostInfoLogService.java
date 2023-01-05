package com.yido.clubd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yido.clubd.model.DrCostInfoLog;
import com.yido.clubd.repository.DrCostInfoLogMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * 요금정보 로그
 * 
 * @author bae
 *
 */
@Slf4j
@Service
public class DrCostInfoLogService {

	@Autowired
    private DrCostInfoLogMapper drCostInfoLogMapper;

    public DrCostInfoLog selectList(DrCostInfoLog drCostInfoLog) {
    	return drCostInfoLogMapper.selectList(drCostInfoLog);
    }
}

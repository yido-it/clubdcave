package com.yido.clubd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yido.clubd.model.DrCostListLog;
import com.yido.clubd.repository.DrCostListLogMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * 요금세부내역 로그
 *  
 * @author bae
 *
 */
@Slf4j
@Service
public class DrCostListLogService {

	@Autowired
    private DrCostListLogMapper drCostListLogMapper;

    public DrCostListLog selectList(DrCostListLog drCostListLog) {
    	return drCostListLogMapper.selectList(drCostListLog);
    }
}

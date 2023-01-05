package com.yido.clubd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yido.clubd.model.DrCostInfo;
import com.yido.clubd.repository.DrCostInfoMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * 요금정보
 * 
 * @author bae
 *
 */
@Slf4j
@Service
public class DrCostInfoService {

	@Autowired
    private DrCostInfoMapper drCostInfoMapper;

    public DrCostInfo selectList(DrCostInfo drCostInfo) {
    	return drCostInfoMapper.selectList(drCostInfo);
    }
}

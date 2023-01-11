package com.yido.clubd.service;

import java.util.Map;

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

	/**
	 * 요금조회
	 * 
	 * @param map
	 * @return
	 */
    public Map<String, Object> getCostInfo(Map<String, Object> map) {
    	return drCostInfoMapper.getCostInfo(map);
    }
}

package com.yido.clubd.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yido.clubd.model.DrBayInfo;
import com.yido.clubd.repository.DrBayInfoMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * BAY관리
 * 
 * @author bae
 *
 */
@Slf4j
@Service
public class DrBayInfoService {

	@Autowired
    private DrBayInfoMapper drBayInfoMapper;
	
    public List<DrBayInfo> selectList(Map<String, Object> map) {
    	return drBayInfoMapper.selectList(map);
    }
    
  
}

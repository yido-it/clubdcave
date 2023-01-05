package com.yido.clubd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yido.clubd.model.ClDayInfo;
import com.yido.clubd.repository.ClDayInfoMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * 월력
 * 
 * @author bae
 *
 */
@Slf4j
@Service
public class ClDayInfoService {

	@Autowired
    private ClDayInfoMapper clDayInfoMapper;

    public ClDayInfo selectList(ClDayInfo clDayInfo) {
    	return clDayInfoMapper.selectList(clDayInfo);
    }
}

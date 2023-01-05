package com.yido.clubd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yido.clubd.model.DrBkSetCount;
import com.yido.clubd.repository.DrBkSetCountMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * 예약오픈수량
 * 
 * @author bae
 *
 */
@Slf4j
@Service
public class DrBkSetCountService {

	@Autowired
    private DrBkSetCountMapper drBkSetCountMapper;

    public DrBkSetCount selectList(DrBkSetCount drBkSetCount) {
    	return drBkSetCountMapper.selectList(drBkSetCount);
    }
}

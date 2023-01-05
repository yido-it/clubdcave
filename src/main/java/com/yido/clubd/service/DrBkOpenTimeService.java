package com.yido.clubd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yido.clubd.model.DrBkOpenTime;
import com.yido.clubd.repository.DrBkOpenTimeMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * 예약오픈일정 시간별
 * 
 * @author bae
 *
 */
@Slf4j
@Service
public class DrBkOpenTimeService {

	@Autowired
    private DrBkOpenTimeMapper drBkOpenTimeMapper;

    public DrBkOpenTime getBkDay(DrBkOpenTime drBkOpenTime) {
    	return drBkOpenTimeMapper.getBkDay(drBkOpenTime);
    }
}

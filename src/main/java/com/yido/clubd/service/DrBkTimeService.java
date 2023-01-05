package com.yido.clubd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yido.clubd.model.DrBkTime;
import com.yido.clubd.repository.DrBkTimeMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * 예약타임
 * 
 * @author bae
 *
 */
@Slf4j
@Service
public class DrBkTimeService {

	@Autowired
    private DrBkTimeMapper drBkTimeMapper;

    public DrBkTime selectList(DrBkTime drBkTime) {
    	return drBkTimeMapper.selectList(drBkTime);
    }
}

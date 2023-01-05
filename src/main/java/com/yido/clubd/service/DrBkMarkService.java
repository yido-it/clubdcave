package com.yido.clubd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yido.clubd.model.DrBkMark;
import com.yido.clubd.repository.DrBkMarkMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * 예약선점용
 * 
 * @author bae
 *
 */
@Slf4j
@Service
public class DrBkMarkService {

	@Autowired
    private DrBkMarkMapper drBkMarkMapper;

    public DrBkMark selectList(DrBkMark drBkMark) {
    	return drBkMarkMapper.selectList(drBkMark);
    }
}

package com.yido.clubd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yido.clubd.model.DrClBayMake;
import com.yido.clubd.repository.DrClBayMakeMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * 일자별 BAY별 생성규칙
 * 
 * @author bae
 *
 */
@Slf4j
@Service
public class DrClBayMakeService {

	@Autowired
    private DrClBayMakeMapper drClBayMakeMapper;

    public DrClBayMake selectList(DrClBayMake drClBayMake) {
    	return drClBayMakeMapper.selectList(drClBayMake);
    }
}

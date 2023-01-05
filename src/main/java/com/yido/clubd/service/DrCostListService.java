package com.yido.clubd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yido.clubd.model.DrCostList;
import com.yido.clubd.repository.DrCostListMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * 요금세부내역
 * 
 * @author bae
 *
 */
@Slf4j
@Service
public class DrCostListService {

	@Autowired
    private DrCostListMapper drCostListMapper;

    public DrCostList selectList(DrCostList drCostList) {
    	return drCostListMapper.selectList(drCostList);
    }
}

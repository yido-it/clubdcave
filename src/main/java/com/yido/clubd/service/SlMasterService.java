package com.yido.clubd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yido.clubd.model.SlMaster;
import com.yido.clubd.repository.SlMasterMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 매출내역
 * 
 * @author bae
 *
 */
@Slf4j
@Service
public class SlMasterService {

	@Autowired
    private SlMasterMapper slMasterMapper;

	/**
	 * 매출내역 등록
	 * 
	 * @param slMaster
	 * @return
	 */
	public int insertSlMaster(SlMaster slMaster) {
		return slMasterMapper.insertSlMaster(slMaster);
	}

}

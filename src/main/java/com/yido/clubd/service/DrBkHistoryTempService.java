package com.yido.clubd.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yido.clubd.model.DrBkHistoryTemp;
import com.yido.clubd.repository.DrBkHistoryTempMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 예약내역 임시테이블
 * 
 * @author bae
 *
 */
@Slf4j
@Service
public class DrBkHistoryTempService {

	@Autowired
    private DrBkHistoryTempMapper drBkHistoryTempMapper;

	public List<DrBkHistoryTemp> selectList(Map<String, Object> params) {
		return drBkHistoryTempMapper.selectList(params);
	}
	
	public int insertDrBkHistoryTemp(DrBkHistoryTemp drBkHistoryTemp) {
		return drBkHistoryTempMapper.insertDrBkHistoryTemp(drBkHistoryTemp);
	}
}

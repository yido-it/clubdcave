package com.yido.clubd.service;

import java.util.List;
import java.util.Map;

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

    public List<DrBkMark> selectList(Map<String, Object> map) {
    	return drBkMarkMapper.selectList(map);
    }
    
	/**
	 * [예약 선점용] 테이블에서 '예약 가능한 데이터만 조회'
	 * 
	 * @param map
	 * @return
	 */
	public List<DrBkMark> selectAvailableData(Map<String, Object> map){
    	return drBkMarkMapper.selectAvailableData(map);
	}
	
    
    /**
     * [예약선점용] 테이블 데이터 변경
     * 
     * @param map
     * @return
     */
    public int updateDrBkMark(Map<String, Object> map) {
    	return drBkMarkMapper.updateDrBkMark(map);
    }
}

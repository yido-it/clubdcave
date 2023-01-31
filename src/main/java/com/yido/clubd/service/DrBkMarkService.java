package com.yido.clubd.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yido.clubd.model.BookInfoVO;
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

    public List<DrBkMark> selectList(BookInfoVO bkInfo) {
    	return drBkMarkMapper.selectList(bkInfo);
    }
    
	/**
	 * [예약 선점용] 테이블에서 '예약 가능한 데이터만 조회'
	 * 
	 * @param map
	 * @return
	 */
	public List<DrBkMark> selectAvailableData(BookInfoVO bkInfo){
    	return drBkMarkMapper.selectAvailableData(bkInfo);
	}
	
    /**
     * [예약선점용] 테이블 데이터 변경 (예약완료되었을때)
     * 
     * @param map
     * @return
     */
    public int updateDrBkMark(Map<String, Object> map) {
    	return drBkMarkMapper.updateDrBkMark(map);
    }
    
    /**
     * [예약선점용] 선점
     * 
     * @param map
     * @return
     */
    public int updateMark(Map<String, Object> map) {
    	return drBkMarkMapper.updateMark(map);
    }
    
    /**
     * [예약선점용] 해제
     * 
     * @param map
     * @return
     */
    public int updateUnMark(Map<String, Object> map) {
    	return drBkMarkMapper.updateUnMark(map);
    }
}

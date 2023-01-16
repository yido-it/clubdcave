package com.yido.clubd.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yido.clubd.model.BookInfoVO;
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

	/**
	 * 베이별 잔여시간 조회 
	 * 
	 * @param map
	 * @return
	 */
    public List<DrBkTime> bookAvailableTime(Map<String, Object> map) {
    	return drBkTimeMapper.bookAvailableTime(map);
    }

    public List<DrBkTime> getBkTime(BookInfoVO bkInfo) {
    	return drBkTimeMapper.getBkTime(bkInfo);
    }
    
    /**
     * [예약 타임] 테이블 데이터 변경 
     * 
     * @param map
     * @return
     */
    public int updateBkRemCount(Map<String, Object> map) {
    	return drBkTimeMapper.updateBkRemCount(map);
    }
}

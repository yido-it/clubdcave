package com.yido.clubd.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yido.clubd.model.DrBkMnMap;
import com.yido.clubd.repository.DrBkMnMapMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * 예약 입금 연결 정보
 * 
 * @author bae
 *
 */
@Slf4j
@Service
public class DrBkMnMapService {

	@Autowired
    private DrBkMnMapMapper drBkMnMapMapper;

	/**
	 * 예약번호로 결제 정보 조회
	 * 
	 * @param param
	 * @return
	 */
	public DrBkMnMap getMnMap(DrBkMnMap drBkMnMap) {
    	return drBkMnMapMapper.getMnMap(drBkMnMap);
	}
	
    public int insertDrBkMnMap(DrBkMnMap drBkMnMap) {
    	return drBkMnMapMapper.insertDrBkMnMap(drBkMnMap);
    }
}

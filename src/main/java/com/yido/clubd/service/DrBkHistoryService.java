package com.yido.clubd.service;

import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.yido.clubd.common.utils.SessionVO;
import com.yido.clubd.model.DrBkHistory;
import com.yido.clubd.model.DrBkHistoryLog;
import com.yido.clubd.repository.DrBkHistoryLogMapper;
import com.yido.clubd.repository.DrBkHistoryMapper;
import com.yido.clubd.repository.DrCostInfoMapper;
import com.yido.clubd.repository.DrMsMaininfoMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 예약내역
 * 
 * @author bae
 *
 */
@Slf4j
@Service
public class DrBkHistoryService {

	@Autowired
    private DrBkHistoryMapper drBkHistoryMapper;
	
	@Autowired
    private DrBkHistoryLogMapper drBkHistoryLogMapper;
	
	@Autowired
    private DrCostInfoMapper drCostInfoMapper;

	/**
	 * 예약고유번호 채번
	 * 
	 * @param params
	 * @return
	 */
	public String getSerialNo(Map<String, Object> params) {
		return drBkHistoryMapper.getSerialNo(params);
	}
	
	/**
	 * 예약내역 등록
	 * 
	 * @param drBkHistory
	 * @return
	 */
	public int insertDrBkHistory(Map<String, Object> params) {
		return drBkHistoryMapper.insertDrBkHistory(params);
	}

	/**
	 * 예약 등록
	 *  
	 * @param params
	 * @param sessionVO
	 * @throws Exception
	 */
	@Transactional
	public void actionReservationLogicQuery(@RequestParam Map<String, Object> param, SessionVO sessionVO) throws Exception {
		
		log.info("[actionReservationLogicQuery] params : " + param);

		String tmpPhone = param.get("phone") != null ? param.get("phone").toString() : "";
		if (!tmpPhone.equals("") && tmpPhone.indexOf("-") > 0) {
			String phone[] = tmpPhone.split("-");
			param.put("bkFirstPhone", phone[0]);
			param.put("bkMidPhone", phone[1]);
			param.put("bkLastPhone", phone[2]);
		}
		
		// 요금정보 조회 및 매핑 
		Map<String, Object> map = drCostInfoMapper.getCostInfo(param);
		param.put("costDiv", map.get("CO_DIV").toString());
		param.put("costName", map.get("COST_NAME").toString());
		param.put("costCd", map.get("COST_CD").toString());
		param.put("costCoDiv", map.get("COST_DIV").toString());
		
		// 예약 내역 insert
		drBkHistoryMapper.insertDrBkHistory(param);
		
		// 예약 내역 로그 insert
		param.put("logDiv", "I");
		drBkHistoryLogMapper.insertDrBkHistoryLog(param);
		
	}
}

package com.yido.clubd.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yido.clubd.common.repository.CommonMapper;
import com.yido.clubd.common.utils.SessionVO;
import com.yido.clubd.model.BookInfoVO;
import com.yido.clubd.model.DrBkHistory;
import com.yido.clubd.model.DrBkMnMap;
import com.yido.clubd.repository.DrBkHistoryLogMapper;
import com.yido.clubd.repository.DrBkHistoryMapper;
import com.yido.clubd.repository.DrBkMarkMapper;
import com.yido.clubd.repository.DrBkMnMapMapper;
import com.yido.clubd.repository.DrCostInfoMapper;
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
    private VoucherService voucherService;
	
	@Autowired
    private DrBkHistoryMapper drBkHistoryMapper;
	
	@Autowired
    private DrBkHistoryLogMapper drBkHistoryLogMapper;
	
	@Autowired
    private DrCostInfoMapper drCostInfoMapper;

	@Autowired
    private DrBkMarkMapper drBkMarkMapper;
	
	@Autowired
    private DrBkMnMapMapper drBkMnMapMapper;

	/**
	 * 예약고유번호 채번
	 * 
	 * @param params
	 * @return
	 */
	public String getSerialNo(Map<String, Object> param) {
		return drBkHistoryMapper.getSerialNo(param);
	}
	
	public List<DrBkHistory> selectList(Map<String, Object> param) {
		return drBkHistoryMapper.selectList(param);
	}

	/**
	 * 고객 예약내역 확인 
	 * 
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> selectBkHis(Map<String, Object> param) {
		return drBkHistoryMapper.selectBkHis(param);
	}
	
	// 가장 최근 대표예약고유번호 조회 (조건 : 회원번호)
	public String selectCalcSNo(Map<String, Object> param) {
		return drBkHistoryMapper.selectCalcSNo(param);
	}
	
	/**
	 * 예약내역 등록
	 * 
	 * @param drBkHistory
	 * @return
	 */
	public int insertDrBkHistory(Map<String, Object> param) {
		return drBkHistoryMapper.insertDrBkHistory(param);
	}

	/**
	 * 금액 변경
	 * 
	 * @param drBkHistory
	 * @return
	 */
	public int updateBkAmount(DrBkHistory drBkHistory) {
		return drBkHistoryMapper.updateBkAmount(drBkHistory);
	}

	/**
	 * 상태 변경
	 * 
	 * @param drBkHistory
	 * @return
	 */
	public int updateBkState(DrBkHistory drBkHistory) {
		return drBkHistoryMapper.updateBkState(drBkHistory);
	}
	
	/**
	 * 예약내역 insert, 예약내역 로그 insert, 예약선점 update, 예약-입금 연결 정보 insert
	 * 
	 * @param param
	 * @param mnMap
	 * @throws Exception
	 */
	@Transactional
	public Map<String, Object> actionReservation(@RequestParam Map<String, Object> param, DrBkMnMap mnMap) throws Exception {
		/*
		 * param
		 * 
		 * ipAddr=127.0.0.1, SERVICE_CODE=0900, SERVICE_ID=glx_api, ORDER_ID=123456789123_20230126153737
		 * , ORDER_DATE=20230126153737, PAY_MESSAGE=
		 * , coDiv=001, mnInDay=20230126, mnRevAmount=50000, mnInAmount=50000, mnChangeAmount=0
		 * , mnInNo=52414400****085*, mnCardApproval=26153757, mnMonth=0, mnAppDate=20230126
		 * , cardCompanyCode=0084, orderId=123456789123_20230126153737, transactionId=2023012615TT009139
		 * , cancelKey=7f5418dee22d9566099e34992c144503b1210980, mnCancelYn=N, mnSeq=1, msNum=123456789123
		 * , bkDay=20230201, bkTime=0900, 1000, bkAmount=50000, bayCondi=001, msLevel=11, userName=배은화
		 * , userMail=, phone=010-9989-9655, msId=baeean
		 * , vList=[{"coDiv":"001","quantity":"1","saleDay":"20230119","saleSeq":"3"}], serialNo=20230126151521}
		 * */
		Map<String, Object> returnMap = new HashMap<String, Object>();
		log.info("[actionReservation] params : " + param);
		
		// 이용권 사용여부 
		String isVoucherUse = param.get("isVoucherUse").toString();
		
		String tmpPhone = param.get("phone") != null ? param.get("phone").toString() : "";
		if (!tmpPhone.equals("") && tmpPhone.indexOf("-") > 0) {
			String phone[] = tmpPhone.split("-");
			param.put("bkFirstPhone", phone[0]);
			param.put("bkMidPhone", phone[1]);
			param.put("bkLastPhone", phone[2]);
		}
				
		// 요금정보 조회 및 매핑 
		if (param.get("bkTime").toString().indexOf(",") > 0) {
			String[] strArr = (param.get("bkTime")).toString().split(",");				
			List<String> bkList = new ArrayList<String>();
			for (String str : strArr) bkList.add(str); 
			param.put("bkList", bkList);	
		} else {
			param.put("bkTime", param.get("bkTime").toString());
		}
				
		Map<String, Object> map = drCostInfoMapper.getCostInfo(param);
		param.put("costDiv"		, map.get("CO_DIV").toString());
		param.put("costName"	, map.get("COST_NAME").toString());
		param.put("costCd"		, map.get("COST_CD").toString());
		param.put("costCoDiv"	, map.get("COST_DIV").toString());
		// end.
		param.put("bkState", "1"); 		// 1 : 정상, 3 : 취소 
		
		// 선점용 map
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("coDiv"		, param.get("coDiv"));
		map2.put("bayCondi"		, param.get("bayCondi"));
		map2.put("bkDay"		, param.get("bkDay"));
		map2.put("entryUser"	, param.get("msId"));
		map2.put("msNum"		, param.get("msNum"));
		map2.put("bkStateIsY"	, "Y");
		map2.put("bkState"		, "N");
		// end.

		List<Object> arrSerialNo = new ArrayList<Object>();
		String calcSerialNo = "";		// 대표 예약고유번호 
		
		if (param.get("bkTime").toString().indexOf(",") > 0) {
			// 다건 예약
			log.info("[actionReservation] 다건 예약");
			String bkTime[] = param.get("bkTime").toString().split(",");

			// 시간별 이용요금 계산 
			int bkAmt = Integer.parseInt(param.get("oriBkAmount").toString());
			int timeAmt = bkAmt / bkTime.length; 
			param.put("bkAmount", timeAmt);
		
			for (int i = 0; i < bkTime.length; i++) {
				String sBkTime = bkTime[i].trim();
				param.put("bkTime", sBkTime);
				
				// 건별로 예약번호 취득 
				String bkSerialNo = drBkHistoryMapper.getSerialNo(param);
				if ( i == 0 ) calcSerialNo = bkSerialNo;
				param.put("bkSerialNo", bkSerialNo);
				param.put("calcSerialNo", calcSerialNo);	
				arrSerialNo.add(bkSerialNo);

				// 예약 내역 insert				
				drBkHistoryMapper.insertDrBkHistory(param);

				// 예약 내역 로그 insert
				Map<String, Object> param2 = new HashMap<String, Object>();
				param2.put("bkSerialNo", bkSerialNo);
				List<DrBkHistory> bList = drBkHistoryMapper.selectList(param2);
				DrBkHistory bkLog = bList.get(0);
				bkLog.setLogDiv("I");
				drBkHistoryLogMapper.insertDrBkHistoryLog(bkLog);
				// end.
				
				// 예약선점 테이블 변경 (예약고유번호, 상태)
				// 조회조건 : 지점코드, 베이코드, 일자, 시간, 아이디, 상태 Y인거 
				map2.put("bkSerialNo", bkSerialNo);
				map2.put("bkTime", sBkTime);
				drBkMarkMapper.updateDrBkMark(map2);	
				 
				if (mnMap != null && mnMap.getMnInDay() != null) {
					// 예약-입금 연결 정보 insert
					// 건수가 여러개이면 순번, 예약고유번호 다르게 들어가야함 
					mnMap.setBkSerialNo(bkSerialNo);
					drBkMnMapMapper.insertDrBkMnMap(mnMap);		
				}			
			}
			
		} else {
			// 단건 예약 
			log.info("[actionReservation] 단건 예약");
			
			String bkSerialNo = drBkHistoryMapper.getSerialNo(param);
			calcSerialNo = bkSerialNo;
			param.put("bkSerialNo", bkSerialNo);
			param.put("calcSerialNo", calcSerialNo);	// 대표 예약고유번호
			arrSerialNo.add(bkSerialNo);

			// 시간별 이용요금  
			int bkAmt = Integer.parseInt(param.get("oriBkAmount").toString());
			param.put("bkAmount", bkAmt);
			
			// 예약 내역 insert
			drBkHistoryMapper.insertDrBkHistory(param);
			
			// 예약 내역 로그 insert
			Map<String, Object> param2 = new HashMap<String, Object>();
			param2.put("bkSerialNo", bkSerialNo);
			List<DrBkHistory> bList = drBkHistoryMapper.selectList(param2);
			DrBkHistory bkLog = bList.get(0);
			bkLog.setLogDiv("I");
			drBkHistoryLogMapper.insertDrBkHistoryLog(bkLog);
			// end.
			
			// 예약선점 테이블 변경 (예약고유번호, 상태)
			// 조회조건 : 지점코드, 베이코드, 일자, 시간, 아이디, 상태 Y인거 
			map2.put("bkSerialNo", bkSerialNo);
			drBkMarkMapper.updateDrBkMark(map2);	

			if (mnMap != null && mnMap.getMnInDay() != null) {
				// 예약-입금 연결 정보 insert
				// 건수가 여러개이면 순번, 예약고유번호 다르게 들어가야함 
				mnMap.setBkSerialNo(bkSerialNo);
				drBkMnMapMapper.insertDrBkMnMap(mnMap);	
			}

		}	

		returnMap.put("calcSerialNo", calcSerialNo);	
		returnMap.put("arrSerialNo", arrSerialNo);		// 예약고유번호 배열 (이용권 사용내역 처리할때 필요함)
		
		return returnMap;
	}

}

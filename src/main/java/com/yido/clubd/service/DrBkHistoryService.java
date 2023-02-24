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
import com.yido.clubd.model.DrBkMark;
import com.yido.clubd.model.DrBkMnMap;
import com.yido.clubd.repository.DrBkHistoryLogMapper;
import com.yido.clubd.repository.DrBkHistoryMapper;
import com.yido.clubd.repository.DrBkMarkMapper;
import com.yido.clubd.repository.DrBkMnMapMapper;
import com.yido.clubd.repository.DrCostInfoMapper;
import com.yido.clubd.repository.DrVoucherSaleMapper;
import com.yido.clubd.repository.MnInHistoryMapper;

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
	
	@Autowired
    private DrVoucherSaleMapper drVoucherSaleMapper;
	
	@Autowired
    private MnInHistoryMapper mnInHistoryMapper;
	
	
	/**
	 * 예약고유번호 채번
	 * 
	 * @param params
	 * @return
	 */
	public String getSerialNo() {
		return drBkHistoryMapper.getSerialNo();
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

	/**
	 * 예약내역 > 이용권 사용내역
	 * 
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> selectVoucherList(Map<String, Object> param) {
		return drBkHistoryMapper.selectVoucherList(param);
	}
	
	/**
	 * 가장 최근 대표예약고유번호 조회 (조건 : 회원번호)
	 * 
	 * @param param
	 * @return
	 */
	public String selectCalcSNo(Map<String, Object> param) {
		return drBkHistoryMapper.selectCalcSNo(param);
	}
	

	/**
	 * 예약 갯수 조회 (조건 : 회원번호 & 상태 : 취소,노쇼,정산완료 아닌 것) 
	 * 
	 * @param drBkHistory
	 * @return
	 */
	public int getBkCnt(String msNum) {
		return drBkHistoryMapper.getBkCnt(msNum);
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
	public Map<String, Object> actionReservation(@RequestParam Map<String, Object> param, SessionVO session) throws Exception {

		Map<String, Object> returnMap = new HashMap<String, Object>();
		log.debug("[actionReservation] params: " + param);

		param.put("bkState"			, "1"); 		// 예약상태 > 1 : 정상, 3 : 취소 
		param.put("bkFirstPhone"	, session.getMsFirstPhone1());
		param.put("bkMidPhone"		, session.getMsMidPhone1());
		param.put("bkLastPhone"		, session.getMsLastPhone1());
		param.put("msNum"			, session.getMsNum());
		param.put("bkName"			, session.getMsName());	// 예약자 이름 
		
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
		
		// 선점용 
		BookInfoVO bkInfo = new BookInfoVO();
		bkInfo.setCoDiv(param.get("coDiv").toString());
		bkInfo.setBayCondi(param.get("bayCondi").toString());
		bkInfo.setBkDay(param.get("bkDay").toString());
		bkInfo.setMsId(session.getMsId());
		bkInfo.setBkState("N"); // 선점 된 것중에 msId null 인거 가져와야함 
		
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("coDiv"		, bkInfo.getCoDiv());
		map2.put("bayCondi"		, bkInfo.getBayCondi());
		map2.put("bkDay"		, bkInfo.getBkDay());
		map2.put("entryUser"	, bkInfo.getMsId());
		map2.put("msNum"		, session.getMsNum());
		// end.
		
		// 예약 입금 연결 정보				
		DrBkMnMap mnMap = new DrBkMnMap();
		if (param.get("mnSeq") != null) {
			mnMap.setCoDiv(param.get("coDiv").toString());
			mnMap.setMnCoDiv(param.get("coDiv").toString());
			mnMap.setMnInDay(param.get("mnInDay").toString()); 
			mnMap.setMnSeq(Integer.parseInt(param.get("mnSeq").toString()));  
			mnMap.setMnAmount(Integer.parseInt(param.get("mnInAmount").toString()));  
		}
		// end.

		List<Object> arrSerialNo = new ArrayList<Object>();
		String calcSerialNo = "";		// 대표 예약고유번호 
		
        // PG 결제내역 등록
		if (param.get("mnSeq") != null ) {
	        param.put("mnSeq"		, param.get("mnSeq"));
	        param.put("msNum"		, session.getMsNum());
	        param.put("mnInName"	, session.getMsName());		// 결제자 이름

	        mnInHistoryMapper.insertMnInHistory(param);
		}
        // end.
		
        
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
				String bkSerialNo = drBkHistoryMapper.getSerialNo();
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
	
				// 해당 아이디로 예약 선점한거 가져오기 (BK_SEQ 필요함)
	    		bkInfo.setBkTime(sBkTime);
				List<DrBkMark> markList = drBkMarkMapper.selectList(bkInfo);
				log.debug("[actionReservation] 해당 아이디로 예약 선점한거 조회 > 결과 : {}", markList);
				// end.
				if (markList != null && markList.size() > 0) {
					// 예약선점 테이블 변경 (예약고유번호, 상태)
					// 조회조건 : 지점코드, 베이코드, 일자, 시간, 아이디, 상태 Y인거 
					map2.put("bkSerialNo"	, bkSerialNo);
					map2.put("bkTime"		, sBkTime);
					map2.put("bkSeq"		, markList.get(0).getBkSeq());
					drBkMarkMapper.updateDrBkMark(map2);	
				}
				 
				if (param.get("mnSeq") != null) {
					// 예약-입금 연결 정보 insert
					// 건수가 여러개이면 순번, 예약고유번호 다르게 들어가야함 
					mnMap.setBkSerialNo(bkSerialNo);
					drBkMnMapMapper.insertDrBkMnMap(mnMap);		
				}			
			}
			
		} else {
			// 단건 예약 
			log.info("[actionReservation] 단건 예약");
			
			String bkSerialNo = drBkHistoryMapper.getSerialNo();
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
			
			// 해당 아이디로 예약 선점한거 가져오기 (BK_SEQ 필요함)
    		bkInfo.setBkTime(param.get("bkTime").toString());
			List<DrBkMark> markList = drBkMarkMapper.selectList(bkInfo);
			log.debug("[actionReservation] 해당 아이디로 예약 선점한거 조회 > 결과 : {}", markList);
			// end.
			if (markList != null && markList.size() > 0) {
				// 예약선점 테이블 변경 (예약고유번호, 상태)
				// 조회조건 : 지점코드, 베이코드, 일자, 시간, 아이디, 상태 Y인거 
				map2.put("bkSerialNo"	, bkSerialNo);
				map2.put("bkTime"		, param.get("bkTime"));
				map2.put("bkSeq"		, markList.get(0).getBkSeq());
				drBkMarkMapper.updateDrBkMark(map2);	
			}
			
			if (param.get("mnSeq") != null) {
				// 예약-입금 연결 정보 insert
				// 건수가 여러개이면 순번, 예약고유번호 다르게 들어가야함 
				mnMap.setBkSerialNo(bkSerialNo);
				drBkMnMapMapper.insertDrBkMnMap(mnMap);	
			}
		}	
	
		returnMap.put("arrSerialNo", arrSerialNo);		// 예약고유번호 배열 (이용권 사용내역 처리할때 필요함)
		
		return returnMap;
	}
}

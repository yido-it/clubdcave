package com.yido.clubd.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yido.clubd.common.repository.CommonMapper;
import com.yido.clubd.common.utils.ResultVO;
import com.yido.clubd.common.utils.SessionVO;
import com.yido.clubd.model.BookInfoVO;
import com.yido.clubd.model.CdCommon;
import com.yido.clubd.model.DrBkHistory;
import com.yido.clubd.model.DrBkHistoryTemp;
import com.yido.clubd.model.DrBkMark;
import com.yido.clubd.model.DrBkTime;
import com.yido.clubd.repository.DrBkHistoryLogMapper;
import com.yido.clubd.repository.DrBkHistoryMapper;
import com.yido.clubd.repository.DrBkMarkMapper;
import com.yido.clubd.repository.DrBkTimeMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BookService {

	@Autowired
    private DrBkHistoryService drBkHistoryService;
	
	@Autowired
    private MnInHistoryService mnInHistoryService;
	
	@Autowired
    private DrBkTimeMapper drBkTimeMapper;
	
	@Autowired
    private DrBkHistoryMapper drBkHistoryMapper;
	
	@Autowired
    private DrBkHistoryLogMapper drBkHistoryLogMapper;

	@Autowired
    private DrBkMarkMapper drBkMarkMapper;
	
	@Autowired
    private CommonMapper cdCommonMapper;
	
	
	
	/**
	 * 예약가능여부 체크 
	 * -> 예약 가능하다면 예약선점 처리 + 임시테이블에 데이터 저장
	 * -> 예약 불가하다면 중단 처리 
	 *  
	 * @param params
	 * @return
	 */
	@Transactional
    public ResultVO chkBookLogic(BookInfoVO bkInfo) throws Exception  {
    	ResultVO result = new ResultVO();
    	String serialNo = "";
		LocalDateTime nowDt = LocalDateTime.now();
    	
        try {
        	log.info("[chkBookLogic] bookInfo : " + bkInfo);

        	// 예약 불가능한 시간 
        	List<String> strArr = new ArrayList<String>();
        	for (Map<String, Object> bk : bkInfo.getBkList()) {
        		String bkTime = bk.get("bkTime").toString();
        		
    			// DR_BK_MARK 예약선점 데이터 체크
        		bkInfo.setBkTime(bkTime.replace(":", ""));
    			List<DrBkMark> markList = drBkMarkMapper.selectAvailableData(bkInfo);
    			log.info("[chkBookLogic] 예약선점 테이블 조회 건수 > {}", markList.size());
    			
    			if (markList == null || markList.size() == 0) {
    				// 예약 가능한 데이터 없을 경우
    				strArr.add(bkTime);
    			} 
			}
			log.info("[chkBookLogic] strArr : {}", strArr);
			
        	if (strArr.size() > 0) {
        		result.setCode("9999");
				result.setMessage("죄송합니다. 예약 마감된 시간이 존재합니다.");	
				result.setData(strArr);
				return result;
        	}
        	
		/*
				// 예약 가능한 데이터 있는 경우, 선점 처리
				Map<String, Object> param = new HashMap<String, Object>();

				// 선점 시간 조회 
				CdCommon common = new CdCommon();
				common.setCoDiv(bkInfo.getCoDiv());
				common.setCdDivision("999");
				common.setCdCode("DRC03");
				common = cdCommonMapper.getCommonCode(common);
				Integer eTime = common != null && !common.getCdLength().equals("") ? 
						Integer.parseInt(common.getCdLength()) : 15;
				// end.
				
				param.put("entryMethod"		, "모바일");	
				param.put("updMsId"			, bkInfo.getMsId());		
				param.put("ipAddr"			, bkInfo.getIpAddr());		
				param.put("entryTime"		, eTime);			
				
				param.put("coDiv"			, bkInfo.getCoDiv());
				param.put("bayCondi"		, bkInfo.getBayCondi());
				param.put("bkDay"			, bkInfo.getBkDay());
				param.put("bkTime"			, bkInfo.getBkTime());
				
				param.put("bkSeq"			, markList.get(0).getBkSeq());	
				
				// (DR_BK_MARK) 선점 처리
				drBkMarkMapper.updateMark(param);
				
				// (DR_BK_TIME) 잔여수량 차감 
				param.put("minusRemCnt", "Y");
				drBkTimeMapper.updateBkRemCount(param);
			
						
			
			// 시리얼번호 생성 (YYMMDDHHMMSS)
			// 임시 테이블 PK : 지점코드 + 시리얼번호 + 회원번호
			serialNo = nowDt.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
			
			// 예약시간 
			List<Map<String, Object>> bkList = bInfo.getBkList();
			bkList.sort(
					Comparator.comparing((Map<String, Object> map) -> (String) map.get("timeIdx"))
				);
			
			String bkTime = "";
			String bkTime2 = "";
			for (Map<String, Object> bk : bkList) {
				String tmpTime = bk.get("bkTime").toString(); // 1100
				if (bkTime.equals("")) {
					bkTime = tmpTime.substring(0, 2) + ":" + tmpTime.substring(2);
					bkTime2 = tmpTime;
				} else {
					bkTime += ", " + tmpTime.substring(0, 2) + ":" + tmpTime.substring(2);
					bkTime2 += ", " + tmpTime;
				}
			}
			
			DrBkHistoryTemp temp = new DrBkHistoryTemp();
			temp.setCoDiv(coDiv);
			temp.setSerialNo(serialNo);
			temp.setMsNum(sessionVO.getMsNum());
			temp.setBayCd(bInfo.getBayCondi());
			temp.setBkDay(bInfo.getBkDay());
			temp.setBkTime(bkTime);
			temp.setBkTime2(bkTime2);
			temp.setInputIp(ipAddr);
			
			// 임시 테이블에 데이터 저장
			drBkHistoryTempService.insertDrBkHistoryTemp(temp);
			result.setData(serialNo);*/
		} catch(Exception e) {
			e.printStackTrace();
			result.setCode("9999");
			result.setMessage("서버와의 통신에 오류가 있습니다.");
		}
    	return result;
    }
	
	/**
	 * 예약 선점해제 
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@Transactional
    public ResultVO unBkMarkLogic(BookInfoVO bkInfo) throws Exception  {
    	ResultVO result = new ResultVO();

		log.info("[unBkMarkLogic] bkInfo : {}", bkInfo);
		
    	try {
			// 해당 아이디로 예약 선점한거 가져오기 (BK_SEQ 필요함)
			List<DrBkMark> markList = drBkMarkMapper.selectList(bkInfo);
			log.info("[unBkMarkLogic] 해당 아이디로 예약 선점한거 조회 > 결과 : {}", markList);
			// end.
	
			if (markList != null && markList.size() > 0) {

				Map<String, Object> param = new HashMap<String, Object>();
								
				param.put("coDiv"			, bkInfo.getCoDiv());
				param.put("bayCondi"		, bkInfo.getBayCondi());
				param.put("bkDay"			, bkInfo.getBkDay());
				param.put("bkTime"			, bkInfo.getBkTime());
				param.put("bkSeq"			, markList.get(0).getBkSeq());
				
				// 예약 선점 해제 
				drBkMarkMapper.updateUnMark(param);
				
				// 잔여수량 복원 
				param.put("addRemCnt", "Y");
				drBkTimeMapper.updateBkRemCount(param);
			}   	
		} catch(Exception e) {
			e.printStackTrace();
			result.setCode("9999");
			result.setMessage("서버와의 통신에 오류가 있습니다.");
		}	    	
    	return result;
	}
	
	/**
	 * 예약 취소 
	 * - 결제 취소
	 * - 예약 취소 
	 * 
	 * @param bInfo
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public ResultVO bookCancel(BookInfoVO bInfo) throws Exception {	
		ResultVO resultVO = new ResultVO();

		// 예약내역 조회 (예약고유번호 획득)
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("calcSerialNo", bInfo.getCalcSerialNo());
		List<DrBkHistory> bList = drBkHistoryService.selectList(param);		
		log.info("[bookCancel] 예약건수: " + bList.size());
		
		if (bList.size() <= 0) {
			resultVO.setCode("9999");
			resultVO.setMessage("예약 정보가 존재하지 않습니다.");
		}
		
		for (DrBkHistory bk : bList) {
			// 결제취소 (주문번호(orderId), 승인금액, 거래번호(transactionId), 취소키 필요)
			// mnInHistoryService.cancelLogic(bInfo.getCoDiv(), orderId, authAmount, transactionId, "C", cancelKey, ipAddr);
	
		}


		
		// 입금내역 원본컬럼에 데이터 넣기 
		
		// 예약취소 
		return resultVO;
	}
	
	/**
	 * 예약 취소
	 * - 상태 변경 + 로그
	 * - 예약타임 수량 복원
	 * - 예약선점 데이터 초기화 
	 * 
	 * @param bInfo
	 * @param bList
	 * @throws Exception
	 */
	@Transactional
	public void cancelBook(BookInfoVO bInfo, List<DrBkHistory> bList) throws Exception {	

		Map<String, Object> param = new HashMap<String, Object>();
		
		// 예약내역 상태 변경 
		for (DrBkHistory bk : bList) {
			bk.setBkState("3");		// 3: 취소
			drBkHistoryMapper.updateBkState(bk);
			
			// 예약내역 로그 
			bk.setLogDiv("U");	
			drBkHistoryLogMapper.insertDrBkHistoryLog(bk);
			
			bInfo.setCoDiv(bk.getCoDiv());
			bInfo.setBayCondi(bk.getBayCondi());
			bInfo.setBkDay(bk.getBkDay());
			bInfo.setBkTime(bk.getBkTime());
			// 예약타임 수량 복원 + 예약선점 데이터 초기화 
			this.unBkMarkLogic(bInfo);
		}
	}
	

}

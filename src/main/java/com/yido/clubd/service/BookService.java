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
import com.yido.clubd.model.DrBkMnMap;
import com.yido.clubd.model.DrBkTime;
import com.yido.clubd.model.DrVoucherUse;
import com.yido.clubd.model.MnInHistory;
import com.yido.clubd.repository.DrBkHistoryLogMapper;
import com.yido.clubd.repository.DrBkHistoryMapper;
import com.yido.clubd.repository.DrBkHistoryTempMapper;
import com.yido.clubd.repository.DrBkMarkMapper;
import com.yido.clubd.repository.DrBkMnMapMapper;
import com.yido.clubd.repository.DrBkTimeMapper;
import com.yido.clubd.repository.DrVoucherUseMapper;
import com.yido.clubd.repository.MnInHistoryMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BookService {

	@Autowired
    private DrBkHistoryService drBkHistoryService;
	
	@Autowired
    private VoucherService voucherService;
	
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
	
	@Autowired
    private DrBkHistoryTempMapper drBkHistoryTempMapper;
	
	@Autowired
    private DrBkMnMapMapper drBkMnMapMapper;
	
	@Autowired
    private MnInHistoryMapper mnInHistoryMapper;
	
	@Autowired
    private DrVoucherUseMapper drVoucherUseMapper;

	
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
		List<DrBkMark> markList = new ArrayList<DrBkMark>();
		
        try {
        	log.debug("[chkBookLogic] bookInfo : " + bkInfo);
        	List<Map<String, Object>> bkList = bkInfo.getBkList();

        	// 예약 불가능한 시간 
        	List<String> strArr = new ArrayList<String>();
        	for (Map<String, Object> bk : bkList) {
        		String bkTime = bk.get("bkTime").toString();
        		
    			// DR_BK_MARK 예약선점 데이터 체크
        		bkInfo.setBkTime(bkTime.replace(":", ""));
    			markList = drBkMarkMapper.selectAvailableData(bkInfo);
    			log.debug("[chkBookLogic] 예약선점 테이블 조회 건수 > {}", markList.size());
    			
    			if (markList == null || markList.size() == 0) {
    				// 예약 가능한 데이터 없을 경우
    				strArr.add(bkTime);
    			} 
			}
			log.info("[chkBookLogic] 예약 불가능한 시간 : {}", strArr);
			
        	if (strArr.size() > 0) {
        		result.setCode("9999");
				result.setMessage("죄송합니다. 예약 마감된 시간이 존재합니다.");	
				result.setData(strArr);
				return result;
        	}
        	
        	// ┌────────────────── 선점 처리 ──────────────────┐
        	for (Map<String, Object> bk : bkList) {
        		
        		String bkTime = bk.get("bkTime").toString();
				Map<String, Object> param = new HashMap<String, Object>();
	
				// 선점 시간 조회 ( 15분 )
				CdCommon common = new CdCommon();
				common.setCoDiv(bkInfo.getCoDiv());
				common.setCdDivision("999");
				common.setCdCode("DRC03");
				common = cdCommonMapper.getCommonCode(common);
				Integer eTime = common != null && !common.getCdLength().equals("") ? 
						Integer.parseInt(common.getCdLength()) : 15;
				// end.
				
				param.put("entryMethod"		, "M");		// M: 모바일	
				param.put("bkStateIsYn"		, "Y");		// 예약가능한거 찾아서 선점처리 
				param.put("updMsId"			, bkInfo.getMsId());		
				param.put("ipAddr"			, bkInfo.getIpAddr());		
				param.put("entryTime"		, eTime);			
				
				param.put("coDiv"			, bkInfo.getCoDiv());
				param.put("bayCondi"		, bkInfo.getBayCondi());
				param.put("bkDay"			, bkInfo.getBkDay());
				param.put("bkTime"			, bkTime.replace(":", ""));
				
				param.put("bkSeq"			, markList.get(0).getBkSeq());	
				
				// (DR_BK_MARK) 선점 처리
				drBkMarkMapper.updateMark(param);
				
				// (DR_BK_TIME) 잔여수량 차감 
				param.put("minusRemCnt", "Y");
				drBkTimeMapper.updateBkRemCount(param);
			
        	}
        	// └────────────────── 선점 처리 ──────────────────┘
        	
        	// ┌───────────── 임시테이블에 예약 데이터 저장 ───────────┐
			// 시리얼번호 생성 (YYMMDDHHMMSS)
			// 임시 테이블 PK : 지점코드 + 시리얼번호 + 회원번호
			serialNo = nowDt.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
			
			// 예약시간 
			bkList.sort(Comparator.comparing((Map<String, Object> map) -> (String) map.get("bkTime")));
			
			String bkTime = "";
			String bkTime2 = "";
			for (Map<String, Object> bk : bkList) {
				String tmpTime = bk.get("bkTime").toString(); 
				if (bkTime.equals("")) {
					bkTime = tmpTime;
					bkTime2 = tmpTime.replace(":", "");
				} else {
					bkTime += ", " + tmpTime;
					bkTime2 += ", " + tmpTime.replace(":", "");
				}
			}
			
			DrBkHistoryTemp temp = new DrBkHistoryTemp();
			temp.setCoDiv(bkInfo.getCoDiv());
			temp.setSerialNo(serialNo);
			temp.setMsNum(bkInfo.getMsNum());
			temp.setBayCd(bkInfo.getBayCondi());
			temp.setBkDay(bkInfo.getBkDay());
			temp.setBkTime(bkTime);
			temp.setBkTime2(bkTime2);
			temp.setInputIp(bkInfo.getIpAddr());
			
			// 임시 테이블에 데이터 저장
			drBkHistoryTempMapper.insertDrBkHistoryTemp(temp);
			result.setData(serialNo);
        	// └───────────── 임시테이블에 예약 데이터 저장 ───────────┘
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

    	log.debug("[unBkMarkLogic] bkInfo : {}", bkInfo);
		
    	try {
    		
    		if (bkInfo.getBkTime().indexOf(",") > 0) {
    			// 다건 예약
    			
        		String[] strArr = (bkInfo.getBkTime()).split(",");				
        		bkInfo.setBkTime("");
        		for (String str : strArr) {
        			// 해당 아이디로 예약 선점한거 가져오기 (BK_SEQ 필요함)
            		bkInfo.setBkTime(str.trim());
        			List<DrBkMark> markList = drBkMarkMapper.selectList(bkInfo);
        			log.debug("[unBkMarkLogic] 해당 아이디로 예약 선점한거 조회 > 결과 : {}", markList);
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
        		}
    		} else {
    			// 단건 예약
    			
    			// 해당 아이디로 예약 선점한거 가져오기 (BK_SEQ 필요함)
    			List<DrBkMark> markList = drBkMarkMapper.selectList(bkInfo);
    			log.debug("[unBkMarkLogic] 해당 아이디로 예약 선점한거 조회 > 결과 : {}", markList);
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
    		}
    		
    		if (bkInfo.getTempDelYn() != null && bkInfo.getTempDelYn().equals("Y")) {
    			// 임시테이블 데이터 삭제
    			DrBkHistoryTemp bkTemp = new DrBkHistoryTemp();
    			bkTemp.setSerialNo(bkInfo.getTempSerialNo());
    			drBkHistoryTempMapper.deleteHistoryTemp(bkTemp);
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
	 * - 이용권 취소
	 * 
	 * @param bInfo
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public ResultVO bookCancel(BookInfoVO bInfo) throws Exception {	
		ResultVO resultVO = new ResultVO();
		
		// 예약정보 조회 (예약취소시 필요)
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("calcSerialNo", bInfo.getCalcSerialNo());
		List<DrBkHistory> bList = drBkHistoryService.selectList(param);		
		log.debug("[bookCancel] 예약건수: " + bList.size());
		
		if (bList.size() <= 0) {
			resultVO.setCode("9999");
			resultVO.setMessage("예약 정보가 존재하지 않습니다.");
		}
		
		// 대표예약번호로 [DR_BK_MN_MAP] 조회해서 [MN_IN_HISTORY]의 MN_SEQ 획득
		DrBkMnMap mnMap = new DrBkMnMap();
		mnMap.setBkSerialNo(bInfo.getCalcSerialNo());
		mnMap = drBkMnMapMapper.getMnMap(mnMap);
		
		MnInHistory mnHis = new MnInHistory();
		mnHis.setMnSeq(mnMap.getMnSeq());
		mnHis = mnInHistoryMapper.getMnInHistory(mnHis);
		
		String orderId = mnHis.getOrderId();
		String authAmount = String.valueOf(mnHis.getMnInAmount());
		String transactionId = mnHis.getTransactionId();
		String cancelKey = mnHis.getCancelkey();
		String ipAddr = bInfo.getIpAddr();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coDiv"		, bInfo.getCoDiv());
		map.put("orderId"	, orderId);
		int mnSeq = mnInHistoryMapper.getMnSeq(map);
		
		log.info("[bookCancel] 주문번호(orderId): {}, 승인금액: {}, 거래번호(transactionId): {}, 취소키: {}"
				, orderId, authAmount, transactionId, cancelKey);
		
		// PG 결제 취소 처리 
		Map<String, Object> cMap = new HashMap<String, Object>();
		cMap.put("coDiv", bInfo.getCoDiv());
		cMap.put("orderId", orderId);
		cMap.put("mnSeq", mnSeq);
		cMap.put("authAmount", authAmount);
		cMap.put("transactionId", transactionId);
		cMap.put("cancelKey", cancelKey);
		cMap.put("ipAddr", ipAddr);
		cMap.put("cancelType", "C");
		cMap.put("mnHis", mnHis);
		
		mnInHistoryService.cancelLogic(cMap);

		// 예약취소 
		this.cancelBook(bInfo, bList, cMap);
		
		// 이용권 사용 취소 
		voucherService.cancelVoucher(bInfo, bList);

		return resultVO;
	}
	
	/**
	 * 예약 취소
	 * - 상태 변경 + 로그
	 * - 예약타임 수량 복원
	 * - 예약선점 데이터 초기화 
	 * - 예약연결정보 insert 
	 * 
	 * @param bInfo
	 * @param bList
	 * @throws Exception
	 */
	@Transactional
	public void cancelBook(BookInfoVO bInfo, List<DrBkHistory> bList, Map<String, Object> cMap) throws Exception {	

		MnInHistory mnHis = (MnInHistory) cMap.get("mnHis");
		int mnSeq = 0;
		if (cMap.get("mnSeq") != null) {
			mnSeq = Integer.parseInt(cMap.get("mnSeq").toString());
		}
		
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
			
            // 예약연결정보 insert
			if (cMap.get("mnSeq") != null) {
	            DrBkMnMap mnMap = new DrBkMnMap();
	            mnMap.setCoDiv(bk.getCoDiv());
	            mnMap.setBkSerialNo(bk.getBkSerialNo());
	            mnMap.setMnCoDiv(mnHis.getCoDiv());
	            mnMap.setMnInDay(mnHis.getMnInDay());
	            mnMap.setMnSeq(mnSeq);
	            mnMap.setMnAmount(-mnHis.getMnInAmount());
	            drBkMnMapMapper.insertDrBkMnMap(mnMap);
			}
            // end.
            
		}
	}
}

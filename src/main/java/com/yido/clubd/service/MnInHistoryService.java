package com.yido.clubd.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yido.clubd.common.utils.Globals;
import com.yido.clubd.common.utils.ResultVO;
import com.yido.clubd.common.utils.SessionVO;
import com.yido.clubd.common.utils.StringUtils;
import com.yido.clubd.common.utils.Utils;
import com.yido.clubd.model.DrBkMnMap;
import com.yido.clubd.repository.DrBkHistoryMapper;
import com.yido.clubd.repository.DrBkMarkMapper;
import com.yido.clubd.repository.DrBkMnMapMapper;
import com.yido.clubd.repository.DrMsMaininfoMapper;
import com.yido.clubd.repository.MnInHistoryMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 입금내역
 * 
 * @author bae
 *
 */
@Slf4j
@Service
public class MnInHistoryService {

	@Autowired
    private DrBkHistoryMapper drBkHistoryMapper;
	
	@Autowired
    private DrBkHistoryService drBkHistoryService;
	
	@Autowired
    private DrMsMaininfoMapper drMsMaininfoMapper;
	
	@Autowired
    private MnInHistoryMapper mnInHistoryMapper;

	@Autowired
    private DrBkMarkMapper drBkMarkMapper;
	
	@Autowired
    private DrBkMnMapMapper drBkMnMapMapper;
	
	public int getMnSeq(Map<String, Object> params) {
		return mnInHistoryMapper.getMnSeq(params);
	}
	
	/**
	 * 입금내역 등록
	 * 
	 * @param params
	 * @return
	 */
	public int insertMnInHistory(Map<String, Object> params) {
		return mnInHistoryMapper.insertMnInHistory(params);
	}

	/**
	 * 입금내역 변경
	 * 
	 * @param params
	 * @return
	 */
	public int updateMnInHistory(Map<String, Object> params) {
		return mnInHistoryMapper.updateMnInHistory(params);
	}

	/**
	 * 카드사 정보조회
	 * 
	 * @param params
	 * @return
	 */
	public Map<String, Object> selectCardInfo(Map<String, Object> params) {
		return mnInHistoryMapper.selectCardInfo(params);
	}
		
	
	/**
	 * PG 결제 성공 후 처리 로직..
	 * 
	 * @param params
	 * @param action	: 예약 or 이용권
	 * @param reserved 	: 결제자 세션정보 
	 * @param mnMap 	: 예약-입금 연결 정보 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@Transactional
	public ResultVO successPayLogic(Map<String, Object> params, String action, String reserved, DrBkMnMap mnMap) {	
		ResultVO resultVO = new ResultVO();
		
		try {
			log.info("[successPayLogic] action : " + action);
			
			if(action.equals("RESERVATION")) {
				// RESERVATION = 예약
				
				String orderId = StringUtils.isNullOrEmpty(params.get("orderId"), "");	// 주문번호
				String msNum = orderId.split("_")[0];									// 회원번호
				
				params.put("msNum", msNum);
	
				SessionVO sessionVO = drMsMaininfoMapper.selectMsSession(params);
				if(sessionVO == null) {
					throw new Exception("회원정보가 존재하지 않습니다.");
				}
				
				JSONObject data = new JSONObject(reserved.replaceAll("&quot;", "\""));
				
				params.put("bkDay",        Utils.getJsonValue(data, "bkDay"));
				params.put("bkTime",       Utils.getJsonValue(data, "bkTime"));
				params.put("bkAmount",     Utils.getJsonValue(data, "bkAmount"));
				params.put("bayCondi",     Utils.getJsonValue(data, "bayCondi"));
				params.put("msLevel",      Utils.getJsonValue(data, "msLevel"));
				params.put("userName",     Utils.getJsonValue(data, "userName"));
				params.put("userMail",     Utils.getJsonValue(data, "userMail"));
				params.put("phone",    	   Utils.getJsonValue(data, "phone"));
				params.put("msId",    	   Utils.getJsonValue(data, "msId"));

				log.info("[successPayLogic] 예약정보:" + params);
				
				// 예약고유번호 채번
				String bkSerialNo = drBkHistoryMapper.getSerialNo(params);
				params.put("bkSerialNo", bkSerialNo);
				mnMap.setBkSerialNo(bkSerialNo);
	       		
				// 이미 다른 회원이 예약했는지 체크 후 예약 데이터 insert 진행
				drBkHistoryService.actionReservationLogicQuery(params, sessionVO);
				
        		this.afterPayLogic(params, mnMap);
	
				// ┌───────────────── SMS 전송 ─────────────────┐
				// 문자 전송 완료하고 나서 DR_BK_HISTORY 테이블 BK_SMS_SEND(SMS 전송 완료 여부) 컬럼 값 업데이트 해야함
	//	    	Map<String, Object> smsInfo = this.selectSmsInfo(params);
	//		   
	//    		params.put("phone", StringUtils.isNullOrEmpty(smsInfo.get("BK_RSV_PHONE"), ""));
	//		    
	//    		String message = commonService.getTemplateContent(params);
	//    		
	//    		message = message.replace("#{성함}", StringUtils.isNullOrEmpty(smsInfo.get("BK_RSV_NAME"), ""));
	//    		message = message.replace("#{예약일}", StringUtils.isNullOrEmpty(smsInfo.get("S_DAY"), ""));
	//    		message = message.replace("#{시간}", StringUtils.isNullOrEmpty(smsInfo.get("S_TIME"), ""));
	//    		message = message.replace("#{총금액}", StringUtils.getNumberCommaFormat(smsInfo.get("BK_SUM_COST")));
	//    		message = message.replace("#{취소기한일}", StringUtils.isNullOrEmpty(smsInfo.get("CANCEL_POSIBLE_DATE"), ""));
	//    		message = message.replace("#{예약번호}", StringUtils.isNullOrEmpty(smsInfo.get("BK_CERT_NUM"), ""));
	//
	//        	params.put("sendMsg", message);
	//    		
	//    		commonService.sendSms2(params);
				// └───────────────── SMS 전송 ─────────────────┘
			} else if(action.equals("VOUCHER")) {
				// VOUCHER = 이용권
				JSONObject data = new JSONObject(reserved.replaceAll("&quot;", "\""));
				
				params.put("vcName",        Utils.getJsonValue(data, "vcName"));
				params.put("vcAmount",      Utils.getJsonValue(data, "vcAmount"));

				log.info("[successPayLogic] 이용권 정보:" + params);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultVO.setCode("9999");
			resultVO.setMessage(e.getMessage());
		}
		
		return resultVO;
	}

	/**
	 * 결제 완료 후 처리 
	 * - PG결제내역 변경 (예약고유번호, 회원이름, 회원번호) 
	 * - 예약선점 테이블 변경 (예약고유번호, 상태) 
	 * 
	 * @param param
	 * @param mnMap		: 예약-입금 연결 정보 
	 * @throws Exception
	 */
	public void afterPayLogic(Map<String, Object> param, DrBkMnMap mnMap) throws Exception {
		
		// PG 결제내역 변경 (예약고유번호, 회원이름, 회원번호) 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mnSeq", param.get("mnSeq"));
		map.put("mnInDay", param.get("mnInDay"));
		map.put("coDiv", param.get("coDiv"));
		map.put("msNum", param.get("msNum"));
		map.put("mnInName", param.get("userName"));
		map.put("mnSerialNo", param.get("bkSerialNo"));
		
		mnInHistoryMapper.updateMnInHistory(map);
		// end.

		
		// 예약선점 테이블 변경 (예약고유번호, 상태)
		// 조회조건 : 지점코드, 베이코드, 일자, 시간, 아이디, 상태 Y인거 
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("coDiv"		, param.get("coDiv"));
		map2.put("bayCondi"		, param.get("bayCondi"));
		map2.put("bkDay"		, param.get("bkDay"));
		map2.put("bkTime"		, param.get("bkTime"));
		map2.put("entryUser"	, param.get("msId"));
		map2.put("bkStateIsY"	, "Y");
		
		map2.put("bkSerialNo"	, param.get("bkSerialNo"));
		map2.put("bkState"		, "N");
		drBkMarkMapper.updateDrBkMark(map2);
		// end.
		
		// 예약-입금 연결 정보 insert
		drBkMnMapMapper.insertDrBkMnMap(mnMap);		
	}
	
	/**
	 * PG 결제 취소 로직 
	 * - MN_IN_HISTORY insert 
	 * 
	 * @param coDiv
	 * @param orderId
	 * @param cancelAmount
	 * @param transactionId
	 * @param cancelType
	 * @param cancelKey
	 * @param ipAddr
	 * @return
	 */
    public boolean cancelLogic(String coDiv, String orderId, String cancelAmount, String transactionId, String cancelType, String cancelKey, String ipAddr) { 
    	boolean resultValue = false;
    	
		String orderDate             = "";
		String cancelDate            = "";
		String partCancelAmount      = "";
		String seqNumber             = "";
		String responseCode          = "";
		String responseMessage       = "";
		String detailResponseCode    = "";
		String detailResponseMessage = "";
    	
    	BufferedReader br = null;
		
		try {
			String current = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			
	    	URL url = new URL(Globals.cancelUrl); 
	        Map<String, Object> params = new LinkedHashMap<>();
	        params.put("SERVICE_ID", Globals.serviceId);
	        params.put("SERVICE_CODE", "0900");
	        params.put("ORDER_ID", orderId);
	        params.put("ORDER_DATE", current);
	        params.put("CANCEL_AMOUNT", cancelAmount);
	        params.put("TRANSACTION_ID", transactionId);
	        params.put("CANCEL_TYPE", cancelType);
	        params.put("CANCEL_KEY", cancelKey);

	        params.put("ACCESS_TARGET", Globals.accessTarget);
	        params.put("HIDDEN_KEY", Globals.hiddenKey);
	        
	        StringBuilder postData = new StringBuilder();
	        for(Map.Entry<String,Object> param : params.entrySet()) {
	            if(postData.length() != 0) postData.append('&');
	            postData.append(URLEncoder.encode(param.getKey(), "EUC-KR"));
	            postData.append('=');
	            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "EUC-KR"));
	        }
	        byte[] postDataBytes = postData.toString().getBytes("EUC-KR");
	        
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Accept", "application/x-www-form-urlencoded xml");
	        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=EUC-KR");
	        conn.setRequestProperty("Accept-language", "gx");        
	        conn.setDoOutput(true);
	        conn.getOutputStream().write(postDataBytes);
	 
	        br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "EUC-KR"));
	 
	        StringBuffer sb = new StringBuffer();

	        String s = "";
	        while ((s = br.readLine()) != null) {
	            sb.append(s);
	        }
	    	
	        JSONObject result = new JSONObject(sb.toString());

            log.info("[cancelLogic] result : " + result);
            
	        log.info("=================== 취소 결과 시작 ===================");
	        log.info("가맹점 아이디 (SERVICE_ID) : " + Utils.getJsonValue(result, "SERVICE_ID"));
	        log.info("서비스코드 (SERVICE_CODE) : " + Utils.getJsonValue(result, "SERVICE_CODE"));
	        log.info("주문번호 (ORDER_ID) : " + Utils.getJsonValue(result, "ORDER_ID"));
	        log.info("주문일시 (ORDER_DATE) : " + Utils.getJsonValue(result, "ORDER_DATE"));
	        log.info("거래번호 (TRANSACTION_ID) : " + Utils.getJsonValue(result, "TRANSACTION_ID"));
	        log.info("전문코드 (COMMAND) : " + Utils.getJsonValue(result, "COMMAND"));
	        log.info("취소타입 (CANCEL_TYPE) : " + Utils.getJsonValue(result, "CANCEL_TYPE"));
	        log.info("취소일시 (CANCEL_DATE) : " + Utils.getJsonValue(result, "CANCEL_DATE"));
	        log.info("취소금액 (CANCEL_AMOUNT) : " + Utils.getJsonValue(result, "CANCEL_AMOUNT"));
	        log.info("응답코드 (RESPONSE_CODE) : " + Utils.getJsonValue(result, "RESPONSE_CODE"));
	        log.info("응답메세지 (RESPONSE_MESSAGE) : " + Utils.getJsonValue(result, "RESPONSE_MESSAGE"));
	        log.info("상세응답코드 (DETAIL_RESPONSE_CODE) : " + Utils.getJsonValue(result, "DETAIL_RESPONSE_CODE"));
	        log.info("상세응답메세지 (DETAIL_RESPONSE_MESSAGE) : " + Utils.getJsonValue(result, "DETAIL_RESPONSE_MESSAGE"));               
	        log.info("=================== 취소 결과 끝 ===================");

	        orderId               = Utils.getJsonValue(result, "ORDER_ID");
	        orderDate             = Utils.getJsonValue(result, "ORDER_DATE");
	        transactionId         = Utils.getJsonValue(result, "TRANSACTION_ID");
	        cancelType            = Utils.getJsonValue(result, "CANCEL_TYPE");
	        cancelDate            = Utils.getJsonValue(result, "CANCEL_DATE");
	        cancelAmount          = Utils.getJsonValue(result, "CANCEL_AMOUNT");
	        responseCode          = Utils.getJsonValue(result, "RESPONSE_CODE");
	        responseMessage       = Utils.getJsonValue(result, "RESPONSE_MESSAGE");
	        detailResponseCode    = Utils.getJsonValue(result, "DETAIL_RESPONSE_CODE");
	        detailResponseMessage = Utils.getJsonValue(result, "DETAIL_RESPONSE_MESSAGE");
            
            params.put("coDiv"				, coDiv);
            params.put("mnInDay"			, orderDate.substring(0,8));	// 취소일자
            params.put("mnRevAmount"		, cancelAmount);				// 총입금액
            params.put("mnInAmount"			, cancelAmount);				// 결제금액
            params.put("mnChangeAmount"		, 0);							// 반환금액
            params.put("mnInNo"				, "");							// 카드번호
            params.put("mnCardApproval"		, "");							// 승인번호
            params.put("mnMonth"            , "");							// 할부개월수
            params.put("mnAppDate"			, "");							// 승인일자
            params.put("cardCompanyCode" 	, "");							// 카드 발급사 코드 4자리       
            params.put("orderId"			, orderId);						// 주문번호
            params.put("transactionId"		, transactionId);				// 거래번호
            params.put("mnRemark"			, detailResponseMessage);		// 비고에 응답상세메시지 insert 
            params.put("mnCancelYn"			, "Y");							// 취소여부 
            params.put("ipAddr"             , ipAddr);
            
            // MN_IN_HISTORY 테이블 조회 (주문번호에 해당하는 마지막 mnSeq 조회)
            params.put("mnSeq", this.getMnSeq(params)); 
            
            // 취소건 insert
            this.insertMnInHistory(params);
            
            // 취소건 update (클럽디에 있던 로직인데, 클럽디 청담에는 필요없을듯함. 나중에 필요하면 다시 넣기)
            // this.updateMnInHistory2(params);
            
            if(responseCode.equals("0000")) {
            	resultValue = true;
            }
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
	            if (br != null) br.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
		
		return resultValue;
    }	
}

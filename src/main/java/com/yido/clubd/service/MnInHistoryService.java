package com.yido.clubd.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yido.clubd.common.utils.Globals;
import com.yido.clubd.common.utils.ResultVO;
import com.yido.clubd.common.utils.SessionVO;
import com.yido.clubd.common.utils.StringUtils;
import com.yido.clubd.common.utils.Utils;
import com.yido.clubd.model.BookInfoVO;
import com.yido.clubd.model.DrBkHistory;
import com.yido.clubd.model.DrBkHistoryTemp;
import com.yido.clubd.model.DrBkMnMap;
import com.yido.clubd.model.MnInHistory;
import com.yido.clubd.repository.DrBkHistoryMapper;
import com.yido.clubd.repository.DrBkHistoryTempMapper;
import com.yido.clubd.repository.DrBkMarkMapper;
import com.yido.clubd.repository.DrBkMnMapMapper;
import com.yido.clubd.repository.MemberMapper;
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
    private VoucherService voucherService;
	
	@Autowired
    private DrBkHistoryService drBkHistoryService;
	
	@Autowired
    private DrBkHistoryMapper drBkHistoryMapper;
	
	@Autowired
    private DrBkHistoryTempMapper drBkHistoryTempMapper;
	
	@Autowired
    private MemberMapper memberMapper;
	
	@Autowired
    private MnInHistoryMapper mnInHistoryMapper;

	@Autowired
    private DrBkMarkMapper drBkMarkMapper;
	
	@Autowired
    private DrBkMnMapMapper drBkMnMapMapper;
	
	public int getMnSeq(Map<String, Object> params) {
		return mnInHistoryMapper.getMnSeq(params);
	}
	
	public MnInHistory getMnInHistory(MnInHistory mnInHistory) {
		return mnInHistoryMapper.getMnInHistory(mnInHistory);
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
	@Transactional
	public ResultVO successPayLogic(Map<String, Object> params, String action, String reserved, DrBkMnMap mnMap, HttpSession session) {	
		ResultVO resultVO = new ResultVO();
		Gson gson = new Gson();
		
		try {
			log.info("[successPayLogic] action : " + action);

			String orderId = StringUtils.isNullOrEmpty(params.get("orderId"), "");	// 주문번호
			String msNum = orderId.split("_")[0];									// 회원번호
			
			params.put("msNum", msNum);

			SessionVO sessionVO = memberMapper.selectMsSession(params);
			if(sessionVO == null) {
				throw new Exception("회원정보가 존재하지 않습니다.");
			}			
			if (session.getAttribute("msMember") == null) {
				log.info("=========== 세션 없음, 세션 구성 =========");
				session.setAttribute("msMember", sessionVO);
				session.setMaxInactiveInterval(30 * 60);
			} else {
				log.info("=========== 세션 있음 =========");
			}
			
			if(action.equals("RESERVATION")) {	// RESERVATION = 예약
				String isVoucherUse = "N";
				List<Map<String, Object>> vList = new ArrayList<Map<String, Object>>();
				BookInfoVO bkInfo = new BookInfoVO();				
				JSONObject data = new JSONObject(reserved.replaceAll("&quot;", "\""));
				
				params.put("bkDay"			, Utils.getJsonValue(data, "bkDay"));
				params.put("bkTime"			, Utils.getJsonValue(data, "bkTime"));
				params.put("bkAmount"		, Utils.getJsonValue(data, "bkAmount"));
				params.put("oriBkAmount"	, Utils.getJsonValue(data, "oriBkAmount"));
				params.put("bayCondi"		, Utils.getJsonValue(data, "bayCondi"));
				params.put("msLevel"		, Utils.getJsonValue(data, "msLevel"));
				params.put("bkName"			, Utils.getJsonValue(data, "userName"));
				params.put("userMail"		, Utils.getJsonValue(data, "userMail"));
				params.put("phone"			, Utils.getJsonValue(data, "phone"));
				params.put("msId"			, Utils.getJsonValue(data, "msId"));	
				params.put("vList"			, Utils.getJsonValue(data, "vList"));			// vList : 이용권 고유번호, 이용권 금액, 사용수량 등
				params.put("serialNo"		, Utils.getJsonValue(data, "tempSerialNo"));	// tempSerialNo : 예약 임시테이블 고유번호 (결제 완료 후 임시테이블 데이터 삭제 해야함)
				params.put("isVoucherUse"	, isVoucherUse);
			
				// vList : 이용권  
				if (params.get("vList") != null) {
					vList = gson.fromJson(params.get("vList").toString(), new TypeToken<List<Map<String, Object>>>() {}.getType());			
					if (vList.size() > 0) isVoucherUse = "Y";
				}
				
				// 지불수단 
				if (Integer.parseInt(params.get("bkAmount").toString()) > 0) params.put("bkPayDiv", 2); // 선불 
				else params.put("bkPayDiv", 1); // 이용권 

				log.info("[successPayLogic] 예약정보 : " + params);
				
				// 예약 내역 insert 				
				Map<String, Object> returnMap = drBkHistoryService.actionReservation(params, mnMap);
				resultVO.setData(returnMap.get("calcSerialNo"));
				
				if (isVoucherUse.equals("Y")) {
					log.info("================= 이용권 사용처리 =================");
					// 이용권 사용처리 
					bkInfo.setVList(vList);
					bkInfo.setCoDiv(params.get("coDiv").toString());
					bkInfo.setIpAddr(params.get("ipAddr").toString());

					voucherService.useVoucher(bkInfo, returnMap);					
				} else {
					log.info("================= 이용권 사용안함 =================");
				}

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
			} else if(action.equals("VOUCHER")) {	// VOUCHER = 이용권
				JSONObject data = new JSONObject(reserved.replaceAll("&quot;", "\""));
				
				params.put("vcCd"			, Utils.getJsonValue(data, "vcCd"));
				params.put("vcAmount"		, Utils.getJsonValue(data, "vcAmount"));
				params.put("msLevel"		, Utils.getJsonValue(data, "msLevel"));
				params.put("bkName"			, Utils.getJsonValue(data, "userName"));
				params.put("userMail"		, Utils.getJsonValue(data, "userMail"));
				params.put("phone"			, Utils.getJsonValue(data, "phone"));
				params.put("msId"			, Utils.getJsonValue(data, "msId"));				

				log.info("[successPayLogic] 이용권 정보:" + params);
				
				// 이용권 구매 내역 insert 				
				voucherService.insertVouInfo(params);
				
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
	 * - 예약 임시테이블 데이터 삭제 
	 * - 이용권 잔여수량 업데이트 / 이용권 잔여수량 변경된 로그 / 이용권 세부 내역 업데이트
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
		
		mnInHistoryMapper.updateMnInHistory(map);
		// end.		
		
		// 예약 임시테이블 데이터 삭제
		DrBkHistoryTemp temp = new DrBkHistoryTemp();
		temp.setSerialNo(param.get("serialNo").toString());
		drBkHistoryTempMapper.deleteHistoryTemp(temp);
		// end.
		
		// 이용권 잔여수량 업데이트 / 이용권 잔여수량 변경된 로그 / 이용권 세부 내역 업데이트
		log.debug("[afterPayLogic] 이용권: " + param.get("vList"));
		
		// end.
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
    public boolean cancelLogic(String coDiv, String orderId, String cancelAmount
    		, String transactionId, String cancelType, String cancelKey, String ipAddr, MnInHistory mnHis) { 
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
            params.put("mnInDay"			, orderDate.substring(0,8));					// 취소일자
            params.put("mnRevAmount"		, -Integer.parseInt(cancelAmount));				// 총입금액
            params.put("mnInAmount"			, -Integer.parseInt(cancelAmount));				// 결제금액
            params.put("mnChangeAmount"		, 0);							// 반환금액
            params.put("mnInNo"				, "");							// 카드번호
            params.put("mnCardApproval"		, "");							// 승인번호
            params.put("mnMonth"            , "");							// 할부개월수
            params.put("mnAppDate"			, "");							// 승인일자
            params.put("cardCompanyCode" 	, "");							// 카드 발급사 코드 4자리       
            params.put("orderId"			, orderId);						// 주문번호
            params.put("transactionId"		, transactionId);				// 거래번호
            params.put("mnCancelYn"			, "Y");							// 취소여부 
            params.put("ipAddr"             , ipAddr);
            params.put("mnSerialNo"			, mnHis.getMnSerialNo());		// 이용권 매출 고유번호 
            
            // MN_IN_HISTORY 테이블 조회 (주문번호에 해당하는 마지막 mnSeq 조회)
            params.put("mnSeq", this.getMnSeq(params)); 
            
            // 취소건 insert
            if (mnHis != null) {
                // 결제 취소시 원본 지점코드, 원본 입금일자, 원본 입금순번 들어가야함  
            	params.put("mnOriCoDiv", mnHis.getCoDiv());
            	params.put("mnOriInDay", mnHis.getMnInDay());
            	params.put("mnOriMnSeq", mnHis.getMnSeq());
            }
            log.info("[cancelLogic] insertMnInHistory : " + params);
            
            this.insertMnInHistory(params);
            
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

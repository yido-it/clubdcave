package com.yido.clubd.service;

import java.net.URLEncoder;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.yido.clubd.common.utils.ResultVO;
import com.yido.clubd.common.utils.StringUtils;
import com.yido.clubd.common.utils.Utils;
import com.yido.clubd.repository.PgHistoryMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * PG 결제내역
 * 
 * @author bae
 *
 */
@Slf4j
@Service
public class PgHistoryService {

	@Autowired
    private PgHistoryMapper pgHistoryMapper;

	public int insertOrderHistory(Map<String, Object> params) {
		return pgHistoryMapper.insertOrderHistory(params);
	}
	
	/**
	 * 결제 성공 후 처리 로직..
	 * 
	 * @param params
	 * @param action
	 * @param reserved
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@Transactional
	public ResultVO successPayLogic(Map<String, Object> params, String action, String reserved) {	
		ResultVO resultVO = new ResultVO();
		
		try {
			log.info("[successPayLogic] action : " + action);
			if(action.equals("RESERVATION")) {
				
				String orderId = StringUtils.isNullOrEmpty(params.get("orderId"), "");	// 주문번호
				String msNum = orderId.split("_")[0];									// 회원번호
				
				params.put("msNum", msNum);
	
				// 로그인 정보 없을때?
				// 트렌젝션 처리에 대한 고민 해야함 .. 
				
				/*
				SessionVO sessionVO = memberService.getMsSession(params);
				if(sessionVO == null) {
					// 예약하는데 회원정보가 없음
					throw new Exception("로그인 후 이용 가능합니다.");
				}
				*/
				
				JSONObject data = new JSONObject(reserved.replaceAll("&quot;", "\""));
				
				params.put("bkDay",        Utils.getJsonValue(data, "bkDay"));
				params.put("bkTime",       Utils.getJsonValue(data, "bkTime"));
				params.put("bkAmount",     Utils.getJsonValue(data, "bkAmount"));

				log.info("[successPayLogic] 예약정보:" + params);
	//			String bkRsvNo = reservationService.getBkRsvNo(params);
	//			params.put("bkRsvNo", bkRsvNo);
	       		
				// 이미 다른 회원이 예약했는지 check 
	//			reservationService.actionReservationLogicQuery(params, sessionVO);
	
				// ┌───────────────── SMS 전송 ─────────────────┐
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
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultVO.setCode("9999");
			resultVO.setMessage(e.getMessage());
		}
		
		return resultVO;
	}

}

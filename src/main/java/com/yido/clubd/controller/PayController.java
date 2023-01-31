package com.yido.clubd.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yido.clubd.common.utils.Globals;
import com.yido.clubd.common.utils.ResultVO;
import com.yido.clubd.common.utils.StringUtils;
import com.yido.clubd.common.utils.Utils;
import com.yido.clubd.model.DrBkMnMap;
import com.yido.clubd.model.MnInHistory;
import com.yido.clubd.service.MnInHistoryService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/pay")
public class PayController {
	
	@Autowired
	private MnInHistoryService mnInHistoryService;
	
	/**
	 * 가맹점 결과 처리 함수 
	 * - 예약, 이용권 모두 해당됨 
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
    @SuppressWarnings("deprecation")
	@RequestMapping(value = "/returnPay.bill")
    public String returnPay(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) {
    	
    	log.info("====================== return Pay ========================");
		BufferedReader br = null; 
		String resultCode = "9999";
		boolean isSuccessPay = false;
		ResultVO resultVO = new ResultVO();

		String serviceId             = "";
		String serviceCode           = "";
		String orderId               = "";		// 주문 번호
		String orderDate             = "";		// 주문 일시
		String payMessage            = "";
		String transactionId         = "";		// 빌게이트 거래 번호
		String cancelKey             = "";		// 취소키
		String authDate              = "";		// 승인일시
		String authNumber            = "";		// 승인번호
		String authAmount            = "";		// 승인금액
		String quota                 = "";		// 할부개월수
		String cardCompanyCode       = "";		// 카드사코드
		String pinNumber             = "";		// 카드번호
		String responseCode          = "";		// 응답코드
		String responseMessage       = "";		// 응답메세지
		String detailResponseCode    = "";		// 상세응답코드
		String detailResponseMessage = "";		// 상세응답메세지
		String reserved1             = "";
		String reserved2             = "";
		String reserved3             = "";
      
		String ipAddr = Utils.getClientIpAddress(request);
		
    	try {
            request.setCharacterEncoding("euc-kr");

            //만료된 페이지 설정
            response.setHeader("cache-control", "no-cache");
            response.setHeader("pragma", "no-cache");
            response.setHeader("expire", "0");

            //결제 정보 받아오기
            log.info("===================인증 결과 시작===================");
            log.info("가맹점아이디 (SERVICE_ID) : " + request.getParameter("SERVICE_ID"));             
            log.info("서비스코드 (SERVICE_CODE) : " + request.getParameter("SERVICE_CODE"));          
            log.info("주문번호 (ORDER_ID) : " + request.getParameter("ORDER_ID"));               
            log.info("주문일시 (ORDER_DATE) : " + request.getParameter("ORDER_DATE"));              
            log.info("거래번호 (TRANSACTION_ID) : " + request.getParameter("TRANSACTION_ID"));          
            log.info("응답코드 (RESPONSE_CODE) : " + request.getParameter("RESPONSE_CODE"));           
            log.info("응답메시지 (RESPONSE_MESSAGE) : " + request.getParameter("RESPONSE_MESSAGE"));         
            log.info("상세응답코드 (DETAIL_RESPONSE_CODE) : " + request.getParameter("DETAIL_RESPONSE_CODE"));    
            log.info("상세응답메시지 (DETAIL_RESPONSE_MESSAGE) : " + request.getParameter("DETAIL_RESPONSE_MESSAGE")); 
            log.info("결제암호화값 (MESSAGE) : " + request.getParameter("MESSAGE"));                 
            log.info("WEBAPI 결제암호화값 (PAY_MESSAGE) : " + request.getParameter("PAY_MESSAGE"));             
            log.info("RESERVED1 : " + request.getParameter("RESERVED1"));               
            log.info("RESERVED2 : " + request.getParameter("RESERVED2"));              
            log.info("RESERVED3 : " + request.getParameter("RESERVED3"));               
            log.info("===================인증 결과 끝===================");

            serviceId	= request.getParameter("SERVICE_ID");
            serviceCode	= request.getParameter("SERVICE_CODE");
            orderId		= request.getParameter("ORDER_ID");
            orderDate	= request.getParameter("ORDER_DATE");
            payMessage	= request.getParameter("PAY_MESSAGE");
         
            // 인증 성공 시 승인 요청
            if (request.getParameter("RESPONSE_CODE").equals("0000")) {
                URL url = new URL(Globals.approveUrl);
                Map<String, Object> params = new LinkedHashMap<>();

        		params.put("ipAddr", ipAddr);
                params.put("SERVICE_CODE", serviceCode);
                params.put("SERVICE_ID", serviceId);
                params.put("ORDER_ID", orderId);
                params.put("ORDER_DATE", orderDate);
                params.put("PAY_MESSAGE", payMessage);
                
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
            	
                // JSONParser parser = new JSONParser();
                // JSONObject result = (JSONObject) parser.parse(sb.toString()); 
                JSONObject result = new JSONObject(sb.toString());

                log.info("[returnPay] result : " + result);
                
            	log.info("===================승인 결과 시작===================");
            	log.info("가맹점 아이디 (SERVICE_ID) : " + StringUtils.isNullOrEmpty(result.get("SERVICE_ID"), ""));				
            	log.info("서비스코드 (SERVICE_CODE) : " + StringUtils.isNullOrEmpty(result.get("SERVICE_CODE"), ""));			
            	log.info("전문코드 (COMMAND) : " + StringUtils.isNullOrEmpty(result.get("COMMAND"), ""));					
            	log.info("주문번호 (ORDER_ID) : " + StringUtils.isNullOrEmpty(result.get("ORDER_ID"), ""));				
            	log.info("주문일시 (ORDER_DATE) : " + StringUtils.isNullOrEmpty(result.get("ORDER_DATE"), ""));				
            	log.info("거래번호 (TRANSACTION_ID) : " + StringUtils.isNullOrEmpty(result.get("TRANSACTION_ID"), ""));			
            	log.info("취소키 (CANCEL_KEY)	 : " + StringUtils.isNullOrEmpty(result.get("CANCEL_KEY"), ""));				
            	log.info("승인일시 (AUTH_DATE) : " + StringUtils.isNullOrEmpty(result.get("AUTH_DATE"), ""));				 
            	log.info("승인번호 (AUTH_NUMBER) : " + StringUtils.isNullOrEmpty(result.get("AUTH_NUMBER"), ""));				
            	log.info("승인금액 (AUTH_AMOUNT) : " + StringUtils.isNullOrEmpty(result.get("AUTH_AMOUNT"), ""));				
            	log.info("할부개월수 (QUOTA) : " + StringUtils.isNullOrEmpty(result.get("QUOTA"), ""));					
            	log.info("카드사코드 (CARD_COMPANY_CODE) : " + StringUtils.isNullOrEmpty(result.get("CARD_COMPANY_CODE"), ""));		
            	log.info("카드번호 (PIN_NUMBER) : " + StringUtils.isNullOrEmpty(result.get("PIN_NUMBER"), ""));				 
            	log.info("응답코드 (RESPONSE_CODE) : " + StringUtils.isNullOrEmpty(result.get("RESPONSE_CODE"), ""));			 
            	log.info("응답메세지 (RESPONSE_MESSAGE) : " + StringUtils.isNullOrEmpty(result.get("RESPONSE_MESSAGE"), ""));		
            	log.info("상세응답코드 (DETAIL_RESPONSE_CODE) : " + StringUtils.isNullOrEmpty(result.get("DETAIL_RESPONSE_CODE"), ""));	
            	log.info("상세응답메세지 (DETAIL_RESPONSE_MESSAGE) : " + StringUtils.isNullOrEmpty(result.get("DETAIL_RESPONSE_MESSAGE"), ""));	 
                log.info("===================승인 결과 끝===================");

                orderId               = StringUtils.isNullOrEmpty(result.get("ORDER_ID"), "");					
                orderDate             = StringUtils.isNullOrEmpty(result.get("ORDER_DATE"), "");				
                transactionId         = StringUtils.isNullOrEmpty(result.get("TRANSACTION_ID"), "");			
                cancelKey             = StringUtils.isNullOrEmpty(result.get("CANCEL_KEY"), "");				
                authDate              = StringUtils.isNullOrEmpty(result.get("AUTH_DATE"), "");					
                authNumber            = StringUtils.isNullOrEmpty(result.get("AUTH_NUMBER"), "");				
                authAmount            = StringUtils.isNullOrEmpty(result.get("AUTH_AMOUNT"), "");				
                quota                 = StringUtils.isNullOrEmpty(result.get("QUOTA"), "");						
                cardCompanyCode       = StringUtils.isNullOrEmpty(result.get("CARD_COMPANY_CODE"), "");			
                pinNumber             = StringUtils.isNullOrEmpty(result.get("PIN_NUMBER"), "");				
                responseCode          = StringUtils.isNullOrEmpty(result.get("RESPONSE_CODE"), "");				
                responseMessage       = StringUtils.isNullOrEmpty(result.get("RESPONSE_MESSAGE"), "");			
                detailResponseCode    = StringUtils.isNullOrEmpty(result.get("DETAIL_RESPONSE_CODE"), "");		
                detailResponseMessage = StringUtils.isNullOrEmpty(result.get("DETAIL_RESPONSE_MESSAGE"), "");	
                reserved1             = URLDecoder.decode(request.getParameter("RESERVED1"));
                reserved2             = URLDecoder.decode(request.getParameter("RESERVED2"));
                reserved3             = URLDecoder.decode(request.getParameter("RESERVED3"));
                isSuccessPay          = responseCode.equals("0000");

                log.info("[returnPay] params 1 : " + params);
                log.info("[returnPay] reserved3 : " + reserved3);
                
                params.put("coDiv"				, reserved1);
                params.put("mnInDay"			, orderDate.substring(0,8));	// 입금일자
                params.put("mnRevAmount"		, authAmount);					// 총입금액
                params.put("mnInAmount"			, authAmount);					// 결제금액
                params.put("mnChangeAmount"		, 0);							// 반환금액
                params.put("mnInNo"				, pinNumber);					// 카드번호
                params.put("mnCardApproval"		, authNumber);					// 승인번호
                params.put("mnMonth"            , quota);						// 할부개월수
                params.put("mnAppDate"			, authDate.substring(0,8));		// 승인일자
                params.put("cardCompanyCode" 	, cardCompanyCode);				// 카드 발급사 코드 4자리       
                params.put("orderId"			, orderId);						// 주문번호
                params.put("transactionId"		, transactionId);				// 거래번호
                params.put("cancelKey"			, cancelKey);					// 취소키  
                params.put("mnCancelYn"			, "N");							// 취소여부 
                
                Map<String, Object> cardInfo = mnInHistoryService.selectCardInfo(params);
                
                if (cardInfo != null) {
                    params.put("mnCardDiv", StringUtils.isNullOrEmpty(cardInfo.get("cardCode"), ""));	// 매입사코드
                    params.put("mnCardName", StringUtils.isNullOrEmpty(cardInfo.get("cardName"), ""));	// 카드발급사명
                }

                log.info("[returnPay] params 2 : " + params);
               
                // PG 결제내역 등록 (예약, 이용권)
                Map<String, Object> tmpMap = new HashMap<String, Object>();
                int mnSeq = mnInHistoryService.getMnSeq(tmpMap);
                params.put("mnSeq", mnSeq);
                mnInHistoryService.insertMnInHistory(params);
                // end.
                
                // 결제 성공
                if(responseCode.equals("0000")) {
                	// 예약-입금 연결 정보 
                    DrBkMnMap mnMap = new DrBkMnMap();
                    mnMap.setCoDiv(reserved1);
                    mnMap.setMnSeq(mnSeq);
                    mnMap.setMnCoDiv(reserved1);
                    mnMap.setMnInDay(orderDate.substring(0,8));
                    mnMap.setMnAmount(Integer.parseInt(authAmount));
                    // end.
                    
                	// 결제 후 예약처리...
                	resultVO = mnInHistoryService.successPayLogic(params, reserved2, reserved3, mnMap, session); 
                	
                	if(resultVO.getCode().equals("0000")) {
                		resultCode = "0000";
                	} else {
                		throw new Exception(resultVO.getMessage());
                	}
                }
            }
    	} catch(Exception e) {
    		model.addAttribute("resultMessage", e.getMessage().replaceAll("\"", "'"));
    		
    		// 결제는 성공했지만 오류 (취소요청 해야함)
    		if(isSuccessPay) {
    			MnInHistory mnHis = new MnInHistory();
    			mnInHistoryService.cancelLogic(reserved1, orderId, authAmount, transactionId, "C", cancelKey, ipAddr, mnHis);
    		}
    	} finally {
    		try {
                if (br != null) br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    	}

    	model.addAttribute("calcSerialNo", resultVO.getData());
    	model.addAttribute("resultCode", resultCode);
    	
    	return "/book/payReturn";
    }  

}

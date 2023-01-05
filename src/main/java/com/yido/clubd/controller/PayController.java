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
import org.springframework.web.bind.annotation.RequestParam;

import com.yido.clubd.common.utils.Globals;
import com.yido.clubd.common.utils.ResultVO;
import com.yido.clubd.common.utils.Seed128;
import com.yido.clubd.common.utils.StringUtils;
import com.yido.clubd.common.utils.Utils;
import com.yido.clubd.service.DrBkHistoryService;
import com.yido.clubd.service.PgHistoryService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/pay")
public class PayController {
	
	@Autowired
	private PgHistoryService payService;
	
	/**
	 * 가맹점 결과 처리 함수 
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
    @SuppressWarnings("deprecation")
	@RequestMapping(value = "/returnPay.bill")
    public String returnPay(HttpServletRequest request, HttpServletResponse response, Model model) {
    	
    	log.info("====================== return Pay ========================");
		BufferedReader br = null; 
		String resultCode = "9999";
		boolean isSuccessPay = false;

		String serviceId             = "";
		String serviceCode           = "";
		String orderId               = "";
		String orderDate             = "";
		String payMessage            = "";
		String transactionId         = "";
		String cancelKey             = "";
		String authDate              = "";
		String authNumber            = "";
		String authAmount            = "";
		String quota                 = "";
		String cardCompanyCode       = "";
		String pinNumber             = "";
		String simpleAffName         = "";
		String simpleDealType        = "";
		String responseCode          = "";
		String responseMessage       = "";
		String detailResponseCode    = "";
		String detailResponseMessage = "";
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
            log.info("SERVICE_ID              : " + request.getParameter("SERVICE_ID"));              // 가맹점아이디
            log.debug("SERVICE_CODE            : " + request.getParameter("SERVICE_CODE"));            // 서비스코드
            log.debug("ORDER_ID                : " + request.getParameter("ORDER_ID"));                // 주문번호
            log.debug("ORDER_DATE              : " + request.getParameter("ORDER_DATE"));              // 주문일시
            log.debug("TRANSACTION_ID          : " + request.getParameter("TRANSACTION_ID"));          // 거래번호
            log.debug("RESPONSE_CODE           : " + request.getParameter("RESPONSE_CODE"));           // 응답코드
            log.debug("RESPONSE_MESSAGE        : " + request.getParameter("RESPONSE_MESSAGE"));        // 응답메시지
            log.debug("DETAIL_RESPONSE_CODE    : " + request.getParameter("DETAIL_RESPONSE_CODE"));    // 상세응답코드
            log.debug("DETAIL_RESPONSE_MESSAGE : " + request.getParameter("DETAIL_RESPONSE_MESSAGE")); // 상세응답메시지
            log.debug("MESSAGE                 : " + request.getParameter("MESSAGE"));                 // 결제암호화값
            log.debug("PAY_MESSAGE             : " + request.getParameter("PAY_MESSAGE"));             // 결제암호화값(WEBAPI)
            log.debug("RESERVED1               : " + request.getParameter("RESERVED1"));               // 예비변수1
            log.debug("RESERVED2               : " + request.getParameter("RESERVED2"));               // 예비변수2
            log.debug("RESERVED3               : " + request.getParameter("RESERVED3"));               // 예비변수3
            log.debug("===================인증 결과 끝===================");
            
            serviceId    = request.getParameter("SERVICE_ID");
            serviceCode  = request.getParameter("SERVICE_CODE");
            orderId      = request.getParameter("ORDER_ID");
            orderDate    = request.getParameter("ORDER_DATE");
            payMessage   = request.getParameter("PAY_MESSAGE");

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
            	
            	log.debug("===================승인 결과 시작===================");
            	log.debug("SERVICE_ID              : " + StringUtils.isNullOrEmpty(result.get("SERVICE_ID"), ""));				// 가맹점 서비스 아이디(SID)
            	log.debug("SERVICE_CODE            : " + StringUtils.isNullOrEmpty(result.get("SERVICE_CODE"), ""));			// 결제 수단별 서비스코드 (예 : 신용카드 0900)
            	log.debug("COMMAND                 : " + StringUtils.isNullOrEmpty(result.get("COMMAND"), ""));					// 전문코드
            	log.debug("ORDER_ID                : " + StringUtils.isNullOrEmpty(result.get("ORDER_ID"), ""));				// 가맹점 측 사용 주문 번호
            	log.debug("ORDER_DATE              : " + StringUtils.isNullOrEmpty(result.get("ORDER_DATE"), ""));				// 가맹점 측 사용 주문 일시
            	log.debug("TRANSACTION_ID          : " + StringUtils.isNullOrEmpty(result.get("TRANSACTION_ID"), ""));			// 빌게이트 거래 번호
            	log.debug("CANCEL_KEY              : " + StringUtils.isNullOrEmpty(result.get("CANCEL_KEY"), ""));				// 취소키
            	log.debug("AUTH_DATE               : " + StringUtils.isNullOrEmpty(result.get("AUTH_DATE"), ""));				// 승인일시
            	log.debug("AUTH_NUMBER             : " + StringUtils.isNullOrEmpty(result.get("AUTH_NUMBER"), ""));				// 승인번호
            	log.debug("AUTH_AMOUNT             : " + StringUtils.isNullOrEmpty(result.get("AUTH_AMOUNT"), ""));				// 승인금액
            	log.debug("QUOTA                   : " + StringUtils.isNullOrEmpty(result.get("QUOTA"), ""));					// 할부개월수
            	log.debug("CARD_COMPANY_CODE       : " + StringUtils.isNullOrEmpty(result.get("CARD_COMPANY_CODE"), ""));		// 카드사코드
            	log.debug("PIN_NUMBER              : " + StringUtils.isNullOrEmpty(result.get("PIN_NUMBER"), ""));				// 카드번호
            	log.debug("SIMPLE_AFF_NAME         : " + StringUtils.isNullOrEmpty(result.get("SIMPLE_AFF_NAME"), ""));			// 간편결제종류 (간편결제건인 경우만 전달)
            	log.debug("SIMPLE_DEAL_TYPE        : " + StringUtils.isNullOrEmpty(result.get("SIMPLE_DEAL_TYPE"), ""));		// 간편결제구분 (간편결제건인 경우만 전달)
            	log.debug("RESPONSE_CODE           : " + StringUtils.isNullOrEmpty(result.get("RESPONSE_CODE"), ""));			// 응답코드
            	log.debug("RESPONSE_MESSAGE        : " + StringUtils.isNullOrEmpty(result.get("RESPONSE_MESSAGE"), ""));		// 응답메세지
            	log.debug("DETAIL_RESPONSE_CODE    : " + StringUtils.isNullOrEmpty(result.get("DETAIL_RESPONSE_CODE"), ""));	// 상세응답코드
            	log.debug("DETAIL_RESPONSE_MESSAGE : " + StringUtils.isNullOrEmpty(result.get("DETAIL_RESPONSE_MESSAGE"), ""));	// 상세응답메세지
                log.debug("===================승인 결과 끝===================");

                orderId               = StringUtils.isNullOrEmpty(result.get("ORDER_ID"), "");					// 가맹점 측 사용 주문 번호
                orderId               = StringUtils.isNullOrEmpty(result.get("ORDER_ID"), "");					// 가맹점 측 사용 주문 번호
                orderDate             = StringUtils.isNullOrEmpty(result.get("ORDER_DATE"), "");				// 가맹점 측 사용 주문 일시
                transactionId         = StringUtils.isNullOrEmpty(result.get("TRANSACTION_ID"), "");			// 빌게이트 거래 번호
                cancelKey             = StringUtils.isNullOrEmpty(result.get("CANCEL_KEY"), "");				// 취소키
                authDate              = StringUtils.isNullOrEmpty(result.get("AUTH_DATE"), "");					// 승인일시
                authNumber            = StringUtils.isNullOrEmpty(result.get("AUTH_NUMBER"), "");				// 승인번호
                authAmount            = StringUtils.isNullOrEmpty(result.get("AUTH_AMOUNT"), "");				// 승인금액
                quota                 = StringUtils.isNullOrEmpty(result.get("QUOTA"), "");						// 할부개월수
                cardCompanyCode       = StringUtils.isNullOrEmpty(result.get("CARD_COMPANY_CODE"), "");			// 카드사코드
                pinNumber             = StringUtils.isNullOrEmpty(result.get("PIN_NUMBER"), "");				// 카드번호
                simpleAffName         = StringUtils.isNullOrEmpty(result.get("SIMPLE_AFF_NAME"), "");			// 간편결제종류 (간편결제건인 경우만 전달)
                simpleDealType        = StringUtils.isNullOrEmpty(result.get("SIMPLE_DEAL_TYPE"), "");			// 간편결제구분 (간편결제건인 경우만 전달)
                responseCode          = StringUtils.isNullOrEmpty(result.get("RESPONSE_CODE"), "");				// 응답코드
                responseMessage       = StringUtils.isNullOrEmpty(result.get("RESPONSE_MESSAGE"), "");			// 응답메세지
                detailResponseCode    = StringUtils.isNullOrEmpty(result.get("DETAIL_RESPONSE_CODE"), "");		// 상세응답코드
                detailResponseMessage = StringUtils.isNullOrEmpty(result.get("DETAIL_RESPONSE_MESSAGE"), "");	// 상세응답메세지
                reserved1             = URLDecoder.decode(request.getParameter("RESERVED1"));
                reserved2             = URLDecoder.decode(request.getParameter("RESERVED2"));
                reserved3             = URLDecoder.decode(request.getParameter("RESERVED3"));
                isSuccessPay          = responseCode.equals("0000");
                
                params.put("coDiv"                , reserved1);
                params.put("orderId"              , orderId);
                params.put("orderDate"            , orderDate);
                params.put("transactionId"        , transactionId);
                params.put("cancelKey"            , cancelKey);
                params.put("authDate"             , authDate);
                params.put("authNumber"           , authNumber);
                params.put("authAmount"           , authAmount);
                params.put("quota"                , quota);
                params.put("cardCompanyCode"      , cardCompanyCode);
                params.put("pinNumber"            , pinNumber);
                params.put("simpleAffName"        , simpleAffName);
                params.put("simpleDealType"       , simpleDealType);
                params.put("responseCode"         , responseCode);
                params.put("responseMessage"      , responseMessage);
                params.put("detailResponseCode"   , detailResponseCode);
                params.put("detailResponseMessage", detailResponseMessage);
             
                // PG 결제내역 insert 
                // payService.insertOrderHistory(params);
                                
                // 결제 성공
                if(responseCode.equals("0000")) {
                	// 결제 후 예약처리...
                	ResultVO resultVO = payService.successPayLogic(params, reserved2, reserved3); 
                	
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
    			//payService.cancelLogic(reserved1, orderId, authAmount, transactionId, "C", cancelKey, ipAddr);
    		}
    	} finally {
    		try {
                if (br != null) br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    	}
    	
    	model.addAttribute("resultCode", resultCode);
    	
    	return "/book/payReturn";
    }  

}

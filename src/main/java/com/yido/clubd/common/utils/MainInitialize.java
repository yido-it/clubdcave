package com.yido.clubd.common.utils;

import java.net.InetAddress;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.yido.clubd.controller.BookController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MainInitialize {

	@Value("${spring.profiles.active}")
	public String activeType;

	@PostConstruct
	public void init() {
		try {
			log.info("======================= Initialize Start =======================");
			Globals.serverIpAddress = Utils.getLocalIpAddr();
			log.info("Server Ip Address : " + Globals.serverIpAddress);		
			//Globals.fileUploadPath = filePath;
			//log.info("File Upload Path : " + filePath);		
			log.info("======================= Initialize Finish =======================");
			
			log.info("======================= Pay Info Start =======================");
			Globals.serviceId = Utils.getProperties("Globals.Pay.Service.Id", "");
			log.info("Pay Service Id : " + Globals.serviceId);
			Globals.serviceId2 = Utils.getProperties("Globals.Pay.Service.Id2", "");
			log.info("Pay Service Id : " + Globals.serviceId2);
			Globals.serviceJs = Utils.getProperties("Globals.Pay.Service.Js", "");
			log.info("Pay Service Js : " + Globals.serviceJs);
			Globals.protocolType = Utils.getProperties("Globals.Pay.Protocol.Type", "");
			log.info("Pay Protocol Type : " + Globals.protocolType);
			Globals.returnPayUrl = Utils.getProperties("Globals.Pay.ReturnPay.Url", "");
			log.info("Pay ReturnPay Url : " + Globals.returnPayUrl);
			Globals.approveUrl = Utils.getProperties("Globals.Pay.Approve.Url", "");
			log.info("Pay Approve Url : " + Globals.approveUrl);
			Globals.cancelUrl = Utils.getProperties("Globals.Pay.Cancel.Url", "");
			log.info("Pay Cancel Url : " + Globals.cancelUrl);
			Globals.accessTarget = Utils.getProperties("Globals.Pay.Cancel.AccessTarget", "");
			log.info("Pay Access Target : " + Globals.accessTarget);
			Globals.hiddenKey = Utils.getProperties("Globals.Pay.Cancel.HiddenKey", "");
			log.info("Pay Hidden Key : " + Globals.hiddenKey);
			Globals.receiptUrl = Utils.getProperties("Globals.Pay.Receipt.Url", "");
			log.info("Pay Receipt Url : " + Globals.receiptUrl);
			Globals.authReceiptUrl = Utils.getProperties("Globals.Pay.AuthReceipt.Url", "");
			log.info("Pay Auth Receipt Url : " + Globals.authReceiptUrl);
			Globals.partnerUrl = Utils.getProperties("Globals.Pay.Partner.Url", "");
			log.info("Pay Partner Url : " + Globals.partnerUrl);
			Globals.partnerCalUrl = Utils.getProperties("Globals.Pay.Partner.Cal.Url", "");
			log.info("Pay Partner Cal Url : " + Globals.partnerCalUrl);
			log.info("======================= Pay Info Finish =======================");

			log.info("======================= SNS Info Start =======================");
			Globals.KakaoKey  = Utils.getProperties("Globals.Kakao.Key", "");
			Globals.kakaoClientId = Utils.getProperties("Globals.Kakao.Client.Id", "");
			Globals.kakaoRedirectUrl = Utils.getProperties("Globals.Kakao.Redirect.Url", "");
			
			Globals.NaverKey  = Utils.getProperties("Globals.Naver.Key", "");
			Globals.NaverCallbackUrl  = Utils.getProperties("Globals.Naver.Callback.Url", "");
			Globals.NaverDomain  = Utils.getProperties("Globals.Naver.Domain", "");
			log.info("======================= SNS Info Finish =======================");
			
			if (activeType.equals("develop")) {
				Globals.MapKey  = Utils.getPropertiesByDev("Globals.Map.Key", "");
			} else {
				Globals.MapKey  = Utils.getPropertiesByPro("Globals.Map.Key", "");
			}
			log.info("======================= MapKey : " + Globals.MapKey);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

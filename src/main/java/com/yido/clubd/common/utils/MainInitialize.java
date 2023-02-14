package com.yido.clubd.common.utils;

import java.net.InetAddress;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Service;
import com.yido.clubd.controller.BookController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@PropertySources({ @PropertySource("classpath:application.properties") })
public class MainInitialize {
	
	@Value("${spring.profiles.active}")
	public String serverType;

	@PostConstruct
	public void init() {
		try {
			log.info("======================= Initialize Start =======================");
			Globals.serverIpAddress = Utils.getLocalIpAddr();
			log.info("Server Ip Address : " + Globals.serverIpAddress);		
			//Globals.fileUploadPath = filePath;
			//log.info("File Upload Path : " + filePath);		
			log.info("======================= Initialize Finish =======================");
			
			log.info("======================= SNS Info Start =======================");
			Globals.KakaoKey  = Utils.getProperties("Globals.Kakao.Key", "");
			Globals.kakaoClientId = Utils.getProperties("Globals.Kakao.Client.Id", "");
			Globals.kakaoRedirectUrl = Utils.getProperties("Globals.Kakao.Redirect.Url", "");
			
			Globals.NaverKey  = Utils.getProperties("Globals.Naver.Key", "");
			Globals.NaverCallbackUrl  = Utils.getProperties("Globals.Naver.Callback.Url", "");
			Globals.NaverDomain  = Utils.getProperties("Globals.Naver.Domain", "");
			log.info("======================= SNS Info Finish =======================");

			if (serverType.equals("develop")) {
				log.info("======================= [develop] Pay Info Start =======================");
				Globals.serviceId = Utils.getPropertiesByDev("Globals.Pay.Service.Id", "");
				log.info("Pay Service Id : " + Globals.serviceId);
				Globals.serviceId2 = Utils.getPropertiesByDev("Globals.Pay.Service.Id2", "");
				log.info("Pay Service Id : " + Globals.serviceId2);
				Globals.serviceJs = Utils.getPropertiesByDev("Globals.Pay.Service.Js", "");
				log.info("Pay Service Js : " + Globals.serviceJs);
				Globals.protocolType = Utils.getPropertiesByDev("Globals.Pay.Protocol.Type", "");
				log.info("Pay Protocol Type : " + Globals.protocolType);
				Globals.returnPayUrl = Utils.getPropertiesByDev("Globals.Pay.ReturnPay.Url", "");
				log.info("Pay ReturnPay Url : " + Globals.returnPayUrl);
				Globals.approveUrl = Utils.getPropertiesByDev("Globals.Pay.Approve.Url", "");
				log.info("Pay Approve Url : " + Globals.approveUrl);
				Globals.cancelUrl = Utils.getPropertiesByDev("Globals.Pay.Cancel.Url", "");
				log.info("Pay Cancel Url : " + Globals.cancelUrl);
				Globals.accessTarget = Utils.getPropertiesByDev("Globals.Pay.Cancel.AccessTarget", "");
				log.info("Pay Access Target : " + Globals.accessTarget);
				Globals.hiddenKey = Utils.getPropertiesByDev("Globals.Pay.Cancel.HiddenKey", "");
				log.info("Pay Hidden Key : " + Globals.hiddenKey);
				Globals.receiptUrl = Utils.getPropertiesByDev("Globals.Pay.Receipt.Url", "");
				log.info("Pay Receipt Url : " + Globals.receiptUrl);
				Globals.authReceiptUrl = Utils.getPropertiesByDev("Globals.Pay.AuthReceipt.Url", "");
				log.info("Pay Auth Receipt Url : " + Globals.authReceiptUrl);
				Globals.partnerUrl = Utils.getPropertiesByDev("Globals.Pay.Partner.Url", "");
				log.info("Pay Partner Url : " + Globals.partnerUrl);
				Globals.partnerCalUrl = Utils.getPropertiesByDev("Globals.Pay.Partner.Cal.Url", "");
				log.info("Pay Partner Cal Url : " + Globals.partnerCalUrl);
				log.info("======================= [develop] Pay Info Finish =======================");
			} else if (serverType.equals("production")) {
				log.info("======================= [production] Pay Info Start =======================");
				Globals.serviceId = Utils.getPropertiesByPro("Globals.Pay.Service.Id", "");
				log.info("Pay Service Id : " + Globals.serviceId);
				Globals.serviceId2 = Utils.getPropertiesByPro("Globals.Pay.Service.Id2", "");
				log.info("Pay Service Id : " + Globals.serviceId2);
				Globals.serviceJs = Utils.getPropertiesByPro("Globals.Pay.Service.Js", "");
				log.info("Pay Service Js : " + Globals.serviceJs);
				Globals.protocolType = Utils.getPropertiesByPro("Globals.Pay.Protocol.Type", "");
				log.info("Pay Protocol Type : " + Globals.protocolType);
				Globals.returnPayUrl = Utils.getPropertiesByPro("Globals.Pay.ReturnPay.Url", "");
				log.info("Pay ReturnPay Url : " + Globals.returnPayUrl);
				Globals.approveUrl = Utils.getPropertiesByPro("Globals.Pay.Approve.Url", "");
				log.info("Pay Approve Url : " + Globals.approveUrl);
				Globals.cancelUrl = Utils.getPropertiesByPro("Globals.Pay.Cancel.Url", "");
				log.info("Pay Cancel Url : " + Globals.cancelUrl);
				Globals.accessTarget = Utils.getPropertiesByPro("Globals.Pay.Cancel.AccessTarget", "");
				log.info("Pay Access Target : " + Globals.accessTarget);
				Globals.hiddenKey = Utils.getPropertiesByPro("Globals.Pay.Cancel.HiddenKey", "");
				log.info("Pay Hidden Key : " + Globals.hiddenKey);
				Globals.receiptUrl = Utils.getPropertiesByPro("Globals.Pay.Receipt.Url", "");
				log.info("Pay Receipt Url : " + Globals.receiptUrl);
				Globals.authReceiptUrl = Utils.getPropertiesByPro("Globals.Pay.AuthReceipt.Url", "");
				log.info("Pay Auth Receipt Url : " + Globals.authReceiptUrl);
				Globals.partnerUrl = Utils.getPropertiesByPro("Globals.Pay.Partner.Url", "");
				log.info("Pay Partner Url : " + Globals.partnerUrl);
				Globals.partnerCalUrl = Utils.getPropertiesByPro("Globals.Pay.Partner.Cal.Url", "");
				log.info("Pay Partner Cal Url : " + Globals.partnerCalUrl);
				log.info("======================= [production] Pay Info Finish =======================");				
			} 
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

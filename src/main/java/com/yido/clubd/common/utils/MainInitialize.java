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
			Globals.KakaoClientId = Utils.getProperties("Globals.Kakao.Client.Id", "");
			Globals.KakaoRedirectUrl = Utils.getProperties("Globals.Kakao.Redirect.Url", "");
			
			Globals.NaverKey  = Utils.getProperties("Globals.Naver.Key", "");
			Globals.NaverCallbackUrl  = Utils.getProperties("Globals.Naver.Callback.Url", "");
			Globals.NaverDomain  = Utils.getProperties("Globals.Naver.Domain", "");
			log.info("======================= SNS Info Finish =======================");

			log.info("======================= [{}] Pay Info Start =======================", serverType);
			Globals.serviceId = Utils.getPropertiesByType("Globals.Pay.Service.Id", "", serverType);
			log.info("Pay Service Id : " + Globals.serviceId);
			Globals.serviceId2 = Utils.getPropertiesByType("Globals.Pay.Service.Id2", "", serverType);
			log.info("Pay Service Id : " + Globals.serviceId2);
			Globals.serviceJs = Utils.getPropertiesByType("Globals.Pay.Service.Js", "", serverType);
			log.info("Pay Service Js : " + Globals.serviceJs);
			Globals.protocolType = Utils.getPropertiesByType("Globals.Pay.Protocol.Type", "", serverType);
			log.info("Pay Protocol Type : " + Globals.protocolType);
			Globals.returnPayUrl = Utils.getPropertiesByType("Globals.Pay.ReturnPay.Url", "", serverType);
			log.info("Pay ReturnPay Url : " + Globals.returnPayUrl);
			Globals.approveUrl = Utils.getPropertiesByType("Globals.Pay.Approve.Url", "", serverType);
			log.info("Pay Approve Url : " + Globals.approveUrl);
			Globals.cancelUrl = Utils.getPropertiesByType("Globals.Pay.Cancel.Url", "", serverType);
			log.info("Pay Cancel Url : " + Globals.cancelUrl);
			Globals.accessTarget = Utils.getPropertiesByType("Globals.Pay.Cancel.AccessTarget", "", serverType);
			log.info("Pay Access Target : " + Globals.accessTarget);
			Globals.hiddenKey = Utils.getPropertiesByType("Globals.Pay.Cancel.HiddenKey", "", serverType);
			log.info("Pay Hidden Key : " + Globals.hiddenKey);
			Globals.receiptUrl = Utils.getPropertiesByType("Globals.Pay.Receipt.Url", "", serverType);
			log.info("Pay Receipt Url : " + Globals.receiptUrl);
			Globals.authReceiptUrl = Utils.getPropertiesByType("Globals.Pay.AuthReceipt.Url", "", serverType);
			log.info("Pay Auth Receipt Url : " + Globals.authReceiptUrl);
			Globals.partnerUrl = Utils.getPropertiesByType("Globals.Pay.Partner.Url", "", serverType);
			log.info("Pay Partner Url : " + Globals.partnerUrl);
			Globals.partnerCalUrl = Utils.getPropertiesByType("Globals.Pay.Partner.Cal.Url", "", serverType);
			log.info("Pay Partner Cal Url : " + Globals.partnerCalUrl);
			log.info("======================= [{}] Pay Info Finish =======================", serverType);
			
log.info("======================= AWS S3 Info Start =======================");
			
			Globals.accessKey = Utils.getProperties("Globals.awsS3.accessKey", "");
			Globals.secretKey = Utils.getProperties("Globals.awsS3.secretKey", "");
			Globals.endPoint = Utils.getProperties("Globals.awsS3.endPoint", "");
			Globals.bucketName = Utils.getProperties("Globals.awsS3.bucketName", "");
			
			log.info("======================= AWS S3 Info Finish =======================");
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

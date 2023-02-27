package com.yido.clubd.common.utils;

import org.springframework.beans.factory.annotation.Value;

public class Globals {	
	public static String serverIpAddress;
	public static String fileUploadPath;
	public static String seedKey;
	
	// amazonS3 SDK
	public static String accessKey;
	public static String secretKey;
	public static String endPoint;
	public static String bucketName;
	public static String videoBucketName;

	// PAY
	public static String serviceId;
	public static String serviceId2;
	public static String serviceJs;
	public static String protocolType;
	public static String returnPayUrl;
	public static String approveUrl;
	public static String cancelUrl;
	public static String accessTarget;
	public static String hiddenKey;
	public static String receiptUrl;
	public static String authReceiptUrl;
	public static String partnerUrl;
	public static String partnerCalUrl;
	
	// SNS - Kakao
	public static String KakaoKey;
	public static String KakaoClientId;
	public static String KakaoRedirectUrl;
	// SNS - Naver
	public static String NaverKey;
	public static String NaverCallbackUrl;
	public static String NaverDomain;
	
}

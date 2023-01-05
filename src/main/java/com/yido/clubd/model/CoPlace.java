package com.yido.clubd.model;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 테이블명 : CO_PLACE
 * 테이블 설명 : 지점코드
 * 
 * @author bae
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CoPlace {

	private String coDiv;				// 지점코드
	private String coName;				// 지점명칭
	private String coShortName;			// 지점명칭(단축명)
	private String coEngName; 			// 지점명칭(영문)
	private String coRepreName;			// 대표자명
	private String coZip;				// 우편번호
	private String coAddr1;				// 우편번호기본
	private String coAddr2; 			// 우편번호상세
	private String coUptae; 			// 업태
	private String coJongmok;			// 종목
	private String coTel;				// 전화번호
	private String coFax; 				// 팩스번호 
	private String coEmail; 			// 공용메일
	private String coTaxCode; 			// 관할세무코드
	private String coTaxOffice; 		// 관할세무서
	private String coOpenDate; 			// 지점 오픈일자
	private String coFirmNo;			// 법인번호
	private String coMpDiv;				// 회원제M, 퍼블릭P 구분
	private int coSort;					// 정렬순번
	private String useYn; 				// 사용여부
	private int smsCode;				// SMS 구분 코드
	private String coImage;				// 사업장이미지경로
	private int coMilerate;				// 마일리지 적용율
	private String coGiftcardcode;		// 문화상품권사용처코드
	private String coTerminalNo;		// SMS모듈에 심어질 사업장코드
	private String coVanType;			// 밴사 구분
	private String coSmsFg;				// 알림톡 사용여부
	
	private String inputStaff; 			// 입력사번

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime inputDatetime;	// 입력일시
	
	private String inputIp;				// 입력IP
	private String updateStaff; 		// 수정사번

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updateDatetime;	// 수정일시
	
	private String updateIp; 			// 수정IP

}



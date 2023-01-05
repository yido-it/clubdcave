package com.yido.clubd.model;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 테이블명 : DR_BK_TIME_VOUCHER 
 * 테이블 설명 : 예약타입별 이용권 설정
 * 
 * @author bae
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DrBkTimeVoucher {

	private String coDiv;					// 지점코드
	private String bkDay;					// 예약일자
	private String bayCondi;				// 예약BAY 구성 단위
	private String bkTime;					// 시작시간
	private String vcCoDiv;					// 이용권 지점코드
	private String vcCd;					// 이용권코드

	private String inputStaff; 				// 입력사번

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime inputDatetime;	// 입력일시
	
	private String inputIp;					// 입력IP

}



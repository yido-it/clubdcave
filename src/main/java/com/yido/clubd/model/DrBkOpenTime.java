package com.yido.clubd.model;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 테이블명 : DR_BK_OPEN_TIME
 * 테이블 설명 : 예약오픈일정 시간별
 * 
 * @author bae
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DrBkOpenTime {

	private String msLevel;					// 회원등급(레슨프로,등급1,등급2)
	private String coDiv;					// 지점코드
	private String bkDay;					// 예약일자
	private String bayCondi;					// 예약BAY구성단위
	private String bkTime;					// 시작시간
	
	private String bkFromDay;				// 예약시작일자
	private String bkStartTime;				// 예약 시작 시간
	private String bkToDay;					// 예약종료일자
	private String bkEndTime;				// 예약 종료 시간
	
	private String bkOpenCount;				// 오픈수량
	private String bkRemCount;				// 잔여수량
	
	private String inputStaff; 				// 입력사번

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime inputDatetime;	// 입력일시
	
	private String inputIp;					// 입력IP

}



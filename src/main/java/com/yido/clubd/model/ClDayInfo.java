package com.yido.clubd.model;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 테이블명 : CL_DAYINFO
 * 테이블 설명 : 월력
 * 
 * @author bae
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ClDayInfo {

	private String coDiv;					// 지점코드
	private String clSolar;					// 양력일자
	private String clLunar; 				// 음력일자
	private String clDaydiv; 				// 요일구분
	private String clBusiness; 				// 주중주말구분 1:주중, 2:토요일, 3:일요일, 4:공휴일, 5:휴장
	private String clSeason; 				// 동절기구분(1:일반,2:동절기)
	private String clHoliremark; 			// 휴장사유
	private String clOpentime;				// 오픈시간
	private String clClosetime;				// 마감시간
	private String clWork;					// 인사용주중구분 월~금:1, 토:2, 일,공휴:4
	private int clDayWeek; 					// 일 주차
	private int clMonthWeek; 				// 월 주차
	private int clYearWeek; 				// 년 주차
	private String clRain;					// 우천여부
	private String clBkclass;				// 예약방식
	private String clSelect;				 // 0:실시간/1:대기
	
	private String inputStaff; 				// 입력사번

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime inputDatetime;	// 입력일시
	
	private String inputIp;					// 입력IP
	private String updateStaff; 			// 수정사번

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updateDatetime;	// 수정일시
	
	private String updateIp; 				// 수정IP

}



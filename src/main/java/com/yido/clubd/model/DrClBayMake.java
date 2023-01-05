package com.yido.clubd.model;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 테이블명 : DR_CL_BAY_MAKE
 * 테이블 설명 : 일자별 BAY별 생성규칙
 * 
 * @author bae
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DrClBayMake {

	private String coDiv; 		// 지점코드
	private String bkDay;		// 예약일자
	private String bayCondi; 	// 예약BAY구성단위
	private String vcFromTime;	// 적용시작시간
	private String vcToTime;	// 적용종료시간
	private int bayUseMinute;	// 이용시간(분)
	private int bayGapMinute;	// 정리시간(분)

}



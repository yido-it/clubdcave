package com.yido.clubd.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 테이블명 : DR_PRO_SCHEDULE_LOG
 * 테이블 설명 : 레슨프로일정 로그
 * 
 * @author bae
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DrProScheduleLog {

	private String coDiv;			// 지점코드
	private String msNum;			// 회원번호
	private int logSeq; 			// 로그순번
	private String logDiv; 			// 로그종류(I,U,D)
	private int scSeq;				// 순번
	private String scDayofweek;		// 레슨 요일
	private String scFromTime;		// 레슨 시작시간
	private String scEndTime; 		// 레슨 종료시간

}



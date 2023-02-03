package com.yido.clubd.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 테이블명 : DR_MS_CO_INFO
 * 테이블 설명 : 회원지점연결정보
 * 
 * @author bae
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DrMsCoInfo {

	private String coDiv;				// 지점코드
	private String msNum; 				// 회원번호
	private String msRegister; 			// 최초등록업장 여부
	private String msFirstPick;			// 선호 업장
	private String useYn;				// 사용여부
	private int lessonWeekCnt;			// 주 레슨 횟수(일정 집계)
	private String lessonOutdoor;		// 필드 레슨 요일(YYNYYNY)

}



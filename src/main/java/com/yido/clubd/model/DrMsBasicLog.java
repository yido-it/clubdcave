package com.yido.clubd.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 테이블명 : DR_MS_BASIC_LOG
 * 테이블 설명 : 회원추가정보 로그
 * 
 * @author bae
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DrMsBasicLog {

	private String msNum;				// 회원번호
	private int logSeq; 				// 로그순번
	private String logDiv; 				// 로그종류(I,U,D)
	private int msHandcap;				// 핸디캡
	private String msLessonExpYn;		// 레슨경험유무
	private int msLessonMinute;			// 1회레슨시 선호시간(분)
	private int msLessonTrem;			// 레슨빈도

}



package com.yido.clubd.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 테이블명 : PRO_NOTICE
 * 테이블 설명 : 레슨프로특이사항
 * 
 * @author bae
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ProNotice {

	private String msNum;		// 회원번호
	private String noticeDiv;	// 비고 종류(경력, 레슨계획, 유투브채널,..)
	private String proMustYn;	// 필수 입력 여부
	private String proRemark;	// 비고 사항
	private String noticeOpenYn; // 공개 여부
}



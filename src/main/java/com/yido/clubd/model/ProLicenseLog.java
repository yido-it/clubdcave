package com.yido.clubd.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 테이블명 : PRO_LICENSE_LOG
 * 테이블 설명 : 레슨프로자격사항 로그
 * 
 * @author bae
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ProLicenseLog {

	private String msNum;		// 회원번호
	private String licSeq;		// 순번
	private int logSeq; 		// 로그순번
	private String logDiv; 		// 로그종류(I,U,D)
	private int licKind;		// 라이센스 코드
	private String licRemark;	// 비고
}



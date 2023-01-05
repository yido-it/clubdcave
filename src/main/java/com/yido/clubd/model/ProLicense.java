package com.yido.clubd.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 테이블명 : PRO_LICENSE
 * 테이블 설명 : 레슨프로자격사항
 * 
 * @author bae
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ProLicense {

	private String msNum;		// 회원번호
	private String licSeq;		// 순번
	private int licKind;		// 라이센스 코드
	private String licRemark;	// 비고
}



package com.yido.clubd.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 모델명 : MemberVO 
 * 모델 설명 : 프로 정보
 * 
 * @author MSYOO
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ProVO {

private String msName;
	
	/* PRO_NOTICE 레슨프로특이사항 */
	private String msNum;			// 회원번호
	private String noticeDiv;		// 비고 종류(경력, 레슨계획, 유투브채널,..)
	private String proMustYn;		// 필수 입력 여부
	private String proRemark;		// 비고 사항
	private String noticeOpenYn;	// 공개 여부
	
	/* PRO_IMAGE 레슨프로 이미지/영상 */
	private String imgDiv;
	private String imgSeq;
	private String imgLink;
	private String imgFilename;
	private String imgData;
	
	/* PRO_LICENSE 레슨프로자격사항 */
	private Integer licSeq;			// 순번
	private String licKind;			// 라이센스 코드
	private String licRemark;		// 비고
	private String licName;		// 라이센스 이름
	
	private String coDiv;
	
	
}



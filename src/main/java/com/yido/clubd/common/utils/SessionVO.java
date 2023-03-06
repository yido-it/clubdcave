package com.yido.clubd.common.utils;

import lombok.Data;

@Data
public class SessionVO {
	
	private String msNum;				// 회원정보 
	private String msStatus;			// 상태
	private String msBkGrant;			// 예약가능여부(위약 관련)
	private String msDivision;			// 프로/회원 구분
	private String msLevel; 			// 회원 등급
	private String msLoginCd; 			// 로그인구분
	private String msSessionKey;		// 세션키
	private String msSessionLimit;		// 세션만료시간
	private String msLink; 				// 가족회원연결번호
	private String msId;				// 회원ID
	private String msPassword;			// 패스워드
	private String msName;				// 고객성명
	private String msEname; 			// 고객영문성명
	private String msNickname; 			// 닉네임
	private String msImagename; 		// 프로필이미지명
	private String msSex; 				// 성별
	private String msBirth; 			// 생일일자
	private String msBirthYl; 			// 생일(양,음)구분
	private String msPhone;				// 핸드폰번호 전체 
	private String msFirstPhone1;		// 핸드폰번호1 FIRST
	private String msMidPhone1; 		// 핸드폰번호1 MID
	private String msLastPhone1; 		// 핸드폰번호1 LAST
	private String smsChk1;				// 문자발송여부1
	private String msBlank;				// 상태구분(Y:탈퇴)
	private String msVoucherDay;		// 이용권종료일자
	private String msWeddingday;		// 결혼기념일
	private String msWedding;  			// 결혼유무
	private String msEmail; 			// 이메일
	private String msEmailYn; 			// 이메일수신여부
	private String msHometel; 			// 집전화번호
	private String msHomezip;			// 집우편번호
	private String msHomeaddr1;			// 집주소
	private String msHomeaddr2; 		// 집주소상세
	private String msPostYn;			// 우편수신여부
	private String msCompnm; 			// 회사명
	private String msCompno; 			// 사업자번호
	private String msComptel; 			// 회사전화번호
	private String msCompfax; 			// 회사팩스번호
	private String msCompzip; 			// 회사우편번호
	private String msCompaddr1; 		// 회사주소
	private String msCompaddr2; 		// 회사주소상세
	private String msCompPostYn; 		// 회사우편수신여부
	private String msCompdept;			// 회사부서
	private String msCompposition;		// 회사직위
	private String msRegday; 			// 가입일자
	private String msEndday; 			// 탈퇴일자
	private String msRemark; 			// 비고사항
	private String msMktAgreeYn; 		// 마케팅동의여부(약관동의)
	private String msDormant; 			// 휴면계정구분
	private String loginTime;			// 홈페이지로그인(일시분초)
	private String msDi; 				// 실명인증
	private String msJobCd; 			// 직업코드	
	private String msArea1; 			// 직업코드	
	private String msArea2; 			// 직업코드	
	
	public String getFullMsPhone() {
		return this.msFirstPhone1 + this.msMidPhone1 + this.msLastPhone1;
	}	
}

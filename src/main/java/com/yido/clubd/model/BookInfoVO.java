package com.yido.clubd.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;

/** 
 * @author bae
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BookInfoVO {

	private String coDiv;			// 지점코드
	private String bkDay;			// 예약일자
	private String bkTime;			// 시작시간
	private String bayCondi;		// 예약BAY 구성 단위
	private int bkAmount;			// 예약금액
	private String msLevel;			// 회원등급
	private String userMail;		// 이메일
	private String msId;			// 아이디
	private String userName;		// 이름
	private String ipAddr;			// ip
	private String phone; 			// 전화번호
	private String bkState; 		// 예약상태
	
	private String availableYn;		// (예약선점용 조회할때 사용) Y이면 예약 가능한 데이터 조회
	
	private List<Map<String, Object>> bkList;		// [{"bkTime":"08:00","amount":50000},{"bkTime":"13:00","amount":50000}]
	private List<String> timeList;					// 08:00, 13:00 
}



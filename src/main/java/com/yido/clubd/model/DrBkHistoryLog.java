package com.yido.clubd.model;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 테이블명 : DR_BK_HISTORY_LOG
 * 테이블 설명 : 예약내역 로그
 * 
 * @author bae
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DrBkHistoryLog {

	private String bkSerialNo;				// 예약고유번호
	private int logSeq; 					// 로그순번
	private String logDiv; 					// 로그종류(I,U,D)
	private String coDiv;					// 지점코드
	private String bkDay; 					// 예약일자 
	private String bayCondi; 				// 예약BAY 구성 단위
	private String bkTime;					// 시작시간
	private String msNum;					// 회원번호
	private String bkName;					// 예약자명
	private String bkFirstPhone; 			// 연락처 1
	private String bkMidPhone;				// 연락처 2
	private String bkLastPhone;				// 연락처 3
	private String bkState;					// 예약상태(정상,노쇼,취소,정산완료)
	private String bkSmsSend;				// SMS 전송 완료 여부
	private String bkPayDiv; 				// 예약 지불 수단(1-이용권, 2-선불)
	private String bkMethod; 				// 예약수단(M:모바일,T:전화,W:워크인)
	private String costDiv;					// 요금구분(이용권,일반)
	private String costCoDiv;				// 지점코드
	private String costName; 				// 요금 명칭
	private String costCd;					// 요금코드
	
	private String inputStaff; 				// 입력사번

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime inputDatetime;	// 입력일시
	
	private String inputIp;					// 입력IP
}



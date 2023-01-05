package com.yido.clubd.model;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 테이블명 : DR_VOUCHER_CODE_LOG
 * 테이블 설명 : 이용권코드 로그
 * 
 * @author bae
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DrVoucherCodeLog {

	private String coDiv;					// 지점코드
	private String vcCd; 					// 이용권코드
	private int logSeq; 					// 로그순번
	private String logDiv; 					// 로그종류(I,U,D)
	private String vcName;					// 이용권명칭
	private String vcDivision;				// 이용권구분(기간권, 레슨권, 쿠폰)
	private int vcLimitCnt;					// 총 수량
	private int vcDayLimitCnt;				// 일 사용 제한 횟수
	private int vcMonth; 					// 유효 개월수
	private int vcMinute;					// 1회 이용 시간(분)
	private String vcUseWeek;				// 이용구분(주중,주말,전체)
	private String vcEnterFromTime;			// 이용가능 시작시간
	private String vcEnterToTime;			// 이용가능 종료시간
	private String vcDisplayYn; 			// 표출 여부
	
	private String inputStaff; 				// 입력사번

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime inputDatetime;	// 입력일시
	
	private String inputIp;					// 입력IP

}



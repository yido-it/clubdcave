package com.yido.clubd.model;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 테이블명 : DR_BK_TIME
 * 테이블 설명 : 예약타임
 * 
 * @author bae
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DrBkTime {

	private String coDiv;					// 지점코드
	private String bkDay; 					// 예약일자
	private String bayCondi; 				// 예약BAY 구성 단위
	private String bkTime;					// 시작시간
	private int bkTotalCount;				// 총 수량
	private int bkRemCount;					// 잔여 수량
	private int bkUseTime;					// 이용시간(분)
	private int bkGapTime;					// 정리시간(분)
	private int bkDcAmount; 				// 특가금액(할인금액)
	private String bkDcRemark;				// 특가 적용 사유
	private int walkCnt;					// 현장예약 수량
	private String costCoDiv;				// 지점코드
	private String costName;				// 요금 명칭(구버전의 일자)
	private String costCd;					// 요금코드
	
	private String inputStaff; 				// 입력사번

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime inputDatetime;	// 입력일시
	
	private String inputIp;					// 입력IP
	private String amount;					

}



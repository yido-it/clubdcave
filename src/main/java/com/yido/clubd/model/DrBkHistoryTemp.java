package com.yido.clubd.model;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 테이블명 : DR_BK_HISTORY_TEMP
 * 테이블 설명 : 예약내역 임시 테이블
 * 
 * @author bae
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DrBkHistoryTemp {

	private String coDiv;					// 지점코드
	private String serialNo;				// 고유번호 (YYMMDDHHMMSS)
	private String msNum;					// 회원번호
	private String bayCd;					// BAY 코드
	private String bkDay;					// 예약날짜
	private String bkTime;					// 예약시간 (쉼표로 구분해서 들어감) 화면에 보여주는 용도 ex) 11:00, 13:00
	private String bkTime2;					// 예약시간 (쉼표로 구분해서 들어감) ex) 1100, 1300
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime inputDatetime;	// 입력일시
	
	private String inputIp;					// 입력IP
}



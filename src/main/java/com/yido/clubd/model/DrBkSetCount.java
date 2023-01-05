package com.yido.clubd.model;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 테이블명 : DR_BK_SET_COUNT
 * 테이블 설명 : 예약오픈수량
 * 
 * @author bae
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DrBkSetCount {

	private String msLevel;					// 회원등급(레슨프로, 등급1, 등급2)
	private String bayCondi;				// 예약BAY 구성 단위
	private String bkTime;					// 시작시간
	private String coDiv; 					// 지점코드
	private String bkDay;					// 양력일자
	private int bkOpenCount;				// 오픈 수량
	private String bkLastOpenYn;			// 마지막 오픈 여부
	
	private String inputStaff; 				// 입력사번

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime inputDatetime;	// 입력일시
	
	private String inputIp;					// 입력IP
}



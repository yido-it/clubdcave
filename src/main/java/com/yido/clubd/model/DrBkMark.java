package com.yido.clubd.model;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 테이블명 : DR_BK_MARK
 * 테이블 설명 : 예약선점용
 * 
 * @author bae
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DrBkMark {

	private String coDiv;					// 지점코드
	private String bkDay;					// 예약일자
	private String bayCondi; 				// 예약BAY 구성 단위
	private String bkTime; 					// 시작시간
	private int bkSeq;						// 순번(타임별 총수량 만큼)
	private String bkState;					// 상태(Y:사용가능,N:사용불가) 예약수량에 대한 잔여량 소멸 처리용
	private String entryMethod;				// 점유경로(매장,모바일)
	private String entryUser;				// 점유자ID
	private String entryIp; 				// 점유IP
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime entryDatetime;	// 점유종료시간

}



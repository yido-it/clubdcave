package com.yido.clubd.model;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 테이블명 : DR_BAY_INFO
 * 테이블 설명 : BAY관리
 * 
 * @author bae
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DrBayInfo {

	private String coDiv;			// 지점코드
	private String bayCd;			// BAY 코드
	private String baySpace;		// BAY 위치(공통코드 : 010 )지하/2층/3층
	private String bayType;			// BAY 형태(공통코드 : 011) BAY/테이블
	private String bayName;			// BAY 명칭
	private String baySpec1;		// BAY 스펙1
	private String baySpec2;		// BAY스펙2
	private String bayState;		// BAY 상태(1-정상,2-사용중,3-고장)
	
	private String inputStaff; 		// 입력사번

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime inputDatetime;	// 입력일시
	
	private String inputIp;					// 입력IP
}



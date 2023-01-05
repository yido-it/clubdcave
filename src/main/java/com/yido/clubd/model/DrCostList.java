package com.yido.clubd.model;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 테이블명 : DR_COST_LIST
 * 테이블 설명 : 요금세부내역
 * 
 * @author bae
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DrCostList {

	private String coDiv;					// 지점코드
	private String costName;				// 요금 명칭
	private String costCd;					// 요금코드
	private String costDiv;					// 요금구분(이용권,일반)
	private int drAmount;					// 금액
	private int drNet;						// 공급가
	private int drVat;						// 부가세
	private String displayYn;				// 대표 표출 여부
	
	private String inputStaff; 				// 입력사번

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime inputDatetime;	// 입력일시
	
	private String inputIp;					// 입력IP

}



package com.yido.clubd.model;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 테이블명 : DR_VOUCHER_COST
 * 테이블 설명 : 이용권요금정보
 * 
 * @author bae
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DrVoucherCost {

	private String coDiv;					// 지점코드
	private String costDay;					// 요금 설정 일자
	private String vcCd; 					// 이용권코드
	private int vcAmount;					// 금액
	private int vcNet;						// 공급가
	private int vcVat; 						// 부가세
	
	private String inputStaff; 				// 입력사번

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime inputDatetime;	// 입력일시
	
	private String inputIp;					// 입력IP

}



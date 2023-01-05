package com.yido.clubd.model;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 테이블명 : SL_MASTER
 * 테이블 설명 : 매출내역
 * 
 * @author bae
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SlMaster {

	private String coDiv;				// 지점코드
	private String saleDay;				// 매출일자
	private int saleSeq;				// 매출순번
	private String slShopCd;			// 업장코드
	private String slBillNo; 			// 영수증번호
	private String bkSerialNo;			// 예약고유번호
	private String bayCd; 				// BAY코드
	private String slPayDiv; 			// 선불/후불(예약)/후불(테이블) 구분
	private int slTotalAmount;			// 총매출액
	private String slState;				// 상태(미정산,정산,VOID)
	private String slRemark;			// 비고
	private String slVoidRemark;		// VOID 사유

	private String inputStaff; 				// 입력사번

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime inputDatetime;	// 입력일시
	
	private String inputIp;					// 입력IP

}



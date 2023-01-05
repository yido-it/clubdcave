package com.yido.clubd.model;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 테이블명 : DR_VOUCHER_SALE
 * 테이블 설명 : 회원이용권 구매내역
 * 
 * @author bae
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DrVoucherSale {

	private String coDiv;					// 지점코드
	private String saleDay;					// 매출일자
	private int saleSeq;					// 매출 순번
	private String msNum;					// 회원번호
	private String vcCd;					// 이용권코드
	private String drSerialNo; 				// 거래고유번호
	private String lkCoDiv;					// 지점코드
	private String lkNum;					// 사물함번호
	private String vcFromDay;				// 이용시작일자
	private String vcToDay;					// 이용종료일자
	private String vcState;					// 이용권 상태(정상,휴회,종료,환불)
	private int vcLimitCnt;					// 총 수량
	private int vcServiceCnt;				// 서비스 수량
	private int vcUseCnt;					// 사용수량
	private int vcRemCnt;					// 잔존수량
	private String vcRemark;				// 비고
	private String useYn; 					// 사용가능여부

	private String inputStaff; 				// 입력사번

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime inputDatetime;	// 입력일시
	
	private String inputIp;					// 입력IP

}



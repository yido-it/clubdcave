package com.yido.clubd.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 테이블명 : DR_VOUCHER_USE 
 * 테이블 설명 : 이용권사용내역
 * 
 * @author bae
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DrVoucherUse {

	private String bkSerialNo;	// 예약고유번호
	private String coDiv;		// 지점코드
	private String vcCoDiv;		// 이용권지점코드
	private String saleDay;		// 매출일자
	private int saleSeq;		// 매출 순번
	private int listSeq;		// 세부 순번

}



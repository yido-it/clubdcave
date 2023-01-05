package com.yido.clubd.model;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 테이블명 : DR_VOUCHER_LIST
 * 테이블 설명 : 이용권세부내역
 * 
 * @author bae
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DrVoucherList {

	private String coDiv;					// 지점코드
	private String saleDay;					// 매출일자
	private int saleSeq;					// 매출 순번
	private int listSeq;					// 세부 순번(기간권-1, 쿠폰-수량만큼)
	private String vcState; 				// 상태(N-미시용,Y-사용,X-사용불가)
	
	private String inputStaff; 				// 입력사번

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime inputDatetime;	// 입력일시
	
	private String inputIp;					// 입력IP

}



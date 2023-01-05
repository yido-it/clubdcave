package com.yido.clubd.model;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 테이블명 : PG_HISTORY
 * 테이블 설명 : PG 결제내역
 * 
 * @author bae
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PgHistory {

	private String coDiv;	
	private int orderId;
	private String orderSeq;
	private String transactionId;
	private String cancelkey;
	private String orderDate;
	private String authDate;
	private String authNumber;
	private String authAmount;
	private String quota;
	private String cardCompanyCode;
	private String pinNumber;
	private String simpleAffName;
	private String simpleDealType;
	private String cancelType;
	private String cancelDate;
	private String cancelAmount;
	private String partCancelAmount;
	private String seqNumber;
	private String responseCode;
	private String responseMessage;
	private String detailResponseCode;
	private String detailResponseMessage;
	private String bkDay;
	private String bkCos;
	private String bkTime;
	private String bkRsvno;
	private String enChkinno;
	private String enChkinno_advance;

	private String inputStaff; 				// 입력사번

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime inputDatetime;	// 입력일시
	
	private String inputIp;					// 입력IP
	private String updateStaff; 			// 수정사번

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updateDatetime;	// 수정일시
	
	private String updateIp;				// 수정IP

}



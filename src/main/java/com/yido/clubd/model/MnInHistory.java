package com.yido.clubd.model;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 테이블명 : MN_IN_HISTORY
 * 테이블 설명 : 입금내역
 * 
 * @author bae
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MnInHistory {
	
	private String coDiv;				// 지점코드
	private String mnInDay;				// 입금일자
	private int mnSeq;					// 입금순번
	private int mnShopCd;				// 업장코드
	private String mnBeforeDiv;			// 입금구분(1:후불,2:선불)
	private String mnPayDiv;			// 지불구분(01:현금,02:카드,03:카드수동)
	private String mnInName;			// 입금자qh
	private String msNum;				// 회원번호
	private int mnRevAmount;			// 총입금액
	private int	mnInAmount;				// 결제금액
	private int	mnChangeAmount; 		// 반환금액
	private String mnInNo;				// 카드번호
	private String mnCardApproval;		// 승인번호
	private String mnMonth;				// 카드할부개월
	private String mnAppDate;			// 승인일시
	private String mnCardDiv;			// 매입사코드
	private String mnCardGano; 			// 카드가맹점번호
	private String mnTmid;				// 단말기번호
	private String mnCardSwipe;			// 카드SWIPE(@:정상)
	private String mnCardName;			// 카드발급사명
	private String mnRemark;			// 비고
	private String mnDeleteYn;			// 삭제여부
	private String mnCancelYn;			// 취소여부
	private String mnOriCoDiv;			// 원본 지점코드
	private String mnOriInDay;			// 원본 입금일자
	private int mnOriMnSeq;				// 원본 입금순번
	private String mnOriSerialNo;		// 원본 거래고유번호
	private String orderId;
	private String transactionId;
	private String cancelkey;
	private String mnSerialNo;			// 이용권 구매시 이용권 고유번호 insert
	
	private String inputStaff; 			// 입력사번

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime inputDatetime;// 입력일시
	
	private String inputIp;				// 입력IP

}



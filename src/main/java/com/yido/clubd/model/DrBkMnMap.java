package com.yido.clubd.model;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 테이블명 : DR_BK_MN_MAP 
 * 테이블 설명 : 예약 입금 연결 정보
 * 
 * @author bae
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DrBkMnMap {

	private String coDiv;			// 지점코드
	private String bkSerialNo;		// 예약고유번호
	private String mnCoDiv;			// 입금 지점코드
	private String mnInDay;			// 입금일자
	private int mnSeq;				// 입금순번
	private int mnAmount;			// 할당 금액
}



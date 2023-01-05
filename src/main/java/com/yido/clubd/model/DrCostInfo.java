package com.yido.clubd.model;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 테이블명 : DR_COST_INFO
 * 테이블 설명 : 요금정보
 * 
 * @author bae
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DrCostInfo {

	private String coDiv;					// 지점코드
	private String costName;				// 요금 명칭(성수기, 비수기, ...)
	private String costCd; 					// 요금코드(공통코드)
	private String costRemark; 				// 요금 설명
	private String useYn; 					// 사용여부
	
	private String inputStaff; 				// 입력사번

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime inputDatetime;	// 입력일시
	
	private String inputIp;					// 입력IP

}



package com.yido.clubd.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;

/** 
 * 이용권 관련
 * 
 * @author bae
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class VouInfoVO {

	private int saleSeq; 			// 이용권 매출순번 
	private String coDiv;
	private String ipAddr;			// ip
	private String drSerialNo;		// 이용권 매출 고유번호
}



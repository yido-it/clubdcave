package com.yido.clubd.model;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 모델명 : Common 
 * 모델 설명 : 공통코드 정보
 * 
 * @author MSYOO
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CommonVO {
	private String coDiv;
	private String cdDivision;
	private String cdCode;
	private String cdTitle1;
	private String cdTitle2;
	private String cdTitle3;
}



package com.yido.clubd.model;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Push extends CommonVO{

	private String  cmpyCd;
	private Integer siteCd;
	private Integer ptnrCd;
	private String	userId;
	private String  siteAuth;
	private Integer mainSeq;
	private Integer subSeq;

	private String	device;
	private String 	token;


	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate logoutDt;

	private	String	version;

	private	Integer	sendSeq;
	private String	title;
	private String	contents;
	private String	forwardUrl;
	private String  imgUrl;

	private Integer badgeCnt;

	private int	targetTp;

	private Boolean successYn;

	private String targetIds;

	public String soundAt;
}
package com.yido.clubd.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Push extends CommonVO{
	private String msNum;
	private String device;
	private String token;
	private String ipAddr;
}
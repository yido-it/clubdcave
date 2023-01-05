package com.yido.clubd.common.utils;

public class ResultVO {
	private String code = "0000";
	private String message = "";
	private Object data;
	private Object sub;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Object getSub() {
		return sub;
	}
	public void setSub(Object sub) {
		this.sub = sub;
	}
}

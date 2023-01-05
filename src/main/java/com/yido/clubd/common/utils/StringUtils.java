package com.yido.clubd.common.utils;

import java.text.DecimalFormat;

public class StringUtils {
	public static String isNullOrEmpty(String s, String d) {
		if(s == null || s.equals("")) {
			return d;
		} 
		return s;
	}
	
	public static String isNullOrEmpty(Object s, String d) {
		if(s == null || s.equals("")) {
			return d;
		} 
		return s.toString();
	}
	
	public static String getNumberCommaFormat(Object n) {
		String returnValue = "0";
		try {
			DecimalFormat decFormat = new DecimalFormat("###,###");
			
			returnValue = decFormat.format(Integer.valueOf(n.toString()));
		} catch(Exception e) {
			returnValue = "0";
		}

		return returnValue;
	}
	
	public static String getNumberCommaFormat(int n) {
		String returnValue = "0";
		try {
			DecimalFormat decFormat = new DecimalFormat("###,###");
			
			returnValue = decFormat.format(n);
		} catch(Exception e) {
			returnValue = "0";
		}
		
		return returnValue;
	}
}

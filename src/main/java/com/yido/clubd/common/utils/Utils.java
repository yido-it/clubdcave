package com.yido.clubd.common.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Utils {

	 public static void sendData(HttpServletResponse response, Object data) {
	    	try {
	    		response.setCharacterEncoding("UTF-8"); 
	        	response.setContentType("text/html; charset=UTF-8");
	        	
	        	PrintWriter out = response.getWriter();
	            
	            out.print(data);
	            out.flush();
	            out.close();
	            
	            log.debug(String.format("[SendData] %s", data.toString()));
	    	} catch(Exception e) {
	    		e.printStackTrace();
	    	}    	
	    }
		
	    public static void sendData(HttpServletResponse response, Object data, boolean logYn) {
	    	try {
	    		response.setCharacterEncoding("UTF-8"); 
	        	response.setContentType("text/html; charset=UTF-8");
	        	
	        	PrintWriter out = response.getWriter();
	            
	            out.print(data);
	            out.flush();
	            out.close();
	            
	            if(logYn) {
	            	log.debug(String.format("[SendData] %s", data.toString()));
	            } else {
	            	log.debug(String.format("[SendData] %s", "데이터가 길어서 로그 제외"));
	            }
	    	} catch(Exception e) {
	    		e.printStackTrace();
	    	}    	
	    }
	    

	    
	    public static String getJsonValue(JSONObject obj, String key){
	    	return obj.get(key) == null ? "" : obj.get(key).toString();
	    }	
	    
	    public static String getLocalIpAddr() {
	    	String returnValue = "";
	    	InetAddress local;
			try {
			    local = InetAddress.getLocalHost();
			    returnValue = local.getHostAddress();
			} catch (UnknownHostException e1) {
			    e1.printStackTrace();
			}
			
			return returnValue;
	    }
	    
	    public static String getProperties(String key, String defaultValue) {
	    	String returnValue = null;
	        
	        try{            
	            String propFile = new ClassPathResource("application.properties").getURI().getPath();

	            Properties props = new Properties();
	             
	            FileInputStream fis = new FileInputStream(propFile);
	             
	            props.load(new java.io.BufferedInputStream(fis));           
	            
	            returnValue = props.getProperty(key) ;
	        }catch(Exception e){
	            e.printStackTrace();
	        }
	        
	        return returnValue == null ? defaultValue : returnValue;
	    }
	    
	    public static String getExtension(String fileName) {
	    	String returnValue = "";
	    	
	    	try {
	    		returnValue = fileName.substring(fileName.lastIndexOf(".") + 1);
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}
	    	
	    	return returnValue;
	    }
	    
	    public static String convertSpecialCharacters(String content) {
	    	String returnValue = content;

			returnValue = returnValue.replaceAll("&", "&amp;");
			returnValue = returnValue.replaceAll("<", "&lt;");
			returnValue = returnValue.replaceAll(">", "&gt;");
			returnValue = returnValue.replaceAll("\"", "&quot;");
	    	
	    	return returnValue;
	    }
	    
	    /**
	     * Disposition 지정하기
	     * @param filename
	     * @param request
	     * @param response
	     * @throws Exception
	     */
	    public static void setDisposition(String filename, HttpServletRequest request, HttpServletResponse response) throws Exception {
	        String browser = getBrowser(request);
	        
	        String dispositionPrefix = "attachment; filename=";
	        String encodedFilename = null;
	        
	        if (browser.equals("MSIE")) {
	            encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
	        } else if (browser.equals("Trident")) { // IE11 문자열 깨짐 방지
	            encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
	        } else if (browser.equals("Firefox")) {
	            encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
	        } else if (browser.equals("Opera")) {
	            encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
	        } else if (browser.equals("Chrome")) {
	            StringBuffer sb = new StringBuffer();
	            for (int i = 0; i < filename.length(); i++) {
	                char c = filename.charAt(i);
	                if (c > '~') {
	                    sb.append(URLEncoder.encode("" + c, "UTF-8"));
	                } else {
	                    sb.append(c);
	                }
	            }
	            encodedFilename = sb.toString();
	        } else {
	            throw new IOException("Not supported browser");
	        }
	        
	        response.setHeader("Content-Disposition", dispositionPrefix + encodedFilename);
	        
	        if ("Opera".equals(browser)) {
	            response.setContentType("application/octet-stream;charset=UTF-8");
	        }
	    }
	    
	    /**
	     * 브라우저 구분 얻기
	     * @param request
	     * @return
	     */
	    public static String getBrowser(HttpServletRequest request) {
	        String header = request.getHeader("User-Agent");
	        if (header.indexOf("MSIE") > -1) {
	            return "MSIE";
	        } else if (header.indexOf("Trident") > -1) { // IE11 문자열 깨짐 방지
	            return "Trident";
	        } else if (header.indexOf("Chrome") > -1) {
	            return "Chrome";
	        } else if (header.indexOf("Opera") > -1) {
	            return "Opera";
	        }
	        return "Firefox";
	    }
	    
	    public static String getClientIpAddress(HttpServletRequest request) { 
	        String ip = request.getHeader("X-Forwarded-For");
	 
	        if (ip == null) {
	            ip = request.getHeader("Proxy-Client-IP");
	        }
	        if (ip == null) {
	            ip = request.getHeader("WL-Proxy-Client-IP");
	        }
	        if (ip == null) {
	            ip = request.getHeader("HTTP_CLIENT_IP");
	        }
	        if (ip == null) {
	            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
	        }
	        if (ip == null) {
	            ip = request.getRemoteAddr();
	        }
	 
	        return ip; 
	    }
	    
	    public static String getClientIpAddress(MultipartHttpServletRequest request) { 
	        String ip = request.getHeader("X-Forwarded-For");
	 
	        if (ip == null) {
	            ip = request.getHeader("Proxy-Client-IP");
	        }
	        if (ip == null) {
	            ip = request.getHeader("WL-Proxy-Client-IP");
	        }
	        if (ip == null) {
	            ip = request.getHeader("HTTP_CLIENT_IP");
	        }
	        if (ip == null) {
	            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
	        }
	        if (ip == null) {
	            ip = request.getRemoteAddr();
	        }
	 
	        return ip; 
	    }
	    
	    public static String getCurrentDate(String format) {
	    	return new SimpleDateFormat(format).format(new Date());
	    }
	    
	    public static int getDiffOfDate(String begin, String end) {
	    	int returnValue = 0;
	    	
	    	try {
	    		 SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
	    	      
	    	      Date beginDate = formatter.parse(begin);
	    	      Date endDate = formatter.parse(end);

	    	      long diff = endDate.getTime() - beginDate.getTime();
	    	      returnValue = (int) (diff / (24 * 60 * 60 * 1000));
	    	} catch(Exception e) {
	    		e.printStackTrace();
	    	}
	     
	      return returnValue;
	    }
}

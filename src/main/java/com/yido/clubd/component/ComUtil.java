package com.yido.clubd.component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.DriverManager;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Controller
@Component
@Slf4j
public class ComUtil {
	@Value("${spring.datasource.url}") 
	static String jsUrl;	
	@Value("${spring.datasource.url}")
	private void setJsUrl(String val) {
		jsUrl = val;
	}
	
	@Value("${spring.datasource.username}") 
	static String jsUserNm;
	@Value("${spring.datasource.username}")
	private void setJsUserNm(String val) {
		jsUserNm = val;
	}
	
    @Value("${spring.datasource.password}") 
    static String jsUserPw;
    @Value("${spring.datasource.password}")
    private void setJsUserPw(String val) {
    	jsUserPw = val;
    }
    
    public static String getFileName(String orgFileName, HttpServletRequest req) throws UnsupportedEncodingException {
    	
    	String header = req.getHeader("User-Agent");
    	String newFileName = "";

    	if (header.contains("MSIE") ||  header.contains("Trident")) {
    		newFileName = URLEncoder.encode(orgFileName,"UTF-8").replaceAll("\\+", "%20");
		} else if (header.contains("Firefox")) {
			newFileName = new String(orgFileName.getBytes("UTF-8"), "ISO-8859-1");
		} else if (header.contains("Opera")) {
			newFileName = new String(orgFileName.getBytes("UTF-8"), "ISO-8859-1");
		} else if (header.contains("Chrome")) {
			newFileName = new String(orgFileName.getBytes("UTF-8"), "ISO-8859-1");
		} else newFileName = orgFileName;    	
    	
    	return newFileName;
    }
    
    @RequestMapping("/fileDownload")
    public void fileDownload(HttpServletRequest request, HttpServletResponse response, String fileName) {

    	String sUrl = request.getServletContext().getRealPath("/");
    	String path =  sUrl + "/tmpReport/" + fileName;
        File file = new File(path);
     
        FileInputStream fileInputStream = null;
        ServletOutputStream servletOutputStream = null;
     
        try{
            String downName = getFileName(fileName,  request);
            response.setHeader("Content-Disposition","attachment;filename=\"" + downName+"\"");             
            response.setContentType("application/octer-stream");
            response.setHeader("Content-Transfer-Encoding", "binary;");
     
            fileInputStream = new FileInputStream(file);
            servletOutputStream = response.getOutputStream();
     
            byte b [] = new byte[1024];
            int data = 0;
     
            while((data=(fileInputStream.read(b, 0, b.length))) != -1){
                servletOutputStream.write(b, 0, data);
            }
     
            servletOutputStream.flush();//출력
             
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(servletOutputStream!=null){
                try{
                    servletOutputStream.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            if(fileInputStream!=null){
                try{
                    fileInputStream.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
    

    /**
     * 레포트 PDF변환
     * @param fileName
     * @param reportName
     * @param param
     * @param request
     * @return
     */
    public static boolean makeReportPdf(String fileName, String reportName, Map<String, Object> param, HttpServletRequest request) {
		
		try {			
			JasperReport jr = JasperCompileManager.compileReport(request.getServletContext().getRealPath("/report/" + reportName +".jrxml"));
			JasperPrint jp = JasperFillManager.fillReport(jr,  param, DriverManager.getConnection(jsUrl, jsUserNm, jsUserPw));
			
			String sUrl = request.getServletContext().getRealPath("/");
			makeDir(sUrl + "/tmpReport/");
			OutputStream output = null;
			output = new FileOutputStream(new File(sUrl + "/tmpReport/" + fileName));
			
			JasperExportManager.exportReportToPdfStream(jp, output);
			output.flush();
			output.close();
						
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
    
    /**
     * 레포트관련 디렉토리 생성
     * @param path
     */
    public static void makeDir(String path) {

    	//String path = "\\tmpReport\\";
    	File Folder = new File(path);

    	if (!Folder.exists()) Folder.mkdir();
    }
}

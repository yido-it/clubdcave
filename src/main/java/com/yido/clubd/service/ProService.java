package com.yido.clubd.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jcodec.api.JCodecException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.yido.clubd.common.utils.AWSFileUtil;
import com.yido.clubd.model.ProVO;
import com.yido.clubd.repository.ProMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 레슨프로특이사항
 * 
 * @author YOO
 *
 */
@Slf4j
@Service
public class ProService {

	@Autowired
    private ProMapper proMapper;
	
	/**
	 * 프로 특이사항 조회
	 * 
	 * @param map
	 * @return
	 */
	public List<ProVO> selectProNoticeList(Map<String, Object> map) {
		return proMapper.selectProNoticeList(map);
	}
	
	/**
	 * 프로 특이사항 저장
	 * 
	 * @param proNotice
	 * @return               
	 */
	public void saveProNotice(Map<String, Object> params) {
		
		List<ProVO> list = new ArrayList<ProVO>();
		for(Entry<String, Object> entry : params.entrySet()) {
			if(-1 < entry.getKey().indexOf("n_")) {
				ProVO proVO = new ProVO();
				proVO.setMsNum((String)params.get("msNum"));
				proVO.setNoticeDiv(entry.getKey().replace("n_", ""));
				proVO.setProRemark(((String) entry.getValue()).replace("\r\n", "<br/>"));
				
				list.add(proVO);
			}
		}
			
		for(ProVO item : list) {			
			if(proMapper.selectProNotice(item) == null) {
				proMapper.insertProNotice(item);			
			} else {
				proMapper.updateProNotice(item);			
			}
		}		

	}	

	/**
	 * 프로 자격사항 조회
	 * 
	 * @param map
	 * @return
	 */
	public List<ProVO> selectProLicenseList(Map<String, Object> map) {
		return proMapper.selectProLicenseList(map);
	}
	
	/**
	 * 프로 자격사항 저장
	 * 
	 * @param proLicense
	 * @return
	 */
	public void saveProLicense(Map<String, Object> params) {		
		
		List<ProVO> list = new ArrayList<ProVO>();
		for(Entry<String, Object> entry : params.entrySet()) {
			if(-1 < entry.getKey().indexOf("l_")) {
				if("Y".equals((String) entry.getValue())) {					
					ProVO proVO = new ProVO();
					proVO.setMsNum((String)params.get("msNum"));
					proVO.setLicKind(entry.getKey().replace("l_", ""));
					list.add(proVO);
				}
			}
		}
		proMapper.deleteAllProLicense(params);
		
		for(ProVO item : list) {			
			proMapper.insertProLicense(item);
		}		
		
	}
	
	/**
	 * 프로 갤러리 이미지 조회
	 * 
	 * @param map
	 * @return
	 */
	public List<ProVO> selectProImageList(Map<String, Object> map) {
		return proMapper.selectProImageList(map);
	}
	
	public void uploadGalleryImg(Map<String, Object> params, MultipartHttpServletRequest mreq) throws Exception {
		Iterator<String> iter = mreq.getFileNames();	
		while(iter.hasNext()) {
			
			// 다음 file[n] 값을 Multipartfile 객체로 생성
			MultipartFile mFile = mreq.getFile(iter.next());
			
			// mFile의 파일이름 가져옴
			String orgFileNm = mFile.getOriginalFilename();
			String extNm = orgFileNm.substring(orgFileNm.lastIndexOf(".") + 1, orgFileNm.length()).toLowerCase();
			
		    String folderNm = (String)params.get("imgPath");		// ex) test/picture/00000001/
			String newFileNm = System.currentTimeMillis() + "." + extNm;
			
			File sameFile = new File(orgFileNm);					// 똑같은 이름의 파일 객체 생성 (file_name.jpg)
			String filePath = sameFile.getAbsolutePath();			// 실행 중인 working directory + File에 전달한 경로값 (C:\folder_name\file_name.jpg)
			File tmpFile = new File(filePath);						// 절대경로로 다시 파일 객체 생성
			mFile.transferTo(tmpFile);								// 임시파일 객체에 mFile을 복사하면 해당 경로에 파일이 만들어짐
			
			Path srcPath = Paths.get(filePath);						// String을 Path 객체로 만들어줌
		    String mimeType = Files.probeContentType(srcPath);		// 파일 경로에 있는 Content-Type(파일 유형) 확인
		    mimeType = (mimeType == null ? "" : mimeType);			// 확장자가 없는 경우 null을 반환
			
		    AWSFileUtil.uploadFile(folderNm, newFileNm, tmpFile, mimeType);	// 생성할 폴더명, 새 파일 이름, 복사될 파일, 파일타입
			// 업로드 후 임시파일 삭제
			if(tmpFile.exists()) tmpFile.delete();
			
			params.put("imgDiv", 1);
			params.put("imgFilename", newFileNm);
			proMapper.insertProImage(params);			
		}
		
	}

	public void uploadGalleryVideo(Map<String, Object> params, MultipartHttpServletRequest mreq) throws Exception {
		Iterator<String> iter = mreq.getFileNames();	
		while(iter.hasNext()) {
			
			// 다음 file[n] 값을 Multipartfile 객체로 생성
			MultipartFile mFile = mreq.getFile(iter.next());
			
			// mFile의 파일이름 가져옴
			String orgFileNm = mFile.getOriginalFilename();
			String extNm = orgFileNm.substring(orgFileNm.lastIndexOf(".") + 1, orgFileNm.length()).toLowerCase();
			
		    String folderNm = (String)params.get("imgPath");		// ex) test/video/00000001
			String newFileNm = (String)params.get("msNum") + "_" + System.currentTimeMillis() + "." + extNm;	
			
			File sameFile = new File(orgFileNm);					// 똑같은 이름의 파일 객체 생성 (file_name.jpg)
			String filePath = sameFile.getAbsolutePath();			// 실행 중인 working directory + File에 전달한 경로값 (C:\folder_name\file_name.jpg)
			File tmpFile = new File(filePath);						// 절대경로로 다시 파일 객체 생성
			mFile.transferTo(tmpFile);								// 임시파일 객체에 mFile을 복사하면 해당 경로에 파일이 만들어짐
			
			Path srcPath = Paths.get(filePath);						// String을 Path 객체로 만들어줌
		    String mimeType = Files.probeContentType(srcPath);		// 파일 경로에 있는 Content-Type(파일 유형) 확인
		    mimeType = (mimeType == null ? "" : mimeType);			// 확장자가 없는 경우 null을 반환
			
									
		    AWSFileUtil.uploadFile(folderNm, newFileNm, tmpFile, mimeType);	// 생성할 폴더명, 새 파일 이름, 복사될 파일, 파일타입
		    // 업로드 후 임시파일 삭제
		 	if(tmpFile.exists()) tmpFile.delete();
			
			params.put("imgDiv", 2);
			
			// VOD Station에서 인코딩 후 생성되는 파일명으로 삽입
			newFileNm = newFileNm.substring(0, newFileNm.lastIndexOf(".")).concat("_default.mp4");
			params.put("imgFilename", newFileNm);
			proMapper.insertProImage(params);
		}
		
	}

	public void deleteGalleryImg(Map<String, Object> params) {
		String objectName = (String)params.get("imgPath") + (String)params.get("imgFilename");
		AWSFileUtil.deleteFile(objectName);
		AWSFileUtil.deleteThumbnail(objectName);		
		
		proMapper.deleteProImage(params);
	}
	
	public void deleteGalleryVideo(Map<String, Object> params) {
		String objectName = (String)params.get("imgPath") + (String)params.get("imgFilename");
		
		AWSFileUtil.deleteFile(objectName);
		AWSFileUtil.deleteVideoThumbnail(objectName);		
		
		proMapper.deleteProImage(params);
	}

}

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.yido.clubd.common.utils.AWSFileUtil;
import com.yido.clubd.common.utils.FFmpegUtil;
import com.yido.clubd.model.ProVO;
import com.yido.clubd.repository.ProMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 프로
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
		for (Entry<String, Object> entry : params.entrySet()) {
			if (-1 < entry.getKey().indexOf("n_")) {
				
				// notice 값이 있는 경우
				if(!((String) entry.getValue()).trim().isEmpty()) {
					String noticeDiv = entry.getKey().replace("n_", "");
					String openYn = (String)params.getOrDefault(noticeDiv + "_yn", "Y"); 					
			
					ProVO proVO = new ProVO();
					proVO.setMsNum((String) params.get("msNum"));
					proVO.setNoticeDiv(noticeDiv);
					proVO.setNoticeOpenYn(openYn);
					proVO.setProRemark(((String) entry.getValue()).replace("\r\n", "<br/>"));
					
					list.add(proVO);
				}
				
			}
		}

		for (ProVO item : list) {
			if (proMapper.selectProNotice(item) == null) {
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
	 * @return List<ProVO>
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
		for (Entry<String, Object> entry : params.entrySet()) {
			if (-1 < entry.getKey().indexOf("l_")) {
				if ("Y".equals((String) entry.getValue())) {
					ProVO proVO = new ProVO();
					proVO.setMsNum((String) params.get("msNum"));
					proVO.setLicKind(entry.getKey().replace("l_", ""));
					list.add(proVO);
				}
			}
		}
		proMapper.deleteAllProLicense(params);

		for (ProVO item : list) {
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

	/**
	 * 프로 갤러리 이미지 업로드
	 * 
	 * @param map
	 * @param mreq
	 * @return
	 */
	public void uploadGalleryImg(Map<String, Object> params, MultipartHttpServletRequest mreq) throws Exception {
		Iterator<String> iter = mreq.getFileNames();
		while (iter.hasNext()) {

			// 다음 file[n] 값을 Multipartfile 객체로 생성
			MultipartFile mFile = mreq.getFile(iter.next());

			// mFile의 파일이름 가져옴
			String orgFileNm = mFile.getOriginalFilename();
			String extNm = orgFileNm.substring(orgFileNm.lastIndexOf(".") + 1, orgFileNm.length()).toLowerCase();
			
			String folderNm = "picture/" + params.get("msNum") + "/"; // ex) test/picture/00000001/
			String newFileNm = System.currentTimeMillis() + "." + extNm;

			File sameFile = new File(orgFileNm); // 똑같은 이름의 파일 객체 생성 (file_name.jpg)
			String filePath = sameFile.getAbsolutePath(); // 실행 중인 working directory + File에 전달한 경로값
															// (C:\folder_name\file_name.jpg)
			File tmpFile = new File(filePath); // 절대경로로 다시 파일 객체 생성
			mFile.transferTo(tmpFile); // 임시파일 객체에 mFile을 복사하면 해당 경로에 파일이 만들어짐

			Path srcPath = Paths.get(filePath); // String을 Path 객체로 만들어줌
			String mimeType = Files.probeContentType(srcPath); // 파일 경로에 있는 Content-Type(파일 유형) 확인
			mimeType = (mimeType == null ? "" : mimeType); // 확장자가 없는 경우 null을 반환

			AWSFileUtil.uploadFile(folderNm, newFileNm, extNm, tmpFile, mimeType); // 생성할 폴더명, 새 파일 이름, 복사될 파일, 파일타입
			// 업로드 후 임시파일 삭제
			if (tmpFile.exists())	tmpFile.delete();

			params.put("imgDiv", 1);
			params.put("imgFilename", newFileNm);
			params.put("imgPath", folderNm);
			
			proMapper.insertProImage(params);
		}

	}
	
	/**
	 * 프로 갤러리 영상 업로드
	 * 
	 * @param params
	 * @param mreq
	 * @return Map
	 */
	public Map<String, Object> uploadGalleryVideo(Map<String, Object> params, MultipartHttpServletRequest mreq) throws Exception {

		Iterator<String> iter = mreq.getFileNames();
		while (iter.hasNext()) {

			// 다음 file[n] 값을 Multipartfile 객체로 생성
			MultipartFile mFile = mreq.getFile(iter.next());

			// mFile의 파일이름 가져옴
			String orgFileNm = mFile.getOriginalFilename();
			String extNm = orgFileNm.substring(orgFileNm.lastIndexOf(".") + 1, orgFileNm.length()).toLowerCase();

			String folderNm = "video/main/clubd_cheongdam/";	// 비디오 업로드 전용 버킷 폴더 경로
			String newFileNm = (String) params.get("msNum") + "_" + System.currentTimeMillis() + "." + extNm;

			File sameFile = new File(orgFileNm);			// 똑같은 이름의 파일 객체 생성 (file_name.jpg)
			String filePath = sameFile.getAbsolutePath();	// 실행 중인 working directory + File에 전달한 경로값
			File tmpFile = new File(filePath);				// 절대경로로 다시 파일 객체 생성
			mFile.transferTo(tmpFile);

			Path srcPath = Paths.get(filePath);					// String을 Path 객체로 만들어줌
			String mimeType = Files.probeContentType(srcPath);	// 파일 경로에 있는 Content-Type(파일 유형) 확인
			mimeType = (mimeType == null ? "" : mimeType);		// 확장자가 없는 경우 null을 반환

			// 1) 동영상 길이 제한 
			double duration = FFmpegUtil.getVideoDuration(filePath);
			if(duration  <= 0) {
				params.put("result", false);
				params.put("message", "잘못된 파일입니다.");
				
				return params;
			}			
			if(duration > 10) {
				params.put("result", false);
				params.put("message", "업로드 하려는 동영상이 10초를 넘습니다.");
				
				return params;
			}	
			
			// 2) 업로드
			AWSFileUtil.uploadFile(folderNm, newFileNm, extNm, tmpFile, mimeType);	// 생성할 폴더명, 새 파일 이름, 복사될 파일, 파일타입
			if (tmpFile.exists()) tmpFile.delete();									// 업로드 후 임시파일 삭제
			
			// 3) DB 데이터 삽입		
			newFileNm = newFileNm.substring(0, newFileNm.lastIndexOf(".")).concat("_default.mp4"); // 인코딩 후 파일명
			params.put("imgFilename", newFileNm);
			params.put("imgDiv", 2);
			params.put("imgPath", folderNm); 
			proMapper.insertProImage(params);	
			
		}
		
		params.put("result", true);
		return params;

	}

	/**
	 * 프로 갤러리 이미지 삭제
	 * 
	 * @param params
	 * @return
	 */
	public void deleteGalleryImg(Map<String, Object> params) {
		String objectName = (String) params.get("imgPath") + (String) params.get("imgFilename");
		AWSFileUtil.deleteFile(objectName);
		AWSFileUtil.deleteThumbnail(objectName);

		proMapper.deleteProImage(params);
	}

	/**
	 * 프로 갤러리 영상 삭제
	 * 
	 * @param params
	 * @return
	 */
	public void deleteGalleryVideo(Map<String, Object> params) {
		String objectName = (String) params.get("imgPath") + (String) params.get("imgFilename");

		AWSFileUtil.deleteFile(objectName);
		AWSFileUtil.deleteVideoThumbnail(objectName);

		proMapper.deleteProImage(params);
	}
	
	/**
	 * 프로 갤러리 이미지/영상 저장
	 * 
	 * @param params
	 * @return
	 */
	public void insertProImage(Map<String, Object> params) {
		proMapper.insertProImage(params);
	}

}

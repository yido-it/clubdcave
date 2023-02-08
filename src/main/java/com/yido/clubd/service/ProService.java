package com.yido.clubd.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.yido.clubd.component.FileUtil;
import com.yido.clubd.model.ProVO;
import com.yido.clubd.repository.ProMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 레슨프로특이사항
 * 
 * @author bae
 *
 */
@Slf4j
@Service
public class ProService {

	@Autowired
    private ProMapper proMapper;

	/**
	 * 레슨프로특이사항 등록
	 * 
	 * @param proNotice
	 * @return
	 */
	public int insertProNotice(ProVO proVO) {
		return proMapper.insertProNotice(proVO);
	}
	
	/**
	 * 레슨프로특이사항 수정
	 * 
	 * @param proNotice
	 * @return
	 */
	public int updateProNotice(ProVO proVO) {
		return proMapper.updateProNotice(proVO);
	}
	
	/**
	 * 레슨프로특이사항 조회
	 * 
	 * @param map
	 * @return
	 */
	public List<ProVO> selectProNoticeList(Map<String, Object> map) {
		return proMapper.selectProNoticeList(map);
	}
	
	/**
	 * 프로 이미지 조회
	 * 
	 * @param map
	 * @return
	 */
	public List<ProVO> selectProImageList(Map<String, Object> map) {
		return proMapper.selectProImageList(map);
	}
	
	public List<ProVO> selectProLicenseList(Map<String, Object> map) {
		return proMapper.selectProLicenseList(map);
	}
	
	/**
	 * 레슨프로자격사항 등록
	 * 
	 * @param proLicense
	 * @return
	 */
	public int insertProLicense(ProVO proVO) {
		return proMapper.insertProLicense(proVO);
	}
	
	/**
	 * 레슨프로자격사항 수정
	 * 
	 * @param proLicense
	 * @return
	 */
	public int updateProLicense(ProVO proVO) {
		return proMapper.updateProLicense(proVO);
	}

}

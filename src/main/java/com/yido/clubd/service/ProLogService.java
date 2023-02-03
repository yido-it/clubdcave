package com.yido.clubd.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yido.clubd.model.ProLogVO;
import com.yido.clubd.repository.ProLogMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 레슨프로특이사항
 * 
 * @author bae
 *
 */
@Slf4j
@Service
public class ProLogService {

	@Autowired
    private ProLogMapper proLogMapper;

	/**
	 * 레슨프로특이사항 등록
	 * 
	 * @param proNotice
	 * @return
	 */
	public int insertProNotice(ProLogVO proLogVO) {
		return proLogMapper.insertProNoticeLog(proLogVO);
	}
	/**
	 * 레슨프로자격사항 등록
	 * 
	 * @param proLicense
	 * @return
	 */
	public int insertProLicense(ProLogVO proLogVO) {
		return proLogMapper.insertProLicenseLog(proLogVO);
	}
	
}

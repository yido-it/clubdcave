package com.yido.clubd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yido.clubd.model.ProLicenseLog;
import com.yido.clubd.model.ProNoticeLog;
import com.yido.clubd.repository.ProLicenseLogMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * 레슨프로자격사항 로그
 * 
 * @author bae
 *
 */
@Slf4j
@Service
public class ProLicenseLogService {

	@Autowired
    private ProLicenseLogMapper proLicenseLogMapper;

	/**
	 * 로그 등록
	 * 
	 * @param proLicenseLog
	 * @return
	 */
	public int insertProLicenseLog(ProLicenseLog proLicenseLog) {
		return proLicenseLogMapper.insertProLicenseLog(proLicenseLog);
	}
}

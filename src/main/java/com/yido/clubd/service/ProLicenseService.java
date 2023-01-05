package com.yido.clubd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yido.clubd.model.ProLicense;
import com.yido.clubd.repository.ProLicenseMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * 레슨프로자격사항
 * 
 * @author bae
 *
 */
@Slf4j
@Service
public class ProLicenseService {

	@Autowired
    private ProLicenseMapper proLicenseMapper;

	/**
	 * 레슨프로자격사항 등록
	 * 
	 * @param proLicense
	 * @return
	 */
	public int insertProLicense(ProLicense proLicense) {
		return proLicenseMapper.insertProLicense(proLicense);
	}
	
	/**
	 * 레슨프로자격사항 수정
	 * 
	 * @param proLicense
	 * @return
	 */
	public int updateProLicense(ProLicense proLicense) {
		return proLicenseMapper.updateProLicense(proLicense);
	}
}

package com.yido.clubd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yido.clubd.model.DrVoucherUse;
import com.yido.clubd.repository.DrVoucherUseMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * 이용권사용내역
 * 
 * @author bae
 *
 */
@Slf4j
@Service
public class DrVoucherUseService {

	@Autowired
    private DrVoucherUseMapper drVoucherUseMapper;
	
	/**
	 * 이용권사용내역 등록
	 * 
	 * @param drVoucherUse
	 * @return
	 */
	public int insertDrVoucherUse(DrVoucherUse drVoucherUse) {
		return drVoucherUseMapper.insertDrVoucherUse(drVoucherUse);
	}
}

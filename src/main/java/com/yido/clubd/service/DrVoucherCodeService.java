package com.yido.clubd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yido.clubd.model.DrVoucherCode;
import com.yido.clubd.repository.DrVoucherCodeMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * 이용권코드
 * 
 * @author bae
 *
 */
@Slf4j
@Service
public class DrVoucherCodeService {

	@Autowired
    private DrVoucherCodeMapper drVoucherCodeMapper;
	
	/**
	 * 이용권 조회
	 * 
	 * @param drVoucherCode
	 * @return
	 */
    public List<DrVoucherCode> selectList(DrVoucherCode drVoucherCode) {
    	return drVoucherCodeMapper.selectList(drVoucherCode);
    }
}

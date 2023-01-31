package com.yido.clubd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yido.clubd.model.DrVoucherSale;
import com.yido.clubd.repository.DrVoucherSaleLogMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * 회원이용권 구매내역 로그
 * 
 * @author bae
 *
 */
@Slf4j
@Service
public class DrVoucherSaleLogService {

	@Autowired
    private DrVoucherSaleLogMapper drVoucherSaleLogMapper;

	/**
	 * 로그 등록
	 * @param drVoucherSaleLog
	 * @return
	 */
	public int insertDrVoucherSaleLog(DrVoucherSale drVoucherSale) {
		return drVoucherSaleLogMapper.insertDrVoucherSaleLog(drVoucherSale);
	}
}

package com.yido.clubd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yido.clubd.model.DrVoucherSale;
import com.yido.clubd.repository.DrVoucherSaleMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * 회원이용권 구매내역
 * 
 * @author bae
 *
 */
@Slf4j
@Service
public class DrVoucherSaleService {

	@Autowired
    private DrVoucherSaleMapper drVoucherSaleMapper;
	
	/**
	 * 회원이용권 구매내역 등록
	 * 
	 * @param drVoucherSale
	 * @return
	 */
	public int insertDrVoucherSale(DrVoucherSale drVoucherSale) {
		return drVoucherSaleMapper.insertDrVoucherSale(drVoucherSale);
	}
	
	/**
	 * 회원이용권 구매내역 수정
	 * 
	 * @param drVoucherSale
	 * @return
	 */
	public int updateDrVoucherSale(DrVoucherSale drVoucherSale) {
		return drVoucherSaleMapper.updateDrVoucherSale(drVoucherSale);
	}
}

package com.yido.clubd.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yido.clubd.common.utils.ResultVO;
import com.yido.clubd.model.BookInfoVO;
import com.yido.clubd.model.DrBkMnMap;
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
	 * 이용권 구매내역 
	 * 
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> selectList(Map<String, Object> map) {
		return drVoucherSaleMapper.selectList(map);
	}
	
	public DrVoucherSale getVoucherSale(DrVoucherSale drVoucherSale) {
		return drVoucherSaleMapper.getVoucherSale(drVoucherSale);
	}
	
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
	
	/**
	 * 회원이용권 잔여수량 수정
	 * 
	 * @param drVoucherSale
	 * @return
	 */
	public int updateVcRemCnt(DrVoucherSale drVoucherSale) {
		return drVoucherSaleMapper.updateVcRemCnt(drVoucherSale);
	}

}

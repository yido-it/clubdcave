package com.yido.clubd.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.yido.clubd.common.utils.ResultVO;
import com.yido.clubd.model.BookInfoVO;
import com.yido.clubd.model.DrBkHistory;
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
	 * 고유번호 채번
	 * 
	 * @param params
	 * @return
	 */
	public String getSerialNo() {
		return drVoucherSaleMapper.getSerialNo();
	}
	
	public int getSaleSeq() {
		return drVoucherSaleMapper.getSaleSeq();
	}
	
	/**
	 * 이용권 구매내역 조회
	 * 
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> selectSaleList(Map<String, Object> map) {
		return drVoucherSaleMapper.selectSaleList(map);
	}
	
	/**
	 * 최근 이용권 구매내역 
	 * 
	 * @param map
	 * @return
	 */
	public Map<String, Object> getLastSale(Map<String, Object> map) {
		return drVoucherSaleMapper.getLastSale(map);
	}
		
	/**
	 * 이용권 구매내역 조회 (예약페이지에서 필요한 정보)
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
	 * 이용권 구매내역 등록
	 * 
	 * @param drVoucherSale
	 * @return
	 */
	public int insertDrVoucherSale(DrVoucherSale drVoucherSale) {
		return drVoucherSaleMapper.insertDrVoucherSale(drVoucherSale);
	}
	
	/**
	 * 이용권 잔여수량 변경
	 * 
	 * @param drVoucherSale
	 * @return
	 */
	public int updateVcRemCnt(DrVoucherSale drVoucherSale) {
		return drVoucherSaleMapper.updateVcRemCnt(drVoucherSale);
	}
	
	/**
	 * 이용권 상태 변경
	 * 
	 * @param drVoucherSale
	 * @return
	 */
	public int updateState(DrVoucherSale drVoucherSale) {
		return drVoucherSaleMapper.updateState(drVoucherSale);
	}

}

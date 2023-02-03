package com.yido.clubd.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yido.clubd.model.DrBkHistory;
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
	
	public List<DrVoucherUse> selectList(Map<String, Object> param) {
		return drVoucherUseMapper.selectList(param);
	}

	// 이용내역 (예약정보 포함)
	public List<Map<String, Object>> selectUseList(Map<String, Object> param) {
		return drVoucherUseMapper.selectUseList(param);
	}
	
	/**
	 * 사용내역 등록
	 * 
	 * @param drVoucherUse
	 * @return
	 */
	public int insertDrVoucherUse(DrVoucherUse drVoucherUse) {
		return drVoucherUseMapper.insertDrVoucherUse(drVoucherUse);
	}
	
	/**
	 * 사용내역 삭제
	 * 
	 * @param drVoucherUse
	 * @return
	 */
	public int deleteDrVoucherUse(DrVoucherUse drVoucherUse) {
		return drVoucherUseMapper.deleteDrVoucherUse(drVoucherUse);
	}
}

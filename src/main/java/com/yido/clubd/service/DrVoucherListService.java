package com.yido.clubd.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yido.clubd.model.DrVoucherList;
import com.yido.clubd.repository.DrVoucherListMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * 이용권세부내역
 * 
 * @author bae
 *
 */
@Slf4j
@Service
public class DrVoucherListService {

	@Autowired
    private DrVoucherListMapper drVoucherListMapper;

	public int getListSeq(Map<String, Object> map) {
    	return drVoucherListMapper.getListSeq(map);
	}
	
    public List<DrVoucherList> selectList(Map<String, Object> map) {
    	return drVoucherListMapper.selectList(map);
    }
    
	/**
	 * 이용권세부내역 등록 
	 * 
	 * @param drVoucherList
	 * @return
	 */
	public int insertDrVoucherList(DrVoucherList drVoucherList) {
		return drVoucherListMapper.insertDrVoucherList(drVoucherList);
	}
	
	/**
	 * 상태변경
	 * 
	 * @param drVoucherList
	 * @return
	 */
	public int updateState(DrVoucherList drVoucherList) {
		return drVoucherListMapper.updateState(drVoucherList);
	}
	
	/**
	 * 매출순번에 대한 모든 list 상태 변경 
	 * 
	 * @param drVoucherList
	 * @return
	 */
	public int updateStateBySaleSeq(DrVoucherList drVoucherList) {
		return drVoucherListMapper.updateStateBySaleSeq(drVoucherList);
	}
}

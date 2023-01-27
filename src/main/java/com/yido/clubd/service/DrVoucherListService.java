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
	
	public int updateDrVoucherList(DrVoucherList drVoucherList) {
		return drVoucherListMapper.updateDrVoucherList(drVoucherList);
	}
}

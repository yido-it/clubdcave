package com.yido.clubd.repository;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.yido.clubd.model.DrBkHistory;
import com.yido.clubd.model.DrVoucherUse;

/**
 * 이용권사용내역
 * 
 * @author bae
 *
 */
@Mapper
@Repository
public interface DrVoucherUseMapper {

	public List<DrVoucherUse> selectList(Map<String, Object> param);
	
	// 이용내역 (예약정보 포함)
	public List<Map<String, Object>> selectUseList(Map<String, Object> param);
	
	// 사용내역 등록
	public int insertDrVoucherUse(DrVoucherUse drVoucherUse);
	
	// 사용내역 삭제
	public int deleteDrVoucherUse(DrVoucherUse drVoucherUse);

}

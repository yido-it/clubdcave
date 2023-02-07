package com.yido.clubd.repository;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.yido.clubd.model.DrVoucherList;

/**
 * 이용권세부내역
 * 
 * @author bae
 *
 */
@Mapper
@Repository
public interface DrVoucherListMapper {
	
	public int getListSeq(Map<String, Object> map);
	
	public List<DrVoucherList> selectList(Map<String, Object> map);

	// 이용권세부내역 등록
	public int insertDrVoucherList(DrVoucherList drVoucherList);
	
	public int updateDrVoucherList(DrVoucherList drVoucherList);

}

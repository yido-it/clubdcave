package com.yido.clubd.repository;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.yido.clubd.model.DrBkMark;

/**
 * 예약선점용
 * 
 * @author bae
 *
 */
@Mapper
@Repository
public interface DrBkMarkMapper {

	public List<DrBkMark> selectList(Map<String, Object> map);
	
	// [예약 선점용] 테이블에서 '예약 가능한 데이터만 조회'
	public List<DrBkMark> selectAvailableData(Map<String, Object> map);
	
	// [예약선점용] 테이블 데이터 변경
	public int updateDrBkMark(Map<String, Object> map);

}

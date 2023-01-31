package com.yido.clubd.repository;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.yido.clubd.model.BookInfoVO;
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

	public List<DrBkMark> selectList(BookInfoVO bkInfo);
	
	// [예약 선점용] 테이블에서 '예약 가능한 데이터만 조회'
	public List<DrBkMark> selectAvailableData(BookInfoVO bkInfo);
	
	// [예약선점용] 테이블 데이터 변경 (예약완료되었을때)
	public int updateDrBkMark(Map<String, Object> map);
	
	// [예약선점용] 선점
	public int updateMark(Map<String, Object> map);
	
	// [예약선점용] 해제
	public int updateUnMark(Map<String, Object> map);

}

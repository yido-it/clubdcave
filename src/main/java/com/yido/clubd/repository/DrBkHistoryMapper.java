package com.yido.clubd.repository;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.yido.clubd.model.DrBkHistory;

/**
 * 예약내역
 * 
 * @author bae
 *
 */
@Mapper
@Repository
public interface DrBkHistoryMapper {
	
	// 예약고유번호 채번
	public String getSerialNo(Map<String, Object> params);

	public List<DrBkHistory> selectList(Map<String, Object> params);
	
	// 고객 예약내역 확인 
	public List<Map<String, Object>> selectBkHis(Map<String, Object> params);
	
	// 가장 최근 대표예약고유번호 조회 (조건 : 회원번호)
	public String selectCalcSNo(Map<String, Object> param);
	
	// 예약내역 등록
	public int insertDrBkHistory(Map<String, Object> params);
	
	// 상태 변경
	public int updateBkState(DrBkHistory drBkHistory);
	
	// 금액 변경
	public int updateBkAmount(DrBkHistory drBkHistory);
	
}

package com.yido.clubd.repository;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.yido.clubd.model.DrBkHistory;
import com.yido.clubd.model.ProLicense;

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
	
	// 예약내역 등록
	public int insertDrBkHistory(Map<String, Object> params);
	
}

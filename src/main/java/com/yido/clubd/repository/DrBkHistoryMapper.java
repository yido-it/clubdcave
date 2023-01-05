package com.yido.clubd.repository;


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
	
	// 예약내역 등록
	public int insertDrBkHistory(DrBkHistory drBkHistory);
}

package com.yido.clubd.repository;

import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.yido.clubd.model.DrBkHistory;


/**
 * 예약내역 로그
 * 
 * @author bae
 *
 */
@Mapper
@Repository
public interface DrBkHistoryLogMapper {

	// 로그 등록
	public int insertDrBkHistoryLog(DrBkHistory drBkHistory);

	// 금액 변경
	public int updateBkAmount(DrBkHistory drBkHistory);
}

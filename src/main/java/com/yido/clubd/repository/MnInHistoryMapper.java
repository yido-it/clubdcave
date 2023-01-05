package com.yido.clubd.repository;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.yido.clubd.model.MnInHistory;

/**
 * 입금내역
 * 
 * @author bae
 *
 */
@Mapper
@Repository
public interface MnInHistoryMapper {

	// 입금내역 등록
	public int insertMnInHistory(MnInHistory mnInHistory);

}

package com.yido.clubd.repository;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.yido.clubd.model.SlMaster;

/**
 * 매출내역
 * 
 * @author bae
 *
 */
@Mapper
@Repository
public interface SlMasterMapper {

	// 매출내역 등록
	public int insertSlMaster(SlMaster slMaster);
	
}

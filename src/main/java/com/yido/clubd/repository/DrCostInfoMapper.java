package com.yido.clubd.repository;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.yido.clubd.model.DrCostInfo;

/**
 * 요금정보
 * 
 * @author bae
 *
 */
@Mapper
@Repository
public interface DrCostInfoMapper {

	// 요금조회
	public Map<String, Object> getCostInfo(Map<String, Object> map);

}

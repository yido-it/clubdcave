package com.yido.clubd.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.yido.clubd.model.DrCostInfoLog;

/**
 * 요금정보 로그
 * 
 * @author bae
 *
 */
@Mapper
@Repository
public interface DrCostInfoLogMapper {

	public DrCostInfoLog selectList(DrCostInfoLog drCostInfoLog);

}

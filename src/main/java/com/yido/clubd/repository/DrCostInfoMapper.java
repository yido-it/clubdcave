package com.yido.clubd.repository;

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

	public DrCostInfo selectList(DrCostInfo drCostInfo);

}

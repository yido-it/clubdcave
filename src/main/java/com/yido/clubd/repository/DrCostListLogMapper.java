package com.yido.clubd.repository;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.yido.clubd.model.DrCostListLog;

/**
 * 요금세부내역 로그
 * 
 * @author bae
 *
 */
@Mapper
@Repository
public interface DrCostListLogMapper {

	public DrCostListLog selectList(DrCostListLog drCostListLog);

}

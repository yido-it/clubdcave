package com.yido.clubd.repository;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.yido.clubd.model.DrCostList;

/**
 * 요금세부내역
 * 
 * @author bae
 *
 */
@Mapper
@Repository
public interface DrCostListMapper {

	public DrCostList selectList(DrCostList drCostList);

}

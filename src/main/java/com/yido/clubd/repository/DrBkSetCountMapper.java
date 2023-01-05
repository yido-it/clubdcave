package com.yido.clubd.repository;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.yido.clubd.model.DrBkSetCount;

/**
 * 예약오픈수량
 * 
 * @author bae
 *
 */
@Mapper
@Repository
public interface DrBkSetCountMapper {

	public DrBkSetCount selectList(DrBkSetCount drBkSetCount);

}

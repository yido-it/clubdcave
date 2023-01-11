package com.yido.clubd.repository;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.yido.clubd.model.ClDayInfo;

/**
 * 월력
 * 
 * @author bae
 *
 */
@Mapper
@Repository
public interface ClDayInfoMapper {

	public ClDayInfo selectList(ClDayInfo clDayInfo);

}
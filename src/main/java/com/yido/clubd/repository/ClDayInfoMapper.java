package com.yido.clubd.repository;


import java.util.List;
import java.util.Map;

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

	public List<Map<String, Object>> selectList(Map<String, Object> map);

}

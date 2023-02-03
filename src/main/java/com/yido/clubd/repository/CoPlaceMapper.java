package com.yido.clubd.repository;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.yido.clubd.model.CoPlace;

/**
 * 지점코드
 * 
 * @author bae
 *
 */
@Mapper
@Repository
public interface CoPlaceMapper {

	public List<CoPlace> selectList(Map<String, Object> map);

	public List<CoPlace> selectPlaceList();

}

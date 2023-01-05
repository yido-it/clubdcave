package com.yido.clubd.repository;


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

	public CoPlace selectList(CoPlace coPlace);

}

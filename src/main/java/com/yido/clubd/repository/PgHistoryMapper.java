package com.yido.clubd.repository;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * PG 결제내역
 * 
 * @author bae
 *
 */
@Mapper
@Repository
public interface PgHistoryMapper {

	 
	public int insertOrderHistory(Map<String, Object> params);

}

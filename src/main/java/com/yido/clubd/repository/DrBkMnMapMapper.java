package com.yido.clubd.repository;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.yido.clubd.model.DrBkMnMap;

/**
 * 예약 입금 연결 정보
 * 
 * @author bae
 *
 */
@Mapper
@Repository
public interface DrBkMnMapMapper {

	public int insertDrBkMnMap(DrBkMnMap drBkMnMap);

}

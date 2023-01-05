package com.yido.clubd.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.yido.clubd.model.DrBkOpenTime;

/**
 * 예약오픈일정 시간별
 * 
 * @author bae
 *
 */
@Mapper
@Repository
public interface DrBkOpenTimeMapper {

	public DrBkOpenTime getBkDay(DrBkOpenTime drBkOpenTime);

}

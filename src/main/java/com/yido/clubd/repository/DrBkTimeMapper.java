package com.yido.clubd.repository;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.yido.clubd.model.DrBkTime;

/**
 * 예약타임
 * 
 * @author bae
 *
 */
@Mapper
@Repository
public interface DrBkTimeMapper {

	public DrBkTime selectList(DrBkTime drBkTime);

}

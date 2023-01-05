package com.yido.clubd.repository;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.yido.clubd.model.DrProSchedule;

/**
 * 레슨프로일정
 * 
 * @author bae
 *
 */
@Mapper
@Repository
public interface DrProScheduleMapper {

	// 레슨프로일정 등록
	public int insertDrProSchedule(DrProSchedule drProSchedule);
	
	// 레슨프로일정 수정
	public int updateDrProSchedule(DrProSchedule drProSchedule);

}

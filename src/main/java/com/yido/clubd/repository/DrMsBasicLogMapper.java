package com.yido.clubd.repository;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.yido.clubd.model.DrMsBasicLog;

/**
 * 회원추가정보 로그
 * 
 * @author bae
 *
 */
@Mapper
@Repository
public interface DrMsBasicLogMapper {

	// 로그 등록
	public int insertDrMsBasicLog(DrMsBasicLog drMsBasicLog);
}

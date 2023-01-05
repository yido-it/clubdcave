package com.yido.clubd.repository;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.yido.clubd.model.ProNoticeLog;

/**
 * 레슨프로특이사항 로그
 * 
 * @author bae
 *
 */
@Mapper
@Repository
public interface ProNoticeLogMapper {

	// 로그 등록
	public int insertProNoticeLog(ProNoticeLog proNoticeLog);
}

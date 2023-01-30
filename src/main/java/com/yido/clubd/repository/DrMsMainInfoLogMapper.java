package com.yido.clubd.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.yido.clubd.model.DrMsMainInfoLog;
import com.yido.clubd.model.MemberVO;

/**
 * 회원정보 (회원/프로) 로그
 * 
 * @author bae
 *
 */
@Mapper
@Repository
public interface DrMsMainInfoLogMapper {

	// 로그 등록
	public int insertDrMsMainInfoLog(DrMsMainInfoLog drMsMainInfoLog);

}

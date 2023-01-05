package com.yido.clubd.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.yido.clubd.common.utils.SessionVO;
import com.yido.clubd.model.DrMsMaininfo;

/**
 * 회원정보 (회원/프로)
 * 
 * @author bae
 *
 */
@Mapper
@Repository
public interface DrMsMaininfoMapper {

	public DrMsMaininfo selectDrMsMaininfo(String msId);
	
	public DrMsMaininfo selectOne(String msId);

	public String getMsNum(Map<String, Object> params);
	
	public SessionVO getMsSession(Map<String, Object> params);

	// 회원/프로 등록
	public int insertDrMsMaininfo(DrMsMaininfo drMsMaininfo);

	// 회원/프로 수정
	public int updateDrMsMaininfo(DrMsMaininfo drMsMaininfo);
	
	// 아이디 중복체크 
	public int duplicateCheckSocialId(Map<String, Object> params);

}

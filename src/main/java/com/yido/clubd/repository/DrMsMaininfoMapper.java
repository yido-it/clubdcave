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

	//public DrMsMaininfo selectDrMsMaininfo(String msId);
	
	// 회원 중복체크 (아이디, 이메일, 핸드폰 번호)
	public DrMsMaininfo selectDuplicateUser(Map<String, Object> params);
	
	public DrMsMaininfo selectLoginUser(Map<String, Object> params);

	public String selectMsNum(Map<String, Object> params);
	
	public SessionVO selectMsSession(Map<String, Object> params);

	// 회원/프로 등록
	public void insertDrMsMaininfo(DrMsMaininfo drMsMaininfo);

	// 회원/프로 수정
	public int updateDrMsMaininfo(DrMsMaininfo drMsMaininfo);
	
	public int duplicateCheckSocialId(Map<String, Object> params);

	// 회원 위약체크 
	public String chkMsBkGrant(Map<String, Object> params);
}

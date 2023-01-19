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
 * @author bae, MSYOO
 *
 */
@Mapper
@Repository
public interface DrMsMaininfoMapper {

	public DrMsMaininfo selectDrMsMaininfo(Map<String, Object> params);
	
	public DrMsMaininfo selectLoginUser(Map<String, Object> params) throws Exception;

	public String selectMsNum(Map<String, Object> params) throws Exception;
	
	public SessionVO selectMsSession(Map<String, Object> params) throws Exception;

	// 회원/프로 등록
	public void insertDrMsMaininfo(DrMsMaininfo drMsMaininfo) throws Exception;

	// 회원/프로 수정
	public int updateDrMsMaininfo(Map<String, Object> params) throws Exception;
	
	public int duplicateCheckSocialId(Map<String, Object> params);
	
	// 아이디/전화번호 중복체크, 아이디 찾기 
	public DrMsMaininfo selectFindUser(Map<String, Object> params) throws Exception;
	
	public DrMsMaininfo selectFindPw(Map<String, Object> params) throws Exception;
	
	public void updatePw(Map<String, Object> params) throws Exception;

	// 회원 위약체크 
	public String chkMsBkGrant(Map<String, Object> params);

	public List<DrMsMaininfo> selectProList();

	public void insertLoginLog(Map<String, Object> params);

	public void updateMsSessionKey(Map<String, Object> params);

	public SessionVO selectSessionLoginUser(Map<String, Object> params);
}

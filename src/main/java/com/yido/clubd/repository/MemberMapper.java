package com.yido.clubd.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.yido.clubd.common.utils.SessionVO;
import com.yido.clubd.model.BookInfoVO;
import com.yido.clubd.model.MemberVO;

/**
 * 회원정보
 * 
 * @author MSYOO
 *
 */
@Mapper
@Repository
public interface MemberMapper {

	public MemberVO selectMember(Map<String, Object> params);
	
	public MemberVO selectLoginUser(Map<String, Object> params) throws Exception;

	public String selectMsNum(Map<String, Object> params) throws Exception;
	
	public SessionVO selectMsSession(Map<String, Object> params) throws Exception;

	// 회원 등록
	public void insertMember(MemberVO member) throws Exception;

	// 회원 수정
	public int updateMember(Map<String, Object> params) throws Exception;
	
	// 회원 추가정보 등록
	public void updateMemberAdd(Map<String, Object> params);
	
	public MemberVO selectMemberBasic(String msNum);
	
	public List<MemberVO> selectMemberCarList(String msNum);
	
	public void updateMemberBasic(Map<String, Object> params);
	
	public void insertMemberBasic(Map<String, Object> params);	
	
	public void deleteMemberCar(String msNum);
	
	public void insertMemberCar(Map<String, Object> carInfo);
	
	// 아이디/전화번호 중복체크, 아이디 찾기 
	public MemberVO selectFindUser(Map<String, Object> params) throws Exception;
	
	public MemberVO selectFindPw(Map<String, Object> params) throws Exception;
	
	public void updatePw(Map<String, Object> params) throws Exception;

	// 회원 위약체크 
	public String chkMsBkGrant(BookInfoVO bookInfo);

	public List<MemberVO> selectProList();

	public void insertLoginLog(Map<String, Object> params);

	public void updateMsSessionKey(Map<String, Object> params);

	public SessionVO selectSessionLoginUser(Map<String, Object> params);



}

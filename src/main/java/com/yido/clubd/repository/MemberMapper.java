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
	
	// MS_ID 또는 MS_NUM으로 회원정보 조회 (정보 수정)
	public MemberVO selectMember(Map<String, Object> params);
	
	// 회원 목록 조회 
	public List<MemberVO> selectMemberList(Map<String, Object> params);
	
	// 아이디/비밀번호로 회원정보 조회 (로그인)
	public MemberVO selectLoginUser(Map<String, Object> params) throws Exception;

	// MS_ID로 MS_NUM 조회
	public String selectMsNum(Map<String, Object> params) throws Exception;
	
	// MS_NUM으로 회원정보 조회 (세션에 적용)
	public SessionVO selectMsSession(Map<String, Object> params) throws Exception;
	
	// 세션키로 조회 (자동 로그인)
	public SessionVO selectSessionLoginUser(Map<String, Object> params);

	// 회원 세션키 등록
	public void updateMsSessionKey(Map<String, Object> params);
	
	// 회원정보 등록
	public void insertMember(MemberVO member) throws Exception;

	// 회원정보 수정
	public int updateMember(Map<String, Object> params) throws Exception;
	
	// 회원 추가정보 조회
	public MemberVO selectMemberBasic(String msNum);
	
	// 회원 추가정보 등록 (DR_MS_BASIC)
	public void insertMemberBasic(Map<String, Object> params);
	
	// 회원 추가정보 수정 (DR_MS_BASIC)
	public void updateMemberBasic(Map<String, Object> params);
	
	// 회원 차량정보 조회
	public List<MemberVO> selectMemberCarList(String msNum);
	
	// 회원 차량정보 등록 
	public void insertMemberCar(Map<String, Object> carInfo);
	
	// 회원 차량정보 삭제
	public void deleteMemberCar(String msNum);
	
	
	// 아이디/전화번호 중복체크, 아이디 유무 확인
	public MemberVO selectFindUser(Map<String, Object> params) throws Exception;
	
	// 비밀번호 초기화
	public void updatePassword(Map<String, Object> params) throws Exception;

	// 회원 위약체크 
	public String chkMsBkGrant(Map<String, Object> params);
	
	// 프로 목록 조회
	public List<MemberVO> selectProList();
	
	// 앰배서더 리스트 조회
	public List<MemberVO> selectAmbassadorList();
	
	// 로그인 접속 로그 등록
	public void insertLoginLog(Map<String, Object> params);

	// 회원/프로 프로필 이미지 등록
	public void insertDrMsPicture(Map<String, Object> params);

	// 회원/프로 프로필 이미지 조회
	public MemberVO selectDrMsPicture(Map<String, Object> map);

	// 회원/프로 프로필 이미지 삭제
	public void deleteDrMsPicture(Map<String, Object> params);

	// 회원 탈퇴
	public void updateMemberQuit(Map<String, Object> params);

	public void updateMemberToken(Map<String, Object> params);


}

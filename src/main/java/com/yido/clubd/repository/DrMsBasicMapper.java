package com.yido.clubd.repository;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.yido.clubd.model.DrMsBasic;
import com.yido.clubd.model.MemberVO;

/**
 * 회원추가정보
 * 
 * @author bae
 *
 */
@Mapper
@Repository
public interface DrMsBasicMapper {

	// 회원추가정보 등록
	public int insertDrMsBasic(DrMsBasic drMsBasic);
	
	// 회원추가정보 수정
	public int updateDrMsBasic(DrMsBasic drMsBasic);
}

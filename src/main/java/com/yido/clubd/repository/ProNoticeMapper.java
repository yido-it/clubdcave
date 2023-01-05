package com.yido.clubd.repository;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.yido.clubd.model.ProNotice;

/**
 * 레슨프로특이사항
 * 
 * @author bae
 *
 */
@Mapper
@Repository
public interface ProNoticeMapper {

	// 레슨프로특이사항 등록
	public int insertProNotice(ProNotice proNotice);
	
	// 레슨프로특이사항 수정
	public int updateProNotice(ProNotice proNotice);

}

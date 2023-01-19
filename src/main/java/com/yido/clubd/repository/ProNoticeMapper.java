package com.yido.clubd.repository;


import java.util.List;
import java.util.Map;

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
	
	// 레슨프로특이사항 조회
	public List<ProNotice> selectProNoticeList(Map<String, Object> map);
	
	// 레슨프로이미지 조회
	public List<ProNotice> selectProImageList(Map<String, Object> map);

}

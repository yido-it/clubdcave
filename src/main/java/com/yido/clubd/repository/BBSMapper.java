package com.yido.clubd.repository;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.yido.clubd.model.BBS;

/**
 * 게시판
 * 
 * @author bae
 *
 */
@Mapper
@Repository
public interface BBSMapper {

	// 게시판 목록 
	public List<BBS> selectList(Map<String, Object> map);
	
	// 게시판 상세
	public BBS getNotice(Map<String, Object> map);

}

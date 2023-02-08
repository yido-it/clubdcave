package com.yido.clubd.repository;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.yido.clubd.model.ProVO;

/**
 * 레슨프로자격사항
 * 
 * @author bae
 *
 */
@Mapper
@Repository
public interface ProMapper {
	
	// 레슨프로특이사항 등록
	public int insertProNotice(ProVO proVO);
	
	// 레슨프로특이사항 수정
	public int updateProNotice(ProVO proVO);
	
	// 레슨프로특이사항 조회
	public List<ProVO> selectProNoticeList(Map<String, Object> map);
	
	// 레슨프로이미지 조회
	public List<ProVO> selectProImageList(Map<String, Object> map);
	
	// 레슨프로자격사항 조회
	public List<ProVO> selectProLicenseList(Map<String, Object> map);
	
	// 레슨프로자격사항 등록
	public int insertProLicense(ProVO proVO);
	
	// 레슨프로자격사항 수정
	public int updateProLicense(ProVO proVO);

	public void insertProImage(Map<String, Object> params);

}

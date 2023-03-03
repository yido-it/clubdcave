package com.yido.clubd.repository;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.yido.clubd.model.ProVO;

/**
 * 프로
 * 
 * @author YOO
 *
 */
@Mapper
@Repository
public interface ProMapper {
	
	// 프로 특이사항 등록
	public int insertProNotice(ProVO proVO);
	
	// 프로 특이사항 수정
	public int updateProNotice(ProVO proVO);
	
	// 프로 특이사항 조회
	public List<ProVO> selectProNoticeList(Map<String, Object> map);
	
	// 프로 갤러리 이미지/영상 조회
	public List<ProVO> selectProImageList(Map<String, Object> map);
	
	// 프로 자격사항 조회
	public List<ProVO> selectProLicenseList(Map<String, Object> map);
	
	// 프로 자격사항 등록
	public int insertProLicense(ProVO proVO);
	
	// 프로 자격사항 수정
	public int updateProLicense(ProVO proVO);

	// 프로 갤러리 이미지/영상 등록
	public void insertProImage(Map<String, Object> params);

	// 프로 특이사항 조회
	public ProVO selectProNotice(ProVO proVO);

	// 프로 자격사항 조회
	public ProVO selectProLicense(ProVO proVO);

	// 프로 자격사항 전부 삭제
	public void deleteAllProLicense(Map<String, Object> params);

	// 프로 갤러리 이미지/영상 삭제
	public void deleteProImage(Map<String, Object> params);

}

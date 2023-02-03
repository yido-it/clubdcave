package com.yido.clubd.repository;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.yido.clubd.model.ProLogVO;

/**
 * 레슨프로자격사항
 * 
 * @author bae
 *
 */
@Mapper
@Repository
public interface ProLogMapper {
	
	// 레슨프로특이사항 등록
	public int insertProNoticeLog(ProLogVO prLogoVO);
	
	// 레슨프로자격사항 등록
	public int insertProLicenseLog(ProLogVO proLogVO);

}

package com.yido.clubd.repository;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.yido.clubd.model.ProLicenseLog;
import com.yido.clubd.model.ProNoticeLog;

/**
 *  레슨프로자격사항 로그
 * 
 * @author bae
 *
 */
@Mapper
@Repository
public interface ProLicenseLogMapper {

	// 로그 등록
	public int insertProLicenseLog(ProLicenseLog proLicenseLog);

}

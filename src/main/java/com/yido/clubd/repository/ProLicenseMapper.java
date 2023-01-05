package com.yido.clubd.repository;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.yido.clubd.model.ProLicense;

/**
 * 레슨프로자격사항
 * 
 * @author bae
 *
 */
@Mapper
@Repository
public interface ProLicenseMapper {

	// 레슨프로자격사항 등록
	public int insertProLicense(ProLicense proLicense);
	
	// 레슨프로자격사항 수정
	public int updateProLicense(ProLicense proLicense);

}

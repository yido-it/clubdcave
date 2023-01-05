package com.yido.clubd.repository;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.yido.clubd.model.DrVoucherUse;

/**
 * 이용권사용내역
 * 
 * @author bae
 *
 */
@Mapper
@Repository
public interface DrVoucherUseMapper {

	// 이용권사용내역 등록
	public int insertDrVoucherUse(DrVoucherUse drVoucherUse);

}

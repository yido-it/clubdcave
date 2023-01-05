package com.yido.clubd.repository;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.yido.clubd.model.DrVoucherCode;

/**
 * 이용권코드
 * 
 * @author bae
 *
 */
@Mapper
@Repository
public interface DrVoucherCodeMapper {

	// 이용권 조회 
	public List<DrVoucherCode> selectList(DrVoucherCode drVoucherCode);

}

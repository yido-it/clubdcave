package com.yido.clubd.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.yido.clubd.model.DrVoucherSale;

/**
 * 회원이용권 구매내역 로그
 * 
 * @author bae
 *
 */
@Mapper
@Repository
public interface DrVoucherSaleLogMapper {
	
	//로그 등록
	public int insertDrVoucherSaleLog(DrVoucherSale drVoucherSale);

}

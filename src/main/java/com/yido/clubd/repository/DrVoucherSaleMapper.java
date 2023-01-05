package com.yido.clubd.repository;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.yido.clubd.model.DrVoucherSale;

/**
 * 회원이용권 구매내역
 * 
 * @author bae
 *
 */
@Mapper
@Repository
public interface DrVoucherSaleMapper {

	// 회원이용권 구매내역 등록
	public int insertDrVoucherSale(DrVoucherSale drVoucherSale);
	
	// 회원이용권 구매내역 수정
	public int updateDrVoucherSale(DrVoucherSale drVoucherSale);

}

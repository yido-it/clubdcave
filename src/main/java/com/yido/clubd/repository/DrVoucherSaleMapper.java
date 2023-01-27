package com.yido.clubd.repository;


import java.util.List;
import java.util.Map;

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
	
	// 이용권 구매내역 조회
	public List<Map<String, Object>> selectList(Map<String, Object> map);
	
	public DrVoucherSale getVoucherSale(DrVoucherSale drVoucherSale);

	// 회원이용권 구매내역 등록
	public int insertDrVoucherSale(DrVoucherSale drVoucherSale);
	
	// 회원이용권 구매내역 수정
	public int updateDrVoucherSale(DrVoucherSale drVoucherSale);
	
	// 회원이용권 잔여수량 수정
	public int updateVcRemCnt(DrVoucherSale drVoucherSale);

}

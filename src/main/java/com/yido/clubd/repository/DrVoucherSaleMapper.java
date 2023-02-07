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

	// 고유번호 채번
	public String getSerialNo();
	
	public int getSaleSeq();
	
	// 이용권 구매내역 조회
	public List<Map<String, Object>> selectSaleList(Map<String, Object> map);
	
	// 최근 이용권 구매내역 
	public Map<String, Object> getLastSale(Map<String, Object> map);
	
	// 이용권 구매내역 조회 (예약페이지에서 필요한 정보)
	public List<Map<String, Object>> selectList(Map<String, Object> map);
	
	public DrVoucherSale getVoucherSale(DrVoucherSale drVoucherSale);

	// 이용권 구매내역 등록
	public int insertDrVoucherSale(DrVoucherSale drVoucherSale);
	
	// 이용권 잔여수량 변경
	public int updateVcRemCnt(DrVoucherSale drVoucherSale);
	
	// 이용권 상태 변경 
	public int updateState(DrVoucherSale drVoucherSale);

}

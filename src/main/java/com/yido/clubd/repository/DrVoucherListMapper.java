package com.yido.clubd.repository;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.yido.clubd.model.DrVoucherList;
import com.yido.clubd.model.DrVoucherSale;

/**
 * 이용권세부내역
 * 
 * @author bae
 *
 */
@Mapper
@Repository
public interface DrVoucherListMapper {


	// 이용권세부내역 등록
	public int insertDrVoucherList(DrVoucherList drVoucherList);

}

package com.yido.clubd.repository;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.yido.clubd.model.DrVoucherCodeLog;

/**
 * 이용권코드 로그
 * 
 * @author bae
 *
 */
@Mapper
@Repository
public interface DrVoucherCodeLogMapper {

	public DrVoucherCodeLog selectList(DrVoucherCodeLog drVoucherCodeLog);

}

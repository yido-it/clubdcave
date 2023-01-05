package com.yido.clubd.repository;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.yido.clubd.model.DrClBayMake;

/**
 * 일자별 BAY별 생성규칙
 * 
 * @author bae
 *
 */
@Mapper
@Repository
public interface DrClBayMakeMapper {

	public DrClBayMake selectList(DrClBayMake drClBayMake);

}

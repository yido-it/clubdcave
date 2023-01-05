package com.yido.clubd.repository;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.yido.clubd.model.DrBkMark;

/**
 * 예약선점용
 * 
 * @author bae
 *
 */
@Mapper
@Repository
public interface DrBkMarkMapper {

	public DrBkMark selectList(DrBkMark drBkMark);

}

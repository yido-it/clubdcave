package com.yido.clubd.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.yido.clubd.model.DrBayInfo;

/**
 * BAY관리
 * 
 * @author bae
 *
 */
@Mapper
@Repository
public interface DrBayInfoMapper {

	public List<DrBayInfo> selectList(Map<String, Object> map);


}

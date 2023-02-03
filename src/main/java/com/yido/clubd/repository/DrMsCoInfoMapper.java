package com.yido.clubd.repository;


import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.yido.clubd.model.DrMsCoInfo;
import com.yido.clubd.model.MemberVO;

/**
 * 회원지점연결정보
 * 
 * @author bae
 *
 */
@Mapper
@Repository
public interface DrMsCoInfoMapper {
	
	public DrMsCoInfo selectDrMsCoInfo(Map<String, Object> params);

	public void insertDrMsCoInfo(DrMsCoInfo drMsCoInfo);

	public void updateFirstPickY(DrMsCoInfo drMsCoInfo);

	public DrMsCoInfo selectFirstPick(String msNum);

}

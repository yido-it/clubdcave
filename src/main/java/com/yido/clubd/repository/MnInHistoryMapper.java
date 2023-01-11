package com.yido.clubd.repository;

import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 입금내역
 * 
 * @author bae
 *
 */
@Mapper
@Repository
public interface MnInHistoryMapper {

	public int getMnSeq(Map<String, Object> params);
	
	// 입금내역 등록
	public int insertMnInHistory(Map<String, Object> params);
	
	// 입금내역 변경
	public int updateMnInHistory(Map<String, Object> params);
	
	// 카드사 정보조회
	public Map<String, Object> selectCardInfo(Map<String, Object> params);
	
}

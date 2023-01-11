package com.yido.clubd.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yido.clubd.common.utils.ResultVO;
import com.yido.clubd.common.utils.SessionVO;
import com.yido.clubd.model.DrBkMark;
import com.yido.clubd.model.DrBkTime;
import com.yido.clubd.repository.DrBkMarkMapper;
import com.yido.clubd.repository.DrBkTimeMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BookService {

	@Autowired
    private DrBkTimeMapper drBkTimeMapper;

	@Autowired
    private DrBkMarkMapper drBkMarkMapper;
	
	/**
	 * 잔여수량 체크(=예약가능여부 체크) + 예약선점 처리
	 * - 결과는 예약불가능 or 예약가능 2가지 경우뿐이다.
	 *  
	 * @param params
	 * @return
	 */
	@Transactional
    public ResultVO chkBookLogic(Map<String, Object> params) throws Exception  {
    	ResultVO result = new ResultVO();
    	
        try {
        
			// DR_BK_TIME 잔여수량 체크
			DrBkTime drBkTime = drBkTimeMapper.getBkTime(params);
			log.info("[chkBookLogic] 잔여수량 > {} ", drBkTime.getBkRemCount());
			if (drBkTime.getBkRemCount() <= 0 ) {
				result.setCode("9999");
				result.setMessage("죄송합니다. 해당 시간 예약 마감 되었습니다.");	
				return result;
			} 
			
			// DR_BK_MARK 예약선점 데이터 체크
			List<DrBkMark> markList = drBkMarkMapper.selectAvailableData(params);
			log.info("[chkBookLogic] 예약선점 테이블 조회 건수 > {}", markList.size());
			
			if (markList == null || markList.size() == 0) {
				result.setCode("9999");
				result.setMessage("죄송합니다. 해당 시간 예약 마감 되었습니다.");	
				return result;
			} else {
				// 예약 선점 처리
				params.put("insertEntryDt", "Y");	// entry_datetime 에 값을 넣겠다는 뜻 
				params.put("entryTime", 15);		// 이부분 나중에 공통코드로 바꿔줘야함
				params.put("bkSeq", markList.get(0).getBkSeq());
				params.put("entryMethod", "모바일");			
				params.put("updMsId", params.get("msId"));			
				drBkMarkMapper.updateDrBkMark(params);
				
				// 잔여수량 차감 
				params.put("minusRemCnt", "Y");
				drBkTimeMapper.updateBkRemCount(params);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			result.setCode("9999");
			result.setMessage("서버와의 통신에 오류가 있습니다.");
		}
    	return result;
    }
	
	/**
	 * 예약 선점된거 풀기 
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@Transactional
    public ResultVO unBkMarkLogic(Map<String, Object> params) throws Exception  {
    	ResultVO result = new ResultVO();
    	
    	try {
			// 해당 아이디로 예약 선점한거 가져오기 (BK_SEQ 필요함)
			Map<String, Object> tmpMap = new HashMap<String, Object>();
			tmpMap.put("coDiv"		, params.get("coDiv"));
			tmpMap.put("bayCondi"	, params.get("bayCondi"));
			tmpMap.put("bkDay"		, params.get("bkDay"));
			tmpMap.put("bkTime"		, params.get("bkTime"));
			tmpMap.put("msId"		, params.get("msId"));
			List<DrBkMark> markList = drBkMarkMapper.selectList(tmpMap);
			log.info("[unBkMark] 해당 아이디로 예약 선점한거 조회 > 파라미터 : {}, 결과 : {}", tmpMap, markList);
			// end.
	
			if (markList != null && markList.size() > 0) {
	
				// 예약 선점 해제 
				params.put("deleteEntryDt"	, "Y");	// entry_datetime 값을 비우겠다는뜻
				params.put("ipAddr"			, "");
				params.put("bkSeq"			, markList.get(0).getBkSeq());
				params.put("updMsId"		, "");
				params.put("entryMethod"	, "");
				drBkMarkMapper.updateDrBkMark(params);
				
				// 잔여수량 복원 
				params.put("addRemCnt", "Y");
				drBkTimeMapper.updateBkRemCount(params);
			}   	
		} catch(Exception e) {
			e.printStackTrace();
			result.setCode("9999");
			result.setMessage("서버와의 통신에 오류가 있습니다.");
		}	    	
    	return result;
	}
}

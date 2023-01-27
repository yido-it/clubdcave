package com.yido.clubd.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yido.clubd.common.repository.CommonMapper;
import com.yido.clubd.common.utils.ResultVO;
import com.yido.clubd.common.utils.SessionVO;
import com.yido.clubd.model.BookInfoVO;
import com.yido.clubd.model.CdCommon;
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
	
	@Autowired
    private CommonMapper cdCommonMapper;
	
	/**
	 * 잔여수량 체크(=예약가능여부 체크) + 예약선점 처리
	 * - 결과는 예약불가능 or 예약가능 2가지 경우뿐이다.
	 *  
	 * @param params
	 * @return
	 */
	@Transactional
    public ResultVO chkBookLogic(BookInfoVO bkInfo) throws Exception  {
    	ResultVO result = new ResultVO();
		
        try {
        	log.info("[chkBookLogic] bookInfo : " + bkInfo);
			
			// DR_BK_TIME 잔여수량 체크 (1/19 8시에 대한 잔여수량 조회)
			List<DrBkTime> tList = drBkTimeMapper.getBkTime(bkInfo);
			log.info("[chkBookLogic] 잔여수량 > {} ", tList.get(0).getBkRemCount());
			if (tList.get(0).getBkRemCount() <= 0 ) {
				result.setCode("9999");
				result.setMessage("죄송합니다. 해당 시간 예약 마감 되었습니다.");	
				return result;
			} 
			
			// DR_BK_MARK 예약선점 데이터 체크 (1/19 8시에 대한 예약선점 가능한 데이터 조회)
			List<DrBkMark> markList = drBkMarkMapper.selectAvailableData(bkInfo);
			log.info("[chkBookLogic] 예약선점 테이블 조회 건수 > {}", markList.size());
			
			if (markList == null || markList.size() == 0) {
				// 예약 가능한 데이터 없을 경우
				result.setCode("9999");
				result.setMessage("죄송합니다. 해당 시간 예약 마감 되었습니다.");	
				return result;
			} else {
				// 예약 가능한 데이터 있는 경우, 선점 처리
				Map<String, Object> param = new HashMap<String, Object>();

				// 선점 시간 조회 
				CdCommon common = new CdCommon();
				common.setCoDiv(bkInfo.getCoDiv());
				common.setCdDivision("999");
				common.setCdCode("DRC03");
				common = cdCommonMapper.getCommonCode(common);
				Integer eTime = common != null && !common.getCdLength().equals("") ? 
						Integer.parseInt(common.getCdLength()) : 15;
				// end.
				
				param.put("entryMethod"		, "모바일");	
				param.put("updMsId"			, bkInfo.getMsId());		
				param.put("ipAddr"			, bkInfo.getIpAddr());		
				param.put("insertEntryDt"	, "Y");			// entry_datetime 에 값을 넣겠다는 뜻 
				param.put("entryTime"		, eTime);			
				
				param.put("coDiv"			, bkInfo.getCoDiv());
				param.put("bayCondi"		, bkInfo.getBayCondi());
				param.put("bkDay"			, bkInfo.getBkDay());
				param.put("bkTime"			, bkInfo.getBkTime());
				
				param.put("bkSeq"			, markList.get(0).getBkSeq());	
				
				// (DR_BK_MARK) 선점 처리
				drBkMarkMapper.updateDrBkMark(param);
				
				// (DR_BK_TIME) 잔여수량 차감 
				param.put("minusRemCnt", "Y");
				drBkTimeMapper.updateBkRemCount(param);
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
    public ResultVO unBkMarkLogic(BookInfoVO bkInfo) throws Exception  {
    	ResultVO result = new ResultVO();

		log.info("[unBkMarkLogic] bkInfo : {}", bkInfo);
		
    	try {
			// 해당 아이디로 예약 선점한거 가져오기 (BK_SEQ 필요함)
			List<DrBkMark> markList = drBkMarkMapper.selectList(bkInfo);
			log.info("[unBkMarkLogic] 해당 아이디로 예약 선점한거 조회 > 결과 : {}", markList);
			// end.
	
			if (markList != null && markList.size() > 0) {

				Map<String, Object> param = new HashMap<String, Object>();
				
				param.put("entryMethod"		, "");
				param.put("updMsId"			, "");
				param.put("ipAddr"			, "");
				param.put("deleteEntryDt"	, "Y");		// entry_datetime 값을 비우겠다는뜻
				
				param.put("coDiv"			, bkInfo.getCoDiv());
				param.put("bayCondi"		, bkInfo.getBayCondi());
				param.put("bkDay"			, bkInfo.getBkDay());
				param.put("bkTime"			, bkInfo.getBkTime());
				param.put("bkSeq"			, markList.get(0).getBkSeq());
				
				// 예약 선점 해제 
				drBkMarkMapper.updateDrBkMark(param);
				
				// 잔여수량 복원 
				param.put("addRemCnt", "Y");
				drBkTimeMapper.updateBkRemCount(param);
			}   	
		} catch(Exception e) {
			e.printStackTrace();
			result.setCode("9999");
			result.setMessage("서버와의 통신에 오류가 있습니다.");
		}	    	
    	return result;
	}
}

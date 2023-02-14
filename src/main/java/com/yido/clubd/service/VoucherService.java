package com.yido.clubd.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yido.clubd.common.utils.ResultVO;
import com.yido.clubd.common.utils.SessionVO;
import com.yido.clubd.model.BookInfoVO;
import com.yido.clubd.model.DrBkHistory;
import com.yido.clubd.model.DrBkHistoryTemp;
import com.yido.clubd.model.DrBkMnMap;
import com.yido.clubd.model.DrVoucherCode;
import com.yido.clubd.model.DrVoucherList;
import com.yido.clubd.model.DrVoucherSale;
import com.yido.clubd.model.DrVoucherUse;
import com.yido.clubd.model.MnInHistory;
import com.yido.clubd.model.VouInfoVO;
import com.yido.clubd.repository.DrBkHistoryTempMapper;
import com.yido.clubd.repository.DrVoucherCodeMapper;
import com.yido.clubd.repository.DrVoucherListMapper;
import com.yido.clubd.repository.DrVoucherSaleLogMapper;
import com.yido.clubd.repository.DrVoucherSaleMapper;
import com.yido.clubd.repository.DrVoucherUseMapper;
import com.yido.clubd.repository.MnInHistoryMapper;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class VoucherService {

	@Autowired
    private DrBkHistoryService drBkHistoryService;
	
	@Autowired
    private DrBkHistoryLogService drBkHistoryLogService;
	
	@Autowired
    private BookService bookService;
	
	@Autowired
    private MnInHistoryService mnInHistoryService;
	
	@Autowired
    private DrVoucherSaleMapper drVoucherSaleMapper;
	
	@Autowired
    private DrVoucherSaleLogMapper drVoucherSaleLogMapper;
	
	@Autowired
    private DrVoucherCodeMapper drVoucherCodeMapper;
	
	@Autowired
    private DrVoucherListMapper drVoucherListMapper;
	
	@Autowired
    private DrVoucherUseMapper drVoucherUseMapper;
	
	@Autowired
    private DrBkHistoryTempMapper drBkHistoryTempMapper;
	
	@Autowired
    private MnInHistoryMapper mnInHistoryMapper;
	
	/**
	 * [예약] 이용권 결제 (결제금액 0원인 경우) 
	 *  
	 * @param bInfo
	 * @return
	 * @throws Exception 
	 */
	@Transactional
	public void vPay(BookInfoVO bInfo, SessionVO sessionVO) throws Exception {	
	
		log.debug("[vPay] param > bInfo : " + bInfo);
		
		// 예약 내역 insert 
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("coDiv"		, bInfo.getCoDiv());
		param.put("bayCondi"	, bInfo.getBayCondi());
		param.put("bkDay"		, bInfo.getBkDay());
		param.put("bkTime"		, bInfo.getBkTime());
		param.put("bkTime"		, bInfo.getBkTime());
		param.put("ipAddr"		, bInfo.getIpAddr());
		param.put("bkAmount"	, bInfo.getBkAmount());
		param.put("oriBkAmount"	, bInfo.getOriBkAmount());
		param.put("bkPayDiv"	, 1); // 이용권 
		param.put("isVoucherUse", "Y");	

		// 예약 처리 
		Map<String, Object> returnMap = drBkHistoryService.actionReservation(param, sessionVO);

		// 이용권 사용 처리 
		this.useVoucher(bInfo, returnMap);
	
		// 예약 임시테이블 데이터 삭제
		DrBkHistoryTemp temp = new DrBkHistoryTemp();
		temp.setSerialNo(bInfo.getTempSerialNo());
		drBkHistoryTempMapper.deleteHistoryTemp(temp);
		// end.
		
	}
	
	/**
	 * 이용권 사용처리
	 * 
	 * @param bInfo
	 * @param returnMap
	 * @throws Exception
	 */
	@Transactional
	public void useVoucher(BookInfoVO bInfo, Map<String, Object> returnMap) throws Exception {	

		log.debug("[useVoucher] param > bInfo : " + bInfo);
		
		int i = 0;
		for (Map<String, Object> vou : bInfo.getVList()) {

			// 이용권 잔여수량 업데이트 
			DrVoucherSale sale = new DrVoucherSale();
			sale.setCoDiv(bInfo.getCoDiv());
			sale.setSaleDay(vou.get("saleDay").toString());
			sale.setSaleSeq(Integer.parseInt(vou.get("saleSeq").toString()));
			sale.setVcRemCnt(Integer.parseInt(vou.get("quantity").toString()));
			sale.setGubun("M");		// 사용처리
			drVoucherSaleMapper.updateVcRemCnt(sale);
			
			// 이용권 잔여수량 변경된 로그
			sale = drVoucherSaleMapper.getVoucherSale(sale);
			sale.setInputIp(bInfo.getIpAddr());			
			sale.setLogDiv("U");
			drVoucherSaleLogMapper.insertDrVoucherSaleLog(sale);
			
			// 이용권 세부 내역 업데이트
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("coDiv" 	, bInfo.getCoDiv());
			map.put("saleDay"	, vou.get("saleDay").toString());
			map.put("saleSeq"	, Integer.parseInt(vou.get("saleSeq").toString()));
			map.put("vcState"	, "N");
			// 사용할 이용권 수량만큼 세부내역 가져오기 
			map.put("limit"		, Integer.parseInt(vou.get("quantity").toString()));	
			List<DrVoucherList> vList = drVoucherListMapper.selectList(map);
			
			// 이용권 세부내역 ( 'A이용권' 2장 사용할 경우 세부내역 2건 update )
			for (DrVoucherList dr : vList) {	
						
				dr.setVcState("Y");
				drVoucherListMapper.updateState(dr);
				
				// 이용권 사용내역 (예약고유번호, 이용권 순번 매칭)
				dr.setSaleSeq(Integer.parseInt(vou.get("saleSeq").toString()));

				DrVoucherUse vUse = new DrVoucherUse();
				vUse.setListSeq(dr.getListSeq());	// 이용권 세부순번
				vUse.setCoDiv(bInfo.getCoDiv());
				vUse.setVcCoDiv(bInfo.getCoDiv());
				vUse.setSaleDay(vou.get("saleDay").toString());						// 이용권 매출일자
				vUse.setSaleSeq(Integer.parseInt(vou.get("saleSeq").toString()));	// 이용권 매출순번
				
				List<Object> arrSerialNo = (List<Object>) returnMap.get("arrSerialNo");
				vUse.setBkSerialNo(arrSerialNo.get(i).toString());
				drVoucherUseMapper.insertDrVoucherUse(vUse);

				// 이용권 사용한거에 대한 예약 이용금액은 0원으로 UPDATE 
				DrBkHistory bkHis = new DrBkHistory();
				bkHis.setBkSerialNo(arrSerialNo.get(i).toString());
				bkHis.setBkAmount(0);
				drBkHistoryService.updateBkAmount(bkHis);
				drBkHistoryLogService.updateBkAmount(bkHis);
				
				i++;
			}			
		}		
	}
	
	/**
	 * [예약] 이용권 결제취소 (결제금액 0원인 경우) 
	 *  
	 * @param bInfo
	 * @return
	 * @throws Exception 
	 */
	@Transactional
	public ResultVO vCancel(BookInfoVO bInfo) throws Exception {	
		ResultVO resultVO = new ResultVO();

		// 예약내역 조회 (예약고유번호 획득)
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("calcSerialNo", bInfo.getCalcSerialNo());
		List<DrBkHistory> bList = drBkHistoryService.selectList(param);		
		log.debug("[vCancel] 예약건수: " + bList.size());
		
		if (bList.size() <= 0) {
			resultVO.setCode("9999");
			resultVO.setMessage("예약 정보가 존재하지 않습니다.");
		}
		
		// 이용권 사용 취소
		this.cancelVoucher(bInfo, bList);
		
		// 예약 취소
		Map<String, Object> cMap = new HashMap<String, Object>();
		bookService.cancelBook(bInfo, bList, cMap);
		
		return resultVO;
	}	
	
	/**
	 * 이용권 사용 취소 
	 * - 사용내역 삭제
	 * - 세부내역 상태 변경 + 로그 
	 * - 구매내역 수량 복원 + 로그 
	 * 
	 * @param bInfo
	 * @throws Exception
	 */
	@Transactional
	public void cancelVoucher(BookInfoVO bInfo, List<DrBkHistory> bList) throws Exception {	
		
		Map<String, Object> param = new HashMap<String, Object>();
		List<String> sNoList = new ArrayList<String>();
		
		for (DrBkHistory sNo : bList) {
			sNoList.add(sNo.getBkSerialNo()); 
		}
		param.put("sNoList", sNoList);	

		// 이용권 사용내역 조회 (지점코드, 매출일자, 매출순번 획득)
		List<DrVoucherUse> uList = drVoucherUseMapper.selectList(param);
		log.debug("[cancelVoucher] 이용권 사용 건수: " + uList.size());
		
		int i = 0;
		for (DrVoucherUse vUse : uList) {
			
			// 이용권 세부내역 > 지점코드, 매출일자, 매출순번으로 조회하여 수량 획득 
			Map<String, Object> param2 = new HashMap<String, Object>();
			param2.put("coDiv", vUse.getVcCoDiv());
			param2.put("saleDay", vUse.getSaleDay());
			param2.put("saleSeq", vUse.getSaleSeq());
			param2.put("listSeq", vUse.getListSeq());
			param2.put("vcState", "Y");						
			List<DrVoucherList> vList = drVoucherListMapper.selectList(param2);
			log.debug("[cancelVoucher] 이용권 세부 건수: " + vList.size());
			
			for (DrVoucherList vou : vList) {

				// 이용권 세부내역 > 상태 N 으로 변경  
				DrVoucherList drVoucherList = new DrVoucherList();
				drVoucherList.setVcState("N");
				drVoucherList.setListSeq(vou.getListSeq());
				drVoucherList.setSaleSeq(vou.getSaleSeq());
				drVoucherListMapper.updateState(drVoucherList);
			}
			 		
			// 이용권 구매내역 잔존수량 복원 
			DrVoucherSale drVoucherSale = new DrVoucherSale();
			drVoucherSale.setGubun("P");				// 복원 처리
			drVoucherSale.setVcRemCnt(vList.size());	// 위에서 조회한 이용권 사용수량만큼 복원처리 
			drVoucherSale.setCoDiv(uList.get(i).getVcCoDiv());
			drVoucherSale.setSaleDay(uList.get(i).getSaleDay());
			drVoucherSale.setSaleSeq(uList.get(i).getSaleSeq());
			drVoucherSaleMapper.updateVcRemCnt(drVoucherSale);
			
			drVoucherSale = drVoucherSaleMapper.getVoucherSale(drVoucherSale);

			// 이용권 구매내역 로그 
			drVoucherSale.setLogDiv("U");
			drVoucherSale.setInputIp(bInfo.getIpAddr());
			drVoucherSaleLogMapper.insertDrVoucherSaleLog(drVoucherSale);
					
			// 이용권 사용내역 삭제 
			drVoucherUseMapper.deleteDrVoucherUse(vUse);

			i++;
		}
	}
	/**
	 * 이용권 구매
	 * - 구매내역 insert, 구매내역로그 insert, 세부내역 insert
	 * - PG결제내역 insert 
	 * 
	 * @param param
	 * @param mnMap
	 * @throws Exception
	 */
	@Transactional
	public Map<String, Object> insertVouInfo(@RequestParam Map<String, Object> param, SessionVO session) throws Exception {

		Map<String, Object> returnMap = new HashMap<String, Object>();
		LocalDateTime nowDt = LocalDateTime.now();
		log.debug("[insertVouInfo] params : " + param);
		
		// 이용권 조회
		DrVoucherCode drCode = new DrVoucherCode();
		drCode.setVcCd(param.get("vcCd").toString());
		List<DrVoucherCode> dcList = drVoucherCodeMapper.selectList(drCode);
		drCode = dcList.get(0);
		log.info("[insertVouInfo] 구매할 이용권 : " + drCode);
		// end.

		String coDiv = param.get("coDiv").toString();
		String ipAddr = param.get("ipAddr").toString();
		String sNowDt = nowDt.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
	
		LocalDate fromDay = LocalDate.of(
				Integer.parseInt(sNowDt.substring(0, 4))
				, Integer.parseInt(sNowDt.substring(4, 6))
				, Integer.parseInt(sNowDt.substring(6, 8)));
		LocalDate toDay 	= fromDay.plusMonths(drCode.getVcMonth());
		
		String sToDay 		= toDay.format(DateTimeFormatter.ofPattern("yyyyMMdd"));	// 이용권 종료일자 
		String drSerialNo 	= param.get("drSerialNo").toString();						// 이용권 매출 고유번호
		int saleSeq			= drVoucherSaleMapper.getSaleSeq();							// 매출순번 채번
		
		// 이용권 구매내역 insert
		DrVoucherSale sale = new DrVoucherSale();
		sale.setSaleSeq(saleSeq);
		sale.setSaleDay(sNowDt);						// 매출일자
		sale.setCoDiv(coDiv);	// 지점코드
		sale.setMsNum(param.get("msNum").toString());	// 회원번호
		sale.setVcCd(param.get("vcCd").toString());		// 이용권코드
		sale.setDrSerialNo(drSerialNo);					// 고유번호
		sale.setVcFromDay(sNowDt);						// 이용시작일자 
		sale.setVcToDay(sToDay);						// 이용종료일자 
		sale.setVcLimitCnt(drCode.getVcLimitCnt());		// 총수량 
		sale.setVcRemCnt(drCode.getVcLimitCnt());
		sale.setVcAmount(drCode.getVcAmount());			// 판매금액
		sale.setVcNet(drCode.getVcNet());				// 공급가
		sale.setVcVat(drCode.getVcVat());				// 부가세 
		sale.setVcState("01");							// 상태 (01: 정상)
		sale.setInputIp(ipAddr);// 입력IP
		sale.setUseYn("Y");
		
		drVoucherSaleMapper.insertDrVoucherSale(sale);
		// end.
		
		// 이용권 구매내역 로그 insert
		sale.setLogDiv("I");
		drVoucherSaleLogMapper.insertDrVoucherSaleLog(sale);
		
		// 이용권 세부내역 insert
		
		// vcDivision > 001 : 기간권, 002 : 쿠폰 
		if (drCode.getVcDivision().equals("001")) {
		
			DrVoucherList dList = new DrVoucherList();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("saleSeq", saleSeq);
			int listSeq = drVoucherListMapper.getListSeq(map);
			
			dList.setCoDiv(coDiv);							// 지점코드 (FK)
			dList.setSaleDay(sNowDt);						// 매출일자 (FK)
			dList.setSaleSeq(saleSeq);						// 매출순번 (FK)
			dList.setListSeq(listSeq);						// 세부순번 (기간권의 경우 1)
			dList.setVcOneAmount(drCode.getVcAmount()); 	// 장당 판매금액
			dList.setVcOneNet(drCode.getVcNet()); 			// 장당 공급가
			dList.setVcOneVat(drCode.getVcVat()); 			// 장당 부가세 
			dList.setVcState("N");							// 미사용
			dList.setInputIp(ipAddr);						// 입력IP
			
			drVoucherListMapper.insertDrVoucherList(dList);
		
		} else {
			int oneAmt = drCode.getVcAmount() / drCode.getVcLimitCnt();	// 장당 판매금액
			int oneNet = drCode.getVcNet() / drCode.getVcLimitCnt();	// 장당 공급가
			int oneVat = drCode.getVcVat() / drCode.getVcLimitCnt();	// 장당 공급가
			
			for (int i = 0; i < drCode.getVcLimitCnt(); i++) {
				DrVoucherList dList = new DrVoucherList();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("saleSeq", saleSeq);
				int listSeq = drVoucherListMapper.getListSeq(map);
				
				dList.setCoDiv(coDiv);				// 지점코드 (FK)
				dList.setSaleDay(sNowDt);			// 매출일자 (FK)
				dList.setSaleSeq(saleSeq);			// 매출순번 (FK)
				dList.setListSeq(listSeq);			// 세부순번 (기간권의 경우 1)
				dList.setVcOneAmount(oneAmt); 		// 장당 판매금액
				dList.setVcOneNet(oneNet); 			// 장당 공급가
				dList.setVcOneVat(oneVat); 			// 장당 부가세 
				dList.setVcState("N");				// 미사용
				dList.setInputIp(ipAddr);			// 입력IP
				
				drVoucherListMapper.insertDrVoucherList(dList);
			}
		}
		// end.
		
        // PG 결제내역 등록
		if (param.get("mnSeq") != null ) {
	        param.put("mnSeq"		, param.get("mnSeq"));
	        param.put("msNum"		, session.getMsNum());
	        param.put("mnInName"	, session.getMsName());		// 결제자 이름

	        mnInHistoryMapper.insertMnInHistory(param);
		}
        // end.
		
		return returnMap;
	}
	
	/**
	 * 이용권 구매 취소
	 * - PG결제 취소 처리 
	 * - 구매내역 update, 구매내역로그 insert, 세부내역 update 
	 * 
	 * @param vInfo
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public ResultVO cancel(VouInfoVO vInfo) throws Exception {	
		ResultVO resultVO = new ResultVO();
		
		MnInHistory mnHis = new MnInHistory();
		mnHis.setMnSerialNo(vInfo.getDrSerialNo());
		mnHis = mnInHistoryMapper.getMnInHistory(mnHis);
		
		String orderId = mnHis.getOrderId();
		String authAmount = String.valueOf(mnHis.getMnInAmount());
		String transactionId = mnHis.getTransactionId();
		String cancelKey = mnHis.getCancelkey();
		String ipAddr = vInfo.getIpAddr();
		
		log.info("[이용권 구매 취소] 주문번호(orderId): {}, 승인금액: {}, 거래번호(transactionId): {}, 취소키: {}"
				, orderId, authAmount, transactionId, cancelKey);
		
		// PG 결제 취소 처리 
		Map<String, Object> cMap = new HashMap<String, Object>();
		cMap.put("coDiv", vInfo.getCoDiv());
		cMap.put("orderId", orderId);
		cMap.put("authAmount", authAmount);
		cMap.put("transactionId", transactionId);
		cMap.put("cancelKey", cancelKey);
		cMap.put("ipAddr", ipAddr);
		cMap.put("cancelType", "C");
		cMap.put("mnHis", mnHis);
		mnInHistoryService.cancelLogic(cMap);

		// 구매내역 상태 update 
		DrVoucherSale dSale = new DrVoucherSale();
		dSale.setSaleSeq(vInfo.getSaleSeq());
		dSale.setVcState("99");	// 이용권 상태 > 99 : 삭제
		drVoucherSaleMapper.updateState(dSale);
		
		// 구매내역 로그 insert
		dSale = drVoucherSaleMapper.getVoucherSale(dSale);
		dSale.setLogDiv("U");
		drVoucherSaleLogMapper.insertDrVoucherSaleLog(dSale);
		
		// 세부내역 update 
		DrVoucherList dList = new DrVoucherList();
		dList.setVcState("X");	// X : 사용불가
		dList.setSaleSeq(vInfo.getSaleSeq());
		drVoucherListMapper.updateStateBySaleSeq(dList);
		
		return resultVO;
	}	
}

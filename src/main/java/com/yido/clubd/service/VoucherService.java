package com.yido.clubd.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yido.clubd.common.utils.ResultVO;
import com.yido.clubd.model.BookInfoVO;
import com.yido.clubd.model.DrBkHistory;
import com.yido.clubd.model.DrBkMnMap;
import com.yido.clubd.model.DrVoucherList;
import com.yido.clubd.model.DrVoucherSale;
import com.yido.clubd.model.DrVoucherUse;
import com.yido.clubd.repository.DrVoucherListMapper;
import com.yido.clubd.repository.DrVoucherSaleLogMapper;
import com.yido.clubd.repository.DrVoucherSaleMapper;
import com.yido.clubd.repository.DrVoucherUseMapper;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class VoucherService {

	@Autowired
    private DrBkHistoryService drBkHistoryService;
	
	@Autowired
    private DrVoucherSaleMapper drVoucherSaleMapper;
	
	@Autowired
    private DrVoucherSaleLogMapper drVoucherSaleLogMapper;
	
	@Autowired
    private DrVoucherListMapper drVoucherListMapper;
	
	@Autowired
    private DrVoucherUseMapper drVoucherUseMapper;
	
	/**
	 * 이용권 결제처리 (이용권 사용 후 결제금액 0원인 경우) 
	 *  
	 * @param bInfo
	 * @return
	 * @throws Exception 
	 */
	@Transactional
	public ResultVO vPay(BookInfoVO bInfo) throws Exception {	
		ResultVO resultVO = new ResultVO();
		
		/*
		 * bInfo 
		 * 
		 * coDiv=001, bkDay=20230131, bkTime=1100, 1300, bayCondi=001, bkAmount=0, msLevel=11
		 * , userMail=, msId=baeean, userName=배은화, ipAddr=null, phone=010-9989-9655, bkState=null
		 * , availableYn=null, bkList=null
		 * , vList=[{coDiv=001, quantity=1, saleDay=20230119, saleSeq=3}
		 * 		, {coDiv=001, quantity=1, saleDay=20230119, saleSeq=4}], timeList=null)
		 * 
		 * */
		
		
		// 예약 내역 insert 
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("coDiv"		, bInfo.getCoDiv());
		param.put("bayCondi"	, bInfo.getBayCondi());
		param.put("bkDay"		, bInfo.getBkDay());
		param.put("bkTime"		, bInfo.getBkTime());
		param.put("msId"		, bInfo.getMsId());
		param.put("msNum"		, bInfo.getMsNum());
		param.put("bkName"		, bInfo.getUserName());
		param.put("phone"		, bInfo.getPhone());
		param.put("bkTime"		, bInfo.getBkTime());
		param.put("ipAddr"		, bInfo.getIpAddr());
		param.put("bkAmount"	, bInfo.getBkAmount());
		param.put("bkPayDiv"	, 1); // 이용권 
		param.put("isVoucherUse", "Y");	

		Map<String, Object> returnMap = drBkHistoryService.actionReservation(param, new DrBkMnMap());

		// 이용권 사용 처리 
		this.useVoucher(bInfo, returnMap);
		resultVO.setData(returnMap.get("calcSerialNo"));	// 결제완료 페이지 이동시 필요한 대표예약고유번호
		
		return resultVO;
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
		
		/*
		 * bInfo 
		 * 
		 * coDiv=001, bkDay=20230131, bkTime=1100, 1300, bayCondi=001, bkAmount=0, msLevel=11
		 * , userMail=, msId=baeean, userName=배은화, ipAddr=null, phone=010-9989-9655, bkState=null
		 * , availableYn=null, bkList=null
		 * , vList=[{coDiv=001, quantity=1, saleDay=20230119, saleSeq=3}
		 * 		, {coDiv=001, quantity=1, saleDay=20230119, saleSeq=4}], timeList=null)
		 * 
		 * */
		
		int i = 0;
		for (Map<String, Object> vou : bInfo.getVList()) {

			// 이용권 잔여수량 업데이트 
			DrVoucherSale sale = new DrVoucherSale();
			sale.setCoDiv(bInfo.getCoDiv());
			sale.setSaleDay(vou.get("saleDay").toString());
			sale.setSaleSeq(Integer.parseInt(vou.get("saleSeq").toString()));
			sale.setVcRemCnt(Integer.parseInt(vou.get("quantity").toString()));
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
				drVoucherListMapper.updateDrVoucherList(dr);
				
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
				log.info("[이용권 사용내역 등록] serialno : {}, {}", i, arrSerialNo.get(i).toString());
				drVoucherUseMapper.insertDrVoucherUse(vUse);

				// 이용권 사용한거에 대한 예약 이용금액은 0원으로 UPDATE 
				DrBkHistory bkHis = new DrBkHistory();
				bkHis.setBkSerialNo(arrSerialNo.get(i).toString());
				bkHis.setBkAmount(0);
				drBkHistoryService.updateBkAmount(bkHis);
				i++;
			}			
		}		
	}
}

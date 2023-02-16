package com.yido.clubd.common.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yido.clubd.common.service.CommonService;
import com.yido.clubd.common.utils.AWSFileUtil;
import com.yido.clubd.common.utils.ResultVO;
import com.yido.clubd.common.utils.Utils;
import com.yido.clubd.model.CdCommon;
import com.yido.clubd.model.MemberVO;

@Controller
@RequestMapping("/common")
public class CommonController {

	@Resource(name = "CommonService")
	private CommonService commonService;	

	@RequestMapping(value = "/savePushKey")
	public void savePushKey(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam Map<String, Object> params) {
		ResultVO result = new ResultVO();

		try {
			commonService.savePushKey(params);

			result.setMessage("푸쉬키가 등록되었습니다.");
		} catch (Exception e) {
			result.setCode("9999");
			result.setMessage(e.getMessage());
		} finally {
			Utils.sendData(response, Utils.makeJsonString(result));
		}
	}
	
	@RequestMapping(value = "/getCommonCodeDetailList")
	@ResponseBody
	public Map<String, Object> getCommonCodeDetailList(HttpServletRequest request, HttpServletResponse response,
			CdCommon cdCommon) {
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			List<CdCommon> detailList = commonService.getCommonCodeDetailList(cdCommon);
			map.put("result", true);
			map.put("detailList", detailList);
		} catch (Exception e) {
			map.put("result", false);
			map.put("message", "공통코드 상세 조회 중 오류가 발생했습니다.");
		}
		return map;
	}

	 
//	@RequestMapping(value = "/getCommonCode")
//	public void getCommonCode(HttpServletRequest request, HttpServletResponse response,
//			@RequestParam Map<String, Object> params) {
//		ResultVO result = new ResultVO();
//
//		try {
//			List<Map<String, Object>> list = commonService.getCommonCode(params);
//
//			result.setData(list);
//		} catch (Exception e) {
//			result.setCode("9999");
//			result.setMessage(e.getMessage());
//		} finally {
//			Utils.sendData(response, Utils.makeJsonString(result));
//		}
//	}

	
}

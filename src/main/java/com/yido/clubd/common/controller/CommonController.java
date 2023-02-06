package com.yido.clubd.common.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yido.clubd.common.service.CommonService;
import com.yido.clubd.common.utils.ResultVO;
import com.yido.clubd.common.utils.SessionVO;
import com.yido.clubd.common.utils.Utils;
import com.yido.clubd.model.DrBayInfo;
import com.yido.clubd.model.DrBkOpenTime;

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

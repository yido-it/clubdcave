package com.yido.clubd.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yido.clubd.model.DrBayInfo;
import com.yido.clubd.model.DrBkHistory;
import com.yido.clubd.model.DrBkOpenTime;
import com.yido.clubd.model.DrBkTime;
import com.yido.clubd.model.ProNotice;
import com.yido.clubd.service.DrBayInfoService;
import com.yido.clubd.service.DrBkHistoryService;
import com.yido.clubd.service.DrBkOpenTimeService;
import com.yido.clubd.service.DrBkTimeService;
import com.yido.clubd.service.DrCostInfoLogService;
import com.yido.clubd.service.DrCostInfoService;
import com.yido.clubd.service.DrMsMaininfoService;
import com.yido.clubd.service.DrProScheduleService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/cost")
public class CostController {

	@Autowired
	private DrCostInfoService drCostInfoService;

	@RequestMapping("/getCostInfo")
	@ResponseBody
	public Map<String, Object> getCostInfo(HttpServletRequest req, @RequestParam Map<String, Object> params){
		
		Map<String, Object> map = new HashMap<String, Object>();
		log.info("[getCostInfo] params :" + params);
		
		try {
			map = drCostInfoService.getCostInfo(params);

			log.info("[getCostInfo] map :" + map);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return map;
	}
	
	
}

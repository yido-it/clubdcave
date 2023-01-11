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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yido.clubd.common.service.CommonService;
import com.yido.clubd.common.utils.ResultVO;
import com.yido.clubd.common.utils.Utils;

@Controller
@RequestMapping("/common")
public class CommonController {

	@Resource(name = "CommonService")
	private CommonService commonService;

	@RequestMapping(value = "/doFindId")
	public void doFindId(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam Map<String, Object> params) {
		ResultVO result = new ResultVO();

		try {
			Map<String, Object> findId = commonService.doFindId(params);

			if(findId.get("msLoginCd").equals("KAKAO")) {
				throw new Exception("KAKAO 간편 로그인 회원 가입자입니다. 간편로그인으로 로그인 부탁드립니다.");
			}

			if(findId.get("msLoginCd").equals("NAVER")) {
				throw new Exception("NAVER 간편 로그인 회원 가입자입니다. 간편로그인으로 로그인 부탁드립니다.");
			}

			if (findId.get("msId") == null || findId.get("msId").equals("")) {
				throw new Exception("등록 된 아이디가 없습니다.");
			}

			params.put("ipAddr", Utils.getClientIpAddress(request));
			params.put("tplCd", Utils.getProperties("Globals.findId.tplCd", "00001"));
			params.put("msId", findId.get("msId"));

			commonService.sendSms(params);
		} catch (Exception e) {
			result.setCode("9999");
			result.setMessage(e.getMessage());
		} finally {
			Utils.sendData(response, Utils.makeJsonString(result));
		}
	}

	@RequestMapping(value = "/doFindPw")
	public void doFindPw(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam Map<String, Object> params) {
		ResultVO result = new ResultVO();

		try {
			Map<String, Object> map = commonService.doFindPw(params);

			if (map == null) {
				throw new Exception("등록 된 아이디가 없습니다.");
			}

			StringBuffer newPw = new StringBuffer();
			Random rnd = new Random();
			for (int i = 0; i < 6; i++) {
				int rIndex = rnd.nextInt(2);
				switch (rIndex) {
				case 0:
					newPw.append((char) ((int) (rnd.nextInt(26)) + 97));
					break;
				case 1:
					newPw.append((rnd.nextInt(10)));
					break;
				}
			}

			params.put("msNum", map.get("MS_NUM").toString());
			params.put("phone", map.get("PHONE").toString());
			params.put("newPw", newPw.toString());

			commonService.updatePw(params);

			params.put("ipAddr", Utils.getClientIpAddress(request));
			params.put("tplCd", Utils.getProperties("Globals.findPw.tplCd", "00001"));
			params.put("msPw", newPw.toString());

			commonService.sendSms(params);
		} catch (Exception e) {
			result.setCode("9999");
			result.setMessage(e.getMessage());
		} finally {
			Utils.sendData(response, Utils.makeJsonString(result));
		}
	}

	@RequestMapping(value = "/checkPhoneNumber")
	public void checkPhoneNumber(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam Map<String, Object> params) {
		ResultVO result = new ResultVO();

		try {
			if(commonService.checkPhoneNumber(params) > 0) {
				params.put("mergeYn", "Y");
				if(commonService.checkPhoneNumber(params) > 0) {
					result.setCode("1000");
	        		result.setMessage("이미 가입된 핸드폰번호 입니다.");
    			}else {
    				result.setCode("2000");
    				result.setMessage("이미 가입한 이력이 있습니다. 통합회원으로 새로 가입하시겠습니까?");
    			}
			}
		} catch (Exception e) {
			result.setCode("9999");
			result.setMessage(e.getMessage());
		} finally {
			Utils.sendData(response, Utils.makeJsonString(result));
		}
	}

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

	@RequestMapping(value = "/getCommonCode")
	public void getCommonCode(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> params) {
		ResultVO result = new ResultVO();

		try {
			List<Map<String, Object>> list = commonService.getCommonCode(params);

			result.setData(list);
		} catch (Exception e) {
			result.setCode("9999");
			result.setMessage(e.getMessage());
		} finally {
			Utils.sendData(response, Utils.makeJsonString(result));
		}
	}

}

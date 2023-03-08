package com.yido.clubd.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.yido.clubd.common.utils.AWSFileUtil;
import com.yido.clubd.common.utils.Globals;
import com.yido.clubd.common.utils.SessionVO;
import com.yido.clubd.common.utils.Utils;
import com.yido.clubd.model.DrMsCoInfo;
import com.yido.clubd.model.MemberVO;
import com.yido.clubd.model.Push;
import com.yido.clubd.repository.DrMsCoInfoMapper;
import com.yido.clubd.repository.MemberMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 회원정보 (회원/프로)
 * 
 * @author YOO
 *
 */
@Slf4j
@Service
public class MemberService {
	@Autowired
	DrMsCoInfoService drMsCoInfoService;
	
	@Autowired
    private MemberMapper memberMapper;
	
	@Autowired
	private DrMsCoInfoMapper drMsCoInfoMapper;
		
	public MemberVO selectMember(Map<String, Object> params) {
    	return memberMapper.selectMember(params);
	}	
	
	public List<MemberVO> selectMemberList(Map<String, Object> params) {
    	return memberMapper.selectMemberList(params);
	}	
    
    /**
	 * 로그인
	 * 
	 * @param params
	 * @return MemberVO
	 */
    public MemberVO selectLoginUser(Map<String, Object> params) throws Exception {
    	return memberMapper.selectLoginUser(params);
    }
    
    /**
     * MS_ID로 MS_NUM 조회
     * 
     * @param params
     * @return SesseionVO
     */
    public String selectMsNum(Map<String, Object> params) throws Exception {
    	return memberMapper.selectMsNum(params);
    }
    
    /**
     * 회원/프로 정보 가져와서 세션에 저장
     * 
     * @param params
     * @return SesseionVO
     */
    public SessionVO selectMsSession(Map<String, Object> params) throws Exception {
    	return memberMapper.selectMsSession(params);
    }
    
    public void insertLoginLog(Map<String, Object> params) throws Exception {
    	memberMapper.insertLoginLog(params);
    }
    
    /**
     * 회원 등록 
     * 
     * @param member
     * @return
     */
	public void insertMember(MemberVO member) throws Exception {
		
		/*
		 * String msNum = this.makeMsNum(member);
		 * member.setMsNum(msNum);
		 */
		
		member.setMsStatus("Y");
		member.setMsBkGrant("Y");
		member.setMsDivision("01");
		member.setMsLevel("11");
		member.setMsBlank("N");
		member.setMsEmailYn("N");		
		member.setMsDormant("N");			
		member.setInputStaff("APP");			
		member = this.seperateMsPhone(member);
		
		memberMapper.insertMember(member);
	}
	
	public MemberVO seperateMsPhone(MemberVO member) {
		String[] msPhone = member.getMsPhone().split("-");
		member.setMsFirstPhone1(msPhone[0]);
		member.setMsMidPhone1(msPhone[1]);
		member.setMsLastPhone1(msPhone[2]);	
		return member;
	}
	
	public Map<String, Object> seperateMsPhone2(Map<String, Object> params) {
		String[] msPhone = ((String)params.get("msPhone")).split("-");
		params.put("msFirstPhone1", msPhone[0]);
		params.put("msMidPhone1", msPhone[1]);
		params.put("msLastPhone1", msPhone[2]);	
		return params;
	}
	
	/**
     * 회원 수정 
     * 
     * @param params
     * @return SessionVO
     */
	public SessionVO updateMember(Map<String, Object> params) throws Exception {
		
		params.put("updateIp", Globals.serverIpAddress);
		params.put("updateStaff", "APP");
		if((String)params.get("msPhone") != null && (String)params.get("msPhone") != "") {
			params = this.seperateMsPhone2(params);			
		}
		
		memberMapper.updateMember(params);
		
		SessionVO member = this.selectMsSession(params);			
		return member;
	}
	
	/**
     * 회원 추가정보 저장
     * 
     * @param params
     * @return 
     */	
	public void saveMemberBasic(Map<String, Object> params) {
		
		if((String)params.get("msLessonTrem") != null && (String)params.get("msLessonTrem") != "") {			
			String msLessonTrem = ((String)params.get("msLessonTrem")).replace("회", "");
			String msLessonUnit = ("주".equals(msLessonTrem.substring(0,1)))? "01" : "02";
			msLessonTrem = msLessonTrem.substring(1);
			
			params.put("msLessonTrem", msLessonTrem);
			params.put("msLessonUnit", msLessonUnit);
		}
		
		if((String)params.get("msLessonMinute") != null && (String)params.get("msLessonMinute") != "") {
			String msLessonMinute = ((String)params.get("msLessonMinute")).replace("분", "");
			params.put("msLessonMinute", msLessonMinute);			
		}
		
		if((String)params.get("msRoundCnt") != null && (String)params.get("msRoundCnt") != "") {		
			String msRoundCnt = ((String)params.get("msRoundCnt")).replace("회", "");
			String msRoundUnit = ("주".equals(msRoundCnt.substring(0,1)))? "01" : "02";
			msRoundCnt = msRoundCnt.substring(1);
			
			params.put("msRoundCnt", msRoundCnt);
			params.put("msRoundUnit", msRoundUnit);
		}
		
		if((String)params.get("msHandcap") != null && (String)params.get("msHandcap") != "") {			
			String msHandcap = ((String)params.get("msHandcap")).replace("개", "");
			params.put("msHandcap", msHandcap);
		}		
		
		MemberVO member = memberMapper.selectMemberBasic((String)params.get("msNum"));
		if(member != null) {
			memberMapper.updateMemberBasic(params);
		} else {
			memberMapper.insertMemberBasic(params);
		}
	}
	
	/**
     * 회원 추가정보 조회
     * 
     * @param msNum
     * @return MemberVO
     */	
	public MemberVO selectMemberBasic(String msNum) {
		return memberMapper.selectMemberBasic(msNum);
	}
	
	/**
     * 회원 차량정보 저장
     * 
     * @param params
     * @return
     */	
	public void saveMemberCar(Map<String, Object> params) {
		
		List<Map<String,Object>> carList = new ArrayList<Map<String,Object>>();
		String msNum = (String) params.get("msNum");
		int i = 0;
		
		if((String)params.get("msCarNo1") != null && (String)params.get("msCarNo1") != "") {
			i += 1;
			Map<String,Object> car = new HashMap<String,Object>();
			car.put("carSeq", i);
			car.put("msNum", msNum);
			car.put("msCarNo", params.get("msCarNo1"));
			carList.add(car);
		}
		if((String)params.get("msCarNo2") != null && (String)params.get("msCarNo2") != "") {
			i += 1;
			Map<String,Object> car = new HashMap<String,Object>();
			car.put("carSeq", i);
			car.put("msNum", msNum);
			car.put("msCarNo", params.get("msCarNo2"));
			carList.add(car);
		}
		if((String)params.get("msCarNo3") != null && (String)params.get("msCarNo3") != "") {
			i += 1;
			Map<String,Object> car = new HashMap<String,Object>();
			car.put("carSeq", i);
			car.put("msNum", msNum);
			car.put("msCarNo", params.get("msCarNo3"));
			carList.add(car);
		}
		
		if(!carList.isEmpty()) {			
			List<MemberVO> result = memberMapper.selectMemberCarList(msNum);
			if(!result.isEmpty()) {
				memberMapper.deleteMemberCar(msNum);
			}
			for(Map<String, Object> item : carList) {				
				memberMapper.insertMemberCar(item);
			}
		} else {
			return;
		}
		
	}
	
	/**
     * 회원 차량정보 리스트 조회
     * 
     * @param msNum
     * @return List<MemberVO>
     */	
	public List<MemberVO> selectMemberCarList(String msNum) {
		return memberMapper.selectMemberCarList(msNum);
	}

	/**
	 * 아이디/전화번호 중복체크, 아이디 찾기 
	 * 
	 * @param params
	 * @return MemberVO
	 */
	public MemberVO selectFindUser(Map<String, Object> params) throws Exception {
		return memberMapper.selectFindUser(params);
	}
	
	/**
	 * 회원 위약체크 
	 * - 회원테이블 > DR_BK_GRANT
	 * 
	 * @param params
	 * @return
	 */
	public String chkMsBkGrant(Map<String, Object> params) {
		return memberMapper.chkMsBkGrant(params);
	}
	
	/**
	 * 비밀번호 초기화
	 * 
	 * @param params
	 * @return
	 */
	public void updatePassword(Map<String, Object> params) throws Exception {
		memberMapper.updatePassword(params);
	}

	/**
	 * 프로 목록 조회
	 * 
	 * @param
	 * @return MemberVO
	 */
	public List<MemberVO> selectProList() {
		return memberMapper.selectProList();
	}
	
	/**
	 * 앰배서더 목록 조회
	 * 
	 * @param
	 * @return MemberVO
	 */
	public List<MemberVO> selectAmbassadorList() {
		return memberMapper.selectAmbassadorList();
	}

	/**
	 * 회원정보에 세션키 등록 (자동 로그인)
	 * 
	 * @param params
	 * @return
	 */
	public void updateMsSessionKey(Map<String, Object> params) {
		memberMapper.updateMsSessionKey(params);
		
	}

	/**
	 * 세션키로 회원정보 조회 (자동 로그인)
	 * 
	 * @param params
	 * @return SessionVO
	 */
	public SessionVO selectSessionLoginUser(Map<String, Object> params) {
		return memberMapper.selectSessionLoginUser(params);
	}

	/**
	 * 로그인 (세션 저장, 자동로그인, 로그 처리)
	 * 
	 * @param req
	 * @param res
	 * @param session
	 * @param params
	 * @return 
	 */
	public void loginUserInfo(HttpServletRequest req, HttpServletResponse res, HttpSession session,
			Map<String, Object> params) throws Exception {
		String msNum = this.selectMsNum(params);
		params.put("msNum", msNum);
		
		// 이전 세션 삭제 (중복 로그인 방지)
		// SessionConfig.getSessionMsNumCheck(msNum);
		
		SessionVO member = this.selectMsSession(params);			
		session.setAttribute("msNum", msNum);
		session.setAttribute("msMember", member);
		session.setMaxInactiveInterval(30 * 60);
		
		// 자동 로그인
		String loginAuto = "";
		if(params.containsKey("loginAuto")) {
			loginAuto = "Y".equals((String) params.get("loginAuto"))? "Y" : "N";
			
			if(loginAuto == "Y") {				
				Cookie loginCookie = new Cookie("sessionKey", session.getId());
				loginCookie.setPath("/");
				loginCookie.setMaxAge(60*60*24*30);
				res.addCookie(loginCookie);
				
				params.put("msSessionKey", session.getId());
				this.updateMsSessionKey(params);
			}
		}
				
		// 로그인 로그 입력
		String ua = null;

		if(params.get("ua") != null) {
			ua = params.get("ua").toString();
		}else {
			ua = req.getHeader("user-agent");
			if (ua.indexOf("Android") != -1) {
				ua = "Android";
			} else if( ua.indexOf("iPad") != -1 || ua.indexOf("iPhone") != -1 || ua.indexOf("iOS") != -1 || ua.indexOf("MAC") != -1 ) {
				ua = "iPhone";
			} else if( ua.indexOf("Windows") != -1) {
				ua = "windows";
			} else {
				ua = req.getHeader("user-agent");
			}
		}

		params.put("platform", "APP");
		params.put("userAgent", ua);
		params.put("loginAuto", loginAuto);
		params.put("inputStaff", "APP");
		params.put("ipAddr", Utils.getClientIpAddress(req));

		this.insertLoginLog(params);
		
	}
	
	/**
	 * 선호업장 저장
	 * 
	 * @param params
	 * @return 
	 */
	public void saveFirstPick(Map<String, Object> params) {
		if((String)params.get("coDiv") != null && (String)params.get("coDiv") != "") {			
			DrMsCoInfo result = drMsCoInfoMapper.selectDrMsCoInfo(params);
			
			DrMsCoInfo drMsCoInfo = new DrMsCoInfo();
			drMsCoInfo.setCoDiv((String)params.get("coDiv"));
			drMsCoInfo.setMsNum((String)params.get("msNum"));
			
			if(result == null) {			
				drMsCoInfoMapper.insertDrMsCoInfo(drMsCoInfo);
			}
			drMsCoInfoService.updateFirstPickY(drMsCoInfo);
		}		
	}
	
	/**
	 * 회원/프로 프로필 이미지 등록
	 * 
	 * @param params
	 * @param mreq
	 * @return 
	 */
	public void uploadProfileImg(Map<String, Object> params, MultipartHttpServletRequest mreq) throws Exception {
		Iterator<String> iter = mreq.getFileNames();	
		while(iter.hasNext()) {
			// 다음 file[n] 값을 Multipartfile 객체로 생성
			MultipartFile mFile = mreq.getFile(iter.next());			
			 
			// mFile의 파일이름 가져옴
			String orgFileNm = mFile.getOriginalFilename();
			String extNm = orgFileNm.substring(orgFileNm.lastIndexOf(".") + 1, orgFileNm.length()).toLowerCase();
			String newFileNm = System.currentTimeMillis() + "." + extNm;
			
			
			File sameFile = new File(orgFileNm);					// 똑같은 이름의 파일 객체 생성 (file_name.jpg)
			String filePath = sameFile.getAbsolutePath();			// 실행 중인 working directory + File에 전달한 경로값 (C:\folder_name\file_name.jpg)
			File tmpFile = new File(filePath);						// 절대경로로 다시 파일 객체 생성
			mFile.transferTo(tmpFile);								// 임시파일 객체에 mFile을 복사하면 해당 경로에 파일이 만들어짐
			
			Path srcPath = Paths.get(filePath);						// String을 Path 객체로 만들어줌
		    String mimeType = Files.probeContentType(srcPath);		// 파일 경로에 있는 Content-Type(파일 유형) 확인
		    mimeType = (mimeType == null ? "" : mimeType);			// 확장자가 없는 경우 null을 반환
			
		    String folderNm = "profile/" + params.get("msNum") + "/";			
		    
		    // AWSFileUtil.uploadFile(folderNm, newFileNm, mFile);	// 생성할 폴더명, 새 파일 이름, 복사될 파일 경로
			AWSFileUtil.uploadFile(folderNm, newFileNm, extNm, tmpFile, mimeType);	// 생성할 폴더명, 새 파일 이름, 복사될 파일, 파일타입
									
			// 업로드 후 임시파일 삭제
			if(tmpFile.exists()) tmpFile.delete();
			
			params.put("msImgName", newFileNm);
			params.put("msImgPath", folderNm);
			memberMapper.insertDrMsPicture(params);
			
		}		
	}	

	public MemberVO selectProfileImg(Map<String, Object> map) {
		return memberMapper.selectDrMsPicture(map);
	}

	public void deleteProfileImg(Map<String, Object> params) {
		String objectName = (String)params.get("msImgPath") + (String)params.get("msImgName");
		AWSFileUtil.deleteFile(objectName);
		AWSFileUtil.deleteThumbnail(objectName);
		
		memberMapper.deleteDrMsPicture(params);		
	}
	
	/**
	 * 회원탈퇴
	 * 
	 * @param session
	 * @return Map
	 */
	public void updateMemberQuit(Map<String, Object> params) {
		memberMapper.updateMemberQuit(params);
		
	}

	public void updateMemberToken(Push push) {
		memberMapper.updateMemberToken(push);
		
	}	

}

package com.yido.clubd.controller;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/")
public class WebController {

	@RequestMapping("")
	public String index(Model model) {
		return "redirect:main";	
	}
	
	/**
	 * 메인페이지
	 * 
	 * @param model
	 * @param assetUser
	 * @param request
	 * @return
	 */
	@RequestMapping("/main")
	public String index(Model model, HttpServletRequest req) {
		return "/index";
	}

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam(required = false) String error, HttpServletRequest req, Model model) throws IOException {
     
        String referrer = req.getHeader("Referer");
		log.debug("referrer - {}", referrer);
	
        Cookie[] cookies = req.getCookies();
    	if(cookies != null) {
    		for(Cookie cookie : cookies) {
    			model.addAttribute(cookie.getName(), cookie.getValue());
        	}
    	}

        log.debug("ip1 - {}", req.getRemoteAddr());
        return "/member/login";
    }  
    
    @RequestMapping("/succ-logout")
	public String successLogout(Model model, Principal principal, HttpServletRequest req, HttpServletResponse res) {

		//세션 invalidate
    	req.getSession().invalidate();
		
		//delete cookie
		Cookie[] cookies = req.getCookies();
    	if(cookies != null) {
    		for(Cookie cookie : cookies) {
    			System.out.println("delete cookie = " + cookie.getName());
    			cookie.setMaxAge(0);
    			cookie.setValue(null);
    			res.addCookie(cookie);    			
        	}
    	}    	
    	return "/index";
	}


	/**
	 * 공지사항 목록
	 * 
	 * @param model
	 * @param req
	 * @return
	 */
	@RequestMapping("/noticeList")  
	public String noticeList(Model model, HttpServletRequest req) {
		return "/notice/noticeList";
	}

	/**
	 * 공지사항 상세
	 * 
	 * @param model
	 * @param req
	 * @return
	 */
	@RequestMapping("/noticeView")  
	public String noticeView(Model model, HttpServletRequest req) {
		return "/notice/noticeView";
	}

	/**
	 * 이벤트 목록
	 * 
	 * @param model
	 * @param req
	 * @return
	 */
	@RequestMapping("/event")  
	public String event(Model model, HttpServletRequest req) {
		return "/event";
	}

	/**
	 * 시설안내
	 * 
	 * @param model
	 * @param req
	 * @return
	 */
	@RequestMapping("/facility")  
	public String facility(Model model, HttpServletRequest req) {
		return "/facility";
	}
	
	@RequestMapping("/bay1")  
	public String bay1(Model model, HttpServletRequest req) {
		return "/bay/bay1";
	}
	
	@RequestMapping("/bay2")  
	public String bay2(Model model, HttpServletRequest req) {
		return "/bay/bay2";
	}
	
	@RequestMapping("/bay3")  
	public String bay3(Model model, HttpServletRequest req) {
		return "/bay/bay3";
	}
	
	@RequestMapping("/bay4")  
	public String bay4(Model model, HttpServletRequest req) {
		return "/bay/bay4";
	}
	
	@RequestMapping("/bay5")  
	public String bay5(Model model, HttpServletRequest req) {
		return "/bay/bay5";
	}
}
 	
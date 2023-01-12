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
	public String index(Model model, HttpServletRequest request) {
		return "/index";
	}

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam(required = false) String error, HttpServletRequest request, Model model) throws IOException {
     
        String referrer = request.getHeader("Referer");
		log.debug("referrer - {}", referrer);
	
        Cookie[] cs = request.getCookies();
    	if(cs != null) {
    		for(Cookie c : cs) {
    			model.addAttribute(c.getName(), c.getValue());
        	}
    	}

        log.debug("ip1 - {}", request.getRemoteAddr());
        return "/member/login";
    }
    
    @RequestMapping("/succ-logout")
	public String successLogout(Model model, Principal principal, HttpServletRequest request, HttpServletResponse response) {

		//세션 invalidate
		request.getSession().invalidate();
		
		//delete cookie
		Cookie[] cs = request.getCookies();
    	if(cs != null) {
    		for(Cookie c : cs) {
    			System.out.println("delete cookie = " + c.getName());
    			c.setMaxAge(0);
    			c.setValue(null);
    			response.addCookie(c);    			
        	}
    	}    	
    	
    	return "/member/login";
	}

}
 	
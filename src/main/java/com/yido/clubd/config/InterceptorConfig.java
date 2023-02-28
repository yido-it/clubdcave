package com.yido.clubd.config;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.yido.clubd.common.utils.SessionVO;
import com.yido.clubd.common.utils.Utils;
import com.yido.clubd.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Component
@Slf4j
public class InterceptorConfig extends HandlerInterceptorAdapter {
	
	public String[] loginEssential = {
			  "/member/memberModify/**"
			, "/pro/proGallery/**"
			, "/pro/proForm/**"
			, "/book/**"
			};
	
	public String[] loginInessential = {
			  "/"
			, "/main"
			, "/login/**"
			};
	
	@Autowired
	private MemberService drMsMaininfoService;
	
	@Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		
        HttpSession session = req.getSession();
        
		SessionVO sessionVO = (SessionVO) session.getAttribute("msMember");
		if(sessionVO != null) {			
			log.info("=============>" + sessionVO);
			return true;
			
		} else {
			Cookie loginCookie = WebUtils.getCookie(req, "sessionKey");
			if (loginCookie != null) {
				Map<String, Object> params = new HashMap<String, Object>();			
				params.put("msSessionKey", loginCookie.getValue());
				SessionVO member = drMsMaininfoService.selectSessionLoginUser(params);
				
				String ua = null;
				if(params.get("ua") != null) {
					ua = params.get("ua").toString();
				} else {
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
				if (member != null) {
					session.setAttribute("msMember", member);

					params.put("msNum",member.getMsNum());
					params.put("platform", "APP");
					params.put("loginAuto", "Y");
					params.put("userAgent", ua);
					params.put("inputStaff", "APP");
					params.put("ipAddr", Utils.getClientIpAddress(req));

					drMsMaininfoService.insertLoginLog(params);
				}
			return true;
			
			} else {
				String destUri = req.getRequestURI(); 
				String destQuery = req.getQueryString();
				String dest = (destQuery == null) ? destUri : destUri+"?"+destQuery;
				req.getSession().setAttribute("dest", dest);
				 
				res.sendRedirect("/login");
				return false;
			}
			// 로그인 정보 없음
			
		}		
    }
	
	@Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.debug("==================== END ======================");
        log.debug("===============================================");
    }
	

}

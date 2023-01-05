package com.yido.clubd.security;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.yido.clubd.service.DrMsMaininfoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler  {

	@Autowired
	private DrMsMaininfoService userService;
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	public MyAuthenticationSuccessHandler(String defaultTargetUrl) {
        setDefaultTargetUrl(defaultTargetUrl);
    }
	
//	@Override
//	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//		HttpSession session = request.getSession();
//		if (session != null) {
//            String redirectUrl = (String) session.getAttribute("prevPage");
//            if (redirectUrl != null) {
//                session.removeAttribute("prevPage");
//                getRedirectStrategy().sendRedirect(request, response, redirectUrl);
//            } else {
//                super.onAuthenticationSuccess(request, response, authentication);
//            }
//        } else {
//            super.onAuthenticationSuccess(request, response, authentication);
//        }
//        clearAuthenticationAttributes(request);
//			
//	}

	//protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
		//
  //  }
//    protected String determineTargetUrl(Authentication authentication) {
//
//        String auth = "";
//
//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//        
//        for (GrantedAuthority grantedAuthority : authorities) {
//        	auth = grantedAuthority.getAuthority();
//        }
//        
//        return "/";
//    }
 
//    protected void clearAuthenticationAttributes(HttpServletRequest request) {
//        HttpSession session = request.getSession(false);
//        if (session == null) {
//            return;
//        }
//        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
//    }
 
    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }
}

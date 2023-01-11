package com.yido.clubd.config;

import java.net.InetAddress;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.yido.clubd.common.utils.SessionVO;
import com.yido.clubd.model.DrMsMaininfo;
import com.yido.clubd.service.DrMsMaininfoService;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Component
@Slf4j
public class InterceptorConfig extends HandlerInterceptorAdapter{

	@Autowired 
	private DrMsMaininfoService drMsMaininfoService;
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if(auth != null && request.getSession().getAttribute("session") == null) {

			SessionVO session = new SessionVO();

			log.debug(InetAddress.getLocalHost().getHostAddress());
			
			/*
			DrMsMaininfo user = drMsMaininfoService.selectOneUser(auth.getName());
		
			session.setMsId(user.getMsId());
			session.setMsName(user.getMsName());
			
			request.getSession().setAttribute("session",  session);
			*/
		
		}
		
		SessionVO session = new SessionVO();
		session.setMsLevel("11");
		session.setMsNum("123456789123");
		session.setMsId("baeean");
		session.setMsName("배은화");
		session.setMsEmail("baeean@naver.com");
		session.setMsPhone("010-1234-5678");
		request.getSession().setAttribute("session",  session);
		
		
        return true;
    }

}

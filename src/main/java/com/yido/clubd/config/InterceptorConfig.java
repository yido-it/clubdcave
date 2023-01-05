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
			
			DrMsMaininfo user = drMsMaininfoService.selectOneUser(auth.getName());
		
			session.setMsId(user.getMsId());
			session.setMsName(user.getMsName());
			
			request.getSession().setAttribute("session",  session);
		
		}
		
        return true;
    }

}

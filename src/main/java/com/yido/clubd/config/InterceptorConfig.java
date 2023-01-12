package com.yido.clubd.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.yido.clubd.common.utils.SessionVO;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Component
@Slf4j
public class InterceptorConfig extends HandlerInterceptorAdapter{

	@Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse response, Object handler) throws Exception {
		/*
        HttpSession session = req.getSession();
		SessionVO sessionVO = (SessionVO) session.getAttribute("msMember");
		log.info("=============>" + sessionVO);
		 */
        return true;
    }

}

package com.turkcell.playcell.gamingplatform.cms.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RequestResponseInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        StringBuilder log;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null){
            String userName;
            try{
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                userName = userDetails.getUsername();
            }catch(RuntimeException e) {
                userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
            }
            log = new StringBuilder("User: ").append(userName).append(" - URI: ").append(request.getMethod()).append(" ").append(request.getRequestURI());
        }else{
            log = new StringBuilder("URI: ").append(request.getMethod()).append(" ").append(request.getRequestURI());
        }
        logger.info(log.toString());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        StringBuilder log;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null){
            String userName;
            try{
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                userName = userDetails.getUsername();
            }catch(RuntimeException e) {
                userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
            }
            log = new StringBuilder("User: ").append(userName).append(" - URI: ").append(request.getMethod()).append(" ").append(request.getRequestURI()).append(" - Status: ").append(response.getStatus());
        }else{
            log = new StringBuilder("URI: ").append(request.getMethod()).append(" ").append(request.getRequestURI()).append(" - Status: ").append(response.getStatus());
        }
        logger.info(log.toString());
    }

}

package com.ourlayer.interceptor;

import com.ourlayer.service.settings.SeasonService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Log
@Component
public class SeasonInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private SeasonService seasonService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(true);

        if ( session.getAttribute("season") == null ) {
            log.info("SEASON SETTING...");
            session.setAttribute("season", seasonService.getCurrSeason());
        }

        return super.preHandle(request, response, handler);
    }
}

package com.dubbo.interceptor;

import com.dubbo.common.util.IdUtil;
import com.dubbo.common.util.ValueHolder;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class SessionIdInterceptor implements HandlerInterceptor {

    private final static String SESSION_KEY = "sessionId";

    @Inject
    private ValueHolder valueHolder;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String sessionValue = "session_id_" + IdUtil.getID();
        MDC.put(SESSION_KEY, sessionValue);
        valueHolder.setSessionIdHolder(sessionValue);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        MDC.remove(SESSION_KEY);
        valueHolder.removeSessionId();
    }
}
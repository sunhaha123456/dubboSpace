package com.dubbo.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.inject.Singleton;

@Slf4j
@Singleton
@Component
public class ValueHolder {

    private static final ThreadLocal<String> userIdHolder = new ThreadLocal<>();

    private static final ThreadLocal<String> tokenHolder = new ThreadLocal<>();

    private static final ThreadLocal<String> sessionIdHolder = new ThreadLocal<>();

    public void setUserIdHolder(String userId) {
        userIdHolder.set(userId);
    }

    public String getUserIdHolder() {
        return userIdHolder.get();
    }

    public void setTokenHolder(String token) {
        tokenHolder.set(token);
    }

    public String getTokenHolder() {
        return tokenHolder.get();
    }

    public void setSessionIdHolder(String sessionId) {
        sessionIdHolder.set(sessionId);
    }

    public String getSessionIdHolder() {
        return sessionIdHolder.get();
    }

    public void removeSessionId() {
        sessionIdHolder.remove();
    }

    public void removeAll() {
        userIdHolder.remove();
        tokenHolder.remove();
        sessionIdHolder.remove();
    }
}
package com.dubbo.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.inject.Singleton;

@Slf4j
@Singleton
@Component
public class ValueHolder {

    private static final ThreadLocal<Long> userIdHolder = new ThreadLocal<>();

    private static final ThreadLocal<String> tokenHolder = new ThreadLocal<>();

    private static final ThreadLocal<String> sessionIdHolder = new ThreadLocal<>();

    public void setUserIdHolder(Long userId) {
        userIdHolder.set(userId);
    }

    public Long getUserIdHolder() {
        return userIdHolder.get();
    }

    public void setTokenHolder(String token) {
        tokenHolder.set(token);
    }

    public String getTokenHolder() {
        return tokenHolder.get();
    }

    public void setSessionIdHolder() {
        sessionIdHolder.set("|" + IdUtil.getID() + "|");
    }

    public String getSessionIdHolder() {
        return sessionIdHolder.get();
    }

    public void removeAll() {
        userIdHolder.remove();
        tokenHolder.remove();
    }

    public void removeSessioId() {
        sessionIdHolder.remove();
    }
}
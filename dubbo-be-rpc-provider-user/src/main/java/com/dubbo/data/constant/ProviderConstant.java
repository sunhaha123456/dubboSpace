package com.dubbo.data.constant;

public interface ProviderConstant {
    // redis中token保存时间长度
    long TOKEN_SAVE_TIME = 8 * 60;

    // redis中用户前缀
    String LOGIN_USER_PREFIX = "dubbo_login_user_";
}
package com.dubbo.rpc.data.constant;

public interface RpcConstant {

    // 用户登录验证码保存时间 3分钟
    long LOGIN_CODE_SAVE_TIME = 3;

    // redis中登录验证码前缀
    String LOGIN_CODE_PREFIX = "dubbo_login_code_";
}
package com.dubbo.util;

import com.dubbo.common.util.StringUtil;
import com.dubbo.data.constant.ConsumerConstant;
import com.dubbo.rpc.data.dto.TokenValidateDto;

import javax.servlet.http.HttpServletRequest;

public class TokenValidateUtil {

    public static TokenValidateDto tokenValidate(HttpServletRequest request) {
        String token = request.getHeader(ConsumerConstant.SYSTEM_TOKEN_NAME);
        String userId = request.getHeader(ConsumerConstant.SYSTEM_USER_ID);
        if (StringUtil.isEmpty(token)) {
            token = request.getParameter(ConsumerConstant.SYSTEM_TOKEN_NAME);
        }
        if (StringUtil.isEmpty(userId)) {
            userId = request.getParameter(ConsumerConstant.SYSTEM_USER_ID);
        }
        String method = request.getMethod();
        String url = request.getRequestURI().toUpperCase();
        return new TokenValidateDto(token, userId, method, url);
    }
}
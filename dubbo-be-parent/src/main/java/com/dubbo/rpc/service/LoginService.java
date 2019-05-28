package com.dubbo.rpc.service;

import com.dubbo.rpc.data.dto.TokenValidateDto;
import com.dubbo.rpc.data.dto.UserInfoDto;
import com.dubbo.rpc.data.dto.UserLoginDto;

/**
 * 功能：登录 service
 * @author sunpeng
 * @date 2019
 */
public interface LoginService {

    String toSuccess(TokenValidateDto dto) throws Exception;

    /**
     * 功能：登出
     * @throws Exception
     */
    void out(TokenValidateDto dto) throws Exception;

    /**
     * 功能：登录
     * @param param
     * @return
     * @throws Exception
     */
    UserInfoDto verify(UserLoginDto param) throws Exception;

    /**
     * 功能：token校验，若通过校验，并更新token有效时长
     * @return true：通过校验，false：未通过校验
     */
    boolean tokenValidate(TokenValidateDto dto);
}
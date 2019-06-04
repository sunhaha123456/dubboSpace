package com.dubbo.interceptor;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.dubbo.common.data.response.ResponseResult;
import com.dubbo.common.data.response.ResponseResultCode;
import com.dubbo.common.util.ValueHolder;
import com.dubbo.rpc.data.dto.TokenValidateDto;
import com.dubbo.rpc.service.LoginService;
import com.dubbo.util.TokenValidateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 功能：登录拦截器
 * @author sunpeng
 * @date 2019
 */
@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Reference(check = false)
    private LoginService loginService;

    @Inject
    private ValueHolder valueHolder;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        TokenValidateDto dto = TokenValidateUtil.tokenValidate(request);
        if (!loginService.tokenValidate(dto)) {
            getFail(response);
            return false;
        }
        valueHolder.setUserIdHolder(dto.getUserId());
        valueHolder.setTokenHolder(dto.getToken());
        log.info("请求---url:{}--userId：{}---token：{}", dto.getUrl(), dto.getUserId(), dto.getToken());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    // 设置返回的失败信息
    private void getFail(HttpServletResponse response) {
        //将实体对象转换为JSON Object转换
        String json = JSONObject.toJSONString(ResponseResult.build(ResponseResultCode.LOGIN_FIRST));
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(json);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
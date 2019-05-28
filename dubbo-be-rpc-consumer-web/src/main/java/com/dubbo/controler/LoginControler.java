package com.dubbo.controler;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dubbo.common.util.StringUtil;
import com.dubbo.rpc.data.dto.TokenValidateDto;
import com.dubbo.rpc.data.dto.UserInfoDto;
import com.dubbo.rpc.data.dto.UserLoginDto;
import com.dubbo.rpc.service.LoginService;
import com.dubbo.util.TokenValidateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 功能：登录 controller
 * @author sunpeng
 * @date 2019
 */
@Slf4j
@Controller
@RequestMapping("/login")
public class LoginControler {

    @Reference(check = false)
    private LoginService loginService;

    @GetMapping(value = "/toLogin")
    public String toLogin() {
        return "login";
    }

    @GetMapping(value = "/toSuccess")
    public String toSuccess(HttpServletRequest request) throws Exception {
        TokenValidateDto dto = TokenValidateUtil.tokenValidate(request);
        String uanme = loginService.toSuccess(dto);
        if (StringUtil.isNotEmpty(uanme)) {
            request.setAttribute("uname", uanme);
            return "home";
        }
        return "login";
    }

    @GetMapping(value = "/out")
    public String out(HttpServletRequest request) throws Exception {
        TokenValidateDto dto = TokenValidateUtil.tokenValidate(request);
        loginService.out(dto);
        return "login";
    }

    @ResponseBody
    @PostMapping(value = "/verify")
    public UserInfoDto verify(@RequestBody @Validated(UserLoginDto.BaseInfo.class) UserLoginDto param) throws Exception {
        return loginService.verify(param);
    }
}
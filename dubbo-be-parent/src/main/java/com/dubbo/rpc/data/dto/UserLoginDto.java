package com.dubbo.rpc.data.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class UserLoginDto implements Serializable {

    // 用户名
    @NotBlank(message = "用户名不能为空！", groups = BaseInfo.class)
    private String uname;

    // 密码
    @NotBlank(message = "密码不能为空！", groups = BaseInfo.class)
    private String upwd;

    // 验证码 redis key
    @NotBlank(message = "验证码不能为空！", groups = BaseInfo.class)
    private String key;

    // 验证码
    @NotBlank(message = "验证码不能为空！", groups = BaseInfo.class)
    private String code;

    public interface BaseInfo {}
}
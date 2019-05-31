package com.dubbo.rpc.data.dto;

import lombok.Data;

@Data
public class TokenValidateDto extends BaseDto {
    private String tokenValidate;
    private String userIdValidate;
    private String method;
    private String url;

    public TokenValidateDto() {
    }

    public TokenValidateDto(String tokenValidate, String userIdValidate, String method, String url) {
        this.tokenValidate = tokenValidate;
        this.userIdValidate = userIdValidate;
        this.method = method;
        this.url = url;
    }
}
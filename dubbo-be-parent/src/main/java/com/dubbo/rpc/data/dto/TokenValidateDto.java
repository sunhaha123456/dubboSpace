package com.dubbo.rpc.data.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class TokenValidateDto implements Serializable {
    private String token;
    private String userId;
    private String method;
    private String url;

    public TokenValidateDto() {
    }

    public TokenValidateDto(String token, String userId, String method, String url) {
        this.token = token;
        this.userId = userId;
        this.method = method;
        this.url = url;
    }
}
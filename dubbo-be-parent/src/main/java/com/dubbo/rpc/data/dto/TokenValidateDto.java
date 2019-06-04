package com.dubbo.rpc.data.dto;

import lombok.Data;

@Data
public class TokenValidateDto extends BaseDto {
    private String method;
    private String url;

    public TokenValidateDto() {
    }

    public TokenValidateDto(String token, String userId, String method, String url) {
        super(token, userId);
        this.method = method;
        this.url = url;
    }
}
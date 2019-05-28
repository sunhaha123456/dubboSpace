package com.dubbo.rpc.data.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfoDto implements Serializable {
    private String token;
    private String userId;

    public UserInfoDto() {
    }

    public UserInfoDto(String token, String userId) {
        this.token = token;
        this.userId = userId;
    }
}
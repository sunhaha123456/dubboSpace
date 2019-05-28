package com.dubbo.rpc.data.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseUserDto implements Serializable{
    protected String token;
    protected Long userId;

    public BaseUserDto() {
    }

    public BaseUserDto(String token, Long userId) {
        this.token = token;
        this.userId = userId;
    }
}
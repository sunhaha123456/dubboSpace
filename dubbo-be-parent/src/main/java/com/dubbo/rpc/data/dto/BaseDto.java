package com.dubbo.rpc.data.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseDto implements Serializable{
    protected String dtoToken;
    protected Long dtoUserId;
    protected String dtoSessionId;

    public BaseDto() {
    }

    public BaseDto(String dtoToken, Long dtoUserId) {
        this.dtoToken = dtoToken;
        this.dtoUserId = dtoUserId;
    }

    public BaseDto(String dtoToken, Long dtoUserId, String dtoSessionId) {
        this.dtoToken = dtoToken;
        this.dtoUserId = dtoUserId;
        this.dtoSessionId = dtoSessionId;
    }
}
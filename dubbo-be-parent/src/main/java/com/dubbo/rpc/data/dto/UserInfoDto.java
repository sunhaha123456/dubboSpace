package com.dubbo.rpc.data.dto;

import lombok.Data;

@Data
public class UserInfoDto extends BaseDto {

    public UserInfoDto() {
    }

    public UserInfoDto(String token, String userId) {
        super(token, userId);
    }
}
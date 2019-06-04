package com.dubbo.rpc.data.dto;

import lombok.Data;

@Data
public class UserInfoDto extends BaseDto {

    public UserInfoDto() {
    }

    public UserInfoDto(String token, Long userId) {
        super(token, userId);
    }
}
package com.dubbo.service.local.impl;

import com.dubbo.common.dao.redis.RedisRepositoryCustom;
import com.dubbo.common.util.JsonUtil;
import com.dubbo.data.constant.ProviderConstant;
import com.dubbo.data.vo.UserRedisVo;
import com.dubbo.service.local.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Inject
    private RedisRepositoryCustom redisRepositoryCustom;

    @Override
    public void userRedisInfoSave(String redisKey, UserRedisVo userRedis) {
        redisRepositoryCustom.saveMinutes(redisKey, JsonUtil.objectToJson(userRedis), ProviderConstant.TOKEN_SAVE_TIME);
    }
}
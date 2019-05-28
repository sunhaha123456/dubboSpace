package com.dubbo.service.local;

import com.dubbo.data.vo.UserRedisVo;

/**
 * 功能：user service
 * @author sunpeng
 * @date 2019
 */
public interface UserService {
    void userRedisInfoSave(String redisKey, UserRedisVo userRedis);
}
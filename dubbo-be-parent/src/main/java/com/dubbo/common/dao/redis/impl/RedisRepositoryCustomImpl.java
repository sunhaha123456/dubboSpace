package com.dubbo.common.dao.redis.impl;

import com.dubbo.common.dao.redis.RedisRepositoryCustom;
import com.dubbo.common.util.JsonUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.concurrent.TimeUnit;

@Repository
public class RedisRepositoryCustomImpl implements RedisRepositoryCustom {

    @Inject
    StringRedisTemplate template;

    @Override
    public void save(String key, String value) {
        ValueOperations<String, String> ops = template.opsForValue();
        ops.set(key, value);
    }

    @Override
    public void saveMinutes(String key, String value, long time) {
        ValueOperations<String, String> ops = template.opsForValue();
        ops.set(key, value, time, TimeUnit.MINUTES);
    }

    @Override
    public void saveDays(String key, String value, long time) {
        ValueOperations<String, String> ops = template.opsForValue();
        ops.set(key, value, time, TimeUnit.DAYS);
    }

    @Override
    public void expireMinutes(String key, long time) {
        template.expire(key, time, TimeUnit.MINUTES);
    }

    @Override
    public void expireDays(String key, long time) {
        template.expire(key, time, TimeUnit.DAYS);
    }

    @Override
    public <T> T getClassObj(String key, Class<T> clazz) {
        String value = getString(key);
        T t = null;
        if (value != null && value.length() > 0) {
            t = JsonUtil.jsonToObject(value, clazz);
        }
        return t;
    }

    @Override
    public String getString(String key) {
        ValueOperations<String, String> ops = template.opsForValue();
        String value = ops.get(key);
        return value;
    }

    @Override
    public void delete(String key) {
        template.delete(key);
    }
}
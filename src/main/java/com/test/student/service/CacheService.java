package com.test.student.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class CacheService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // 从缓存拿数据
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    // 放入缓存，设置过期时间（秒）
    public void set(String key, Object value, long ttlSeconds) {
        redisTemplate.opsForValue().set(key, value, ttlSeconds, TimeUnit.SECONDS);
    }

    // 删除缓存（数据更新时调用）
    public void delete(String key) {
        redisTemplate.delete(key);
    }
}

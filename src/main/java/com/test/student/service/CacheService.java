package com.test.student.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;  // 注意泛型

    // 存数据
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    // 取数据
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    // 删数据
    public void delete(String key) {
        redisTemplate.delete(key);
    }
}
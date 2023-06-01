package com.moulh.pg.core.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName : RedisService
 * @Author : moulh@paxsz.com
 * @Date : 2021-07-11 11:16
 * @Version : V1.0
 * @Description : 缓存配置
 */
@Service
@Slf4j
public class RedisService {
    /**
     * 过期时长
     */
    private final Long key_duration = 24 * 60 * 60 * 1000L;

    @Resource
    private RedisTemplate redisTemplate;

    private ValueOperations<String, String> valueOperations;

    @PostConstruct
    public void init() {
        RedisSerializer redisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        redisTemplate.setValueSerializer(redisSerializer);
        redisTemplate.setHashKeySerializer(redisSerializer);
        redisTemplate.setHashValueSerializer(redisSerializer);
        valueOperations = redisTemplate.opsForValue();
    }

    public void set(String key, String value) {
        valueOperations.set(key, value, key_duration, TimeUnit.MILLISECONDS);
        log.info("key={}, value is: {} into redis cache", key, value);
    }

    public String get(String key) {
        String redisValue = valueOperations.get(key);
        log.info("get from redis, value is: {}", redisValue);
        return redisValue;
    }

    public boolean delete(String key) {
        boolean result = redisTemplate.delete(key);
        log.info("delete from redis, key is: {}", key);
        return result;
    }

    public Long getExpireTime(String key) {
        return valueOperations.getOperations().getExpire(key);
    }
}

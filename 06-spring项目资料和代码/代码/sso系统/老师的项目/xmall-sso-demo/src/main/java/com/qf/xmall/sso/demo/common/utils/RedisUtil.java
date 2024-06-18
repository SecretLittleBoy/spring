package com.qf.xmall.sso.demo.common.utils;

import com.qf.xmall.sso.demo.common.dto.LoginDTO;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisUtil {

    /**
     * 保存键值对
     * @param redisTemplate
     * @param redisKey
     * @param loginDTO
     */
    public static void save(RedisTemplate redisTemplate, String redisKey, LoginDTO loginDTO) {
        redisTemplate.opsForValue().set(redisKey,loginDTO);
    }

    public static Object get(RedisTemplate redisTemplate, String id) {
        return redisTemplate.opsForValue().get(id);
    }

    public static void delete(RedisTemplate redisTemplate, String redisKey) {
        redisTemplate.delete(redisKey);
    }
}

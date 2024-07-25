package com.qf.xmall.cart.demo.common.util;

public class RedisUtil {
    /**
     * 生成redis的key
     */
    public static String crtRedisKey(String pre, String content) {
        return new StringBuilder().append(pre).append(content).toString();
    }

}

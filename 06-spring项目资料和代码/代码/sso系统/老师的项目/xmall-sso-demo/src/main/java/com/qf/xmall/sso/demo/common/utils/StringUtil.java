package com.qf.xmall.sso.demo.common.utils;

public class StringUtil {


    /**
     * 连接字符串
     * @param loginTokenPre
     * @param token
     * @return
     */
    public static String contact(String loginTokenPre, String token) {
        StringBuffer stringBuffer = new StringBuffer(loginTokenPre);
        return stringBuffer.append(token).toString();
    }
}

package com.qf.xmall.cart.demo.common.sso;

import com.alibaba.fastjson.JSON;
import com.qf.xmall.cart.demo.common.constant.URLConstant;
import com.qf.xmall.cart.demo.common.result.ResultModel;
import com.qf.xmall.cart.demo.common.util.HttpClientUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 发送Http请求访问SSO模块
 */
@Service
public class LoginService {

    public ResultModel checkLogin(String token) {
        Map<String, String> param = new HashMap<>();
        param.put("token", token);
        String result = HttpClientUtil.doGet(URLConstant.SSO_CHECKLOGIN_URL, param);
        return JSON.parseObject(result, ResultModel.class);
    }
}

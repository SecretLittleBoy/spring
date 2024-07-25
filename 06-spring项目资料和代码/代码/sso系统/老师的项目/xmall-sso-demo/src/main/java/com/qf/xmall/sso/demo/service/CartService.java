package com.qf.xmall.sso.demo.service;

import com.alibaba.fastjson.JSON;
import com.qf.xmall.sso.demo.common.constant.URLConstant;
import com.qf.xmall.sso.demo.common.request.CartMergeRequest;
import com.qf.xmall.sso.demo.common.result.ResultModel;
import com.qf.xmall.sso.demo.common.utils.HttpClientUtil;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    public ResultModel merge(CartMergeRequest cartMergeRequest) {
        String json = JSON.toJSONString(cartMergeRequest);
        String result = HttpClientUtil.doPostJson(URLConstant.CART_MERGE_URL, json);
        ResultModel resultModel = JSON.parseObject(result, ResultModel.class);
        return resultModel;
    }
}

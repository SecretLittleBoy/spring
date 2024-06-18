package com.qf.xmall.cart.demo.service;

import com.qf.xmall.cart.demo.common.po.CartItemPO;
import com.qf.xmall.cart.demo.common.request.cart.CartMergeRequest;
import com.qf.xmall.cart.demo.common.request.cart.CartModelRequest;
import com.qf.xmall.cart.demo.common.result.ResultModel;

import java.util.List;

public interface ICartService {

    /**
     * 未登录状态下添加购物车
     * @param request
     * @return
     */
    ResultModel<String> addUnLoginCart(CartModelRequest request);

    ResultModel<List<CartItemPO>> details(String token);

    ResultModel update(CartModelRequest cartModelRequest);

    ResultModel<String> addLoginCart(CartModelRequest cartModelRequest);


    ResultModel merge(CartMergeRequest cartMergeRequest);
}

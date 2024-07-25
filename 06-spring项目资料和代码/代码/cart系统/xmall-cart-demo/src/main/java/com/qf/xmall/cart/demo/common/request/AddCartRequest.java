package com.qf.xmall.cart.demo.common.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 封装前端请求过来的购物车数据
 */
@Data
public class AddCartRequest implements Serializable {

    private String token;// 如果是已经登陆，登陆完后会生成jwt，那么上游就需要封装这个token

    private Long pid;// 商品id

    private int count;// 商品数量

}

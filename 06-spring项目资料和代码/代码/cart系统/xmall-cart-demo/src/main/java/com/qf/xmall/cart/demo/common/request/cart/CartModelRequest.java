package com.qf.xmall.cart.demo.common.request.cart;

import lombok.Data;

import java.io.Serializable;

@Data
public class CartModelRequest implements Serializable {

    private String token;//已经登陆后获取的login_token，或者是未登陆状态下的cart_token

    private Long pid;

    private int count;
}

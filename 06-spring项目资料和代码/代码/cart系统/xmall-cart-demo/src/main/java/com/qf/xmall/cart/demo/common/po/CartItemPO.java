package com.qf.xmall.cart.demo.common.po;

import lombok.Data;

import java.io.Serializable;

@Data
public class CartItemPO extends TbItemPO implements Serializable {

    private int count;


}

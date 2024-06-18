package com.qf.xmall.sso.demo.common.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class CartMergeRequest implements Serializable {

    private static final long serialVersionUID = 42L;

    private String loginToken;
    private String cartToken;
}

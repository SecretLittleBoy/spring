package com.qf.xmall.cart.demo.common.request.sso;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginModelRequest implements Serializable {

    private String userName;

    private String userPwd;

    private String challenge;

    private String validate;

    private String seccode;

    private String statusKey;

}

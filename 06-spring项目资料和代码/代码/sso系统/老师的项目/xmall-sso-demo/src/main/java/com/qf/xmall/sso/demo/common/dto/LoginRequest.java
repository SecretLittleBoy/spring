package com.qf.xmall.sso.demo.common.dto;

import lombok.Data;

@Data
public class LoginRequest {

//    private String username;
//    private String password;

    private String userName;

    private String userPwd;

    private String challenge;

    private String validate;

    private String seccode;

    private String statusKey;
}

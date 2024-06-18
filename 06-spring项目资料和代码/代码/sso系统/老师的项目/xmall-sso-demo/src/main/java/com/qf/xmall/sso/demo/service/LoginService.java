package com.qf.xmall.sso.demo.service;

import com.qf.xmall.sso.demo.common.dto.LoginModelRequest;
import com.qf.xmall.sso.demo.common.dto.LoginResponse;
import com.qf.xmall.sso.demo.common.result.ResultModel;

public interface LoginService {

    ResultModel<LoginResponse> login(LoginModelRequest loginModelRequest);

    ResultModel<LoginResponse> checkLogin(String token);

    ResultModel logout(String token);
}

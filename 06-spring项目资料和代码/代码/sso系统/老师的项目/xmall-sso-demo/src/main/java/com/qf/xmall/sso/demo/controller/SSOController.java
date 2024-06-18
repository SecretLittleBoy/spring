package com.qf.xmall.sso.demo.controller;

import com.alibaba.druid.util.StringUtils;
import com.qf.xmall.sso.demo.common.constant.CookieConstant;
import com.qf.xmall.sso.demo.common.dto.LoginModelRequest;
import com.qf.xmall.sso.demo.common.dto.LoginRequest;
import com.qf.xmall.sso.demo.common.dto.LoginResponse;
import com.qf.xmall.sso.demo.common.exception.SSOException;
import com.qf.xmall.sso.demo.common.request.CartMergeRequest;
import com.qf.xmall.sso.demo.common.result.ResultModel;
import com.qf.xmall.sso.demo.service.CartService;
import com.qf.xmall.sso.demo.service.LoginService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * SSO单点登录系统
 * @Author: 索尔
 */
@RestController
@RequestMapping("/sso")
//解决跨域问题
@CrossOrigin
public class SSOController {


    @Autowired
    private LoginService loginService;

    @Autowired
    private CartService cartService;

    @PostMapping("/login")
    public ResultModel<LoginResponse> login(@RequestBody LoginRequest request,
                                            HttpServletRequest servletRequest,
                                            HttpServletResponse response){

        //校验请求参数
        if(Objects.isNull(request) || StringUtils.isEmpty(request.getUserName())){
            try {
                SSOException ssoException = new SSOException("参数不正确，请检查");
                throw ssoException;
            } catch (Exception e) {
                return ResultModel.error(e.getMessage());
            }
        }
        //执行登录业务
        //把LoginRequest转换成LoginModelRequest对象
        LoginModelRequest loginModelRequest = new LoginModelRequest();
        BeanUtils.copyProperties(request,loginModelRequest);
        ResultModel<LoginResponse> resultModel = loginService.login(loginModelRequest);

        //购物车合并
        CartMergeRequest cartMergeRequest = new CartMergeRequest();
        //封装loginToken
        cartMergeRequest.setLoginToken(resultModel.getResult().getToken());
        //封装cart_token
        Cookie[] cookies = servletRequest.getCookies();
        if(cookies!=null){
            for (Cookie cookie : cookies) {
                if(CookieConstant.CART_TOKEN.equals(cookie.getName())){
                    String cart_token = cookie.getValue();
                    cartMergeRequest.setCartToken(cart_token);
                    break;
                }
            }
        }
        //调用购物车的合并接口
        cartService.merge(cartMergeRequest);
        //合并后删除cart_token这个cookie
        Cookie cookie = new Cookie(CookieConstant.CART_TOKEN,"");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return resultModel;
    }

    @GetMapping("/checkLogin")
    public ResultModel<LoginResponse> checkLogin(String token){
        //验证参数是否为空
        if(StringUtils.isEmpty(token)){
            try {
                throw new SSOException("token参数不正确，请检查");
            } catch (SSOException e) {
                return ResultModel.error(e.getMessage());
            }
        }
        return loginService.checkLogin(token);
    }

    @GetMapping("/loginOut")
    public ResultModel logout(String token){
        if(StringUtils.isEmpty(token)){
            try {
                throw new SSOException("当前用户未登录");
            } catch (SSOException e) {
                return ResultModel.error(e.getMessage());
            }
        }
        return loginService.logout(token);
    }

}

package com.qf.xmall.cart.demo.controller;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.qf.xmall.cart.demo.common.constant.CookieConstant;
import com.qf.xmall.cart.demo.common.constant.JWTConstant;
import com.qf.xmall.cart.demo.common.constant.RedisConstant;
import com.qf.xmall.cart.demo.common.exception.CartException;
import com.qf.xmall.cart.demo.common.po.CartItemPO;
import com.qf.xmall.cart.demo.common.request.AddCartRequest;
import com.qf.xmall.cart.demo.common.request.cart.CartMergeRequest;
import com.qf.xmall.cart.demo.common.request.cart.CartModelRequest;
import com.qf.xmall.cart.demo.common.result.ResultModel;
import com.qf.xmall.cart.demo.common.sso.LoginService;
import com.qf.xmall.cart.demo.common.util.RedisUtil;
import com.qf.xmall.cart.demo.service.ICartService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ICartService cartService;

    @Autowired
    private LoginService loginService;

    @PostMapping("/add")
    public ResultModel<String> addCart(@RequestBody AddCartRequest request, HttpServletRequest req,
            HttpServletResponse resp) {
        // 参数非空的判断
        if (Objects.isNull(request) || Objects.isNull(request.getPid())) {
            return ResultModel.error("参数有误");
        }

        // 执行具体的添加逻辑
        /*
         * 未登录状态：
         * 1.未登录状态下没有购物车
         * 2.未登录状态下有购物车没有该商品
         * 3.未登录状态下有购物车且有该商品
         *   
         * 判断未登录状态下是否是第一次访问，就看cookie中有没有cart_token。如果有就不是第一次访问，找到之前的购物车。
         * 这样的业务判断应该在service层完成，controller层只是获得cookie
         */
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (CookieConstant.CART_TOKEN.equals(cookie.getName())) {
                    String cart_token = cookie.getValue();
                    // 拿到token放到request对象中，这个request对象会传入到service里，由service去做具体的判断：是否是第一次
                    request.setToken(cart_token);
                }
            }
        }

        // 执行具体的调service的动作，resultModel有cartToken，需要把cartToken存入到cookie中
        ResultModel<String> resultModel = this.addCart(request);

        // 封装cookie
        if (!StringUtils.isEmpty(resultModel.getResult())) {
            // 未登录状态下，把cartToken存入到cookie
            Cookie cookie = new Cookie(CookieConstant.CART_TOKEN, resultModel.getResult());
            cookie.setPath("/");// localhost:8080/cart localhost:8080/sso
            cookie.setMaxAge(7 * 24 * 60 * 60);
            resp.addCookie(cookie);
        }
        return resultModel;
    }

    /**
     * 调用具体的service
     */
    private ResultModel<String> addCart(AddCartRequest request) {
        // 已登陆状态
        if (!StringUtils.isEmpty(request.getToken())) {
            // 去sso模块验证当前用户是否已登陆
            ResultModel resultModel = loginService.checkLogin(request.getToken());
            Object result = resultModel.getResult();
            if (Objects.nonNull(result)) {
                JSONObject obj = (JSONObject) resultModel.getResult();
                if ((int) obj.get("state") == 1) {
                    // 表示已登陆，将商品添加到已登陆的购物车中
                    CartModelRequest cartModelRequest = new CartModelRequest();
                    BeanUtils.copyProperties(request, cartModelRequest);
                    return cartService.addLoginCart(cartModelRequest);
                }
            }
        }
        // 未登录状态下的购物车
        CartModelRequest cartModelRequest = new CartModelRequest();
        BeanUtils.copyProperties(request, cartModelRequest);
        // 添加完购物车后获得当前购物车的cartToken
        ResultModel<String> resultModel = cartService.addUnLoginCart(cartModelRequest);
        return resultModel;
    }

    /**
     * 如果未登录，token是null,但是cookie里是有cart_token
     * 如果已登陆，token是jwt，cookie里没有cart_token
     */
    @GetMapping("/details")
    public ResultModel<List<CartItemPO>> cartDetails(String token, HttpServletRequest request) {
        // String token = null;
        // 未登录状态下：
        // 1.从cookie中cart_token键获得token
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (CookieConstant.CART_TOKEN.equals(cookie.getName())) {
                    String cart_token = cookie.getValue();
                    token = cart_token;
                    break;
                }
            }
        }
        return this.details(token);
    }

    private ResultModel<List<CartItemPO>> details(String token) {
        // 已登陆状态下的token的判断: token有两种可能，jwt，要么是uuid，把判断的任务交给sso
        if (!StringUtils.isEmpty(token)) {
            // 使用http访问sso模块的checkLogin
            ResultModel resultModel = loginService.checkLogin(token);
            Object result = resultModel.getResult();
            if (Objects.nonNull(result)) {
                JSONObject obj = (JSONObject) resultModel.getResult();
                int state = (int) obj.get("state");
                if (state == 1) {
                    // 已登陆，解析jwt-》用户id
                    Claims claims = Jwts.parser().setSigningKey(JWTConstant.SIGN_KEY).parseClaimsJws(token).getBody();
                    String id = claims.getId();
                    return cartService.details(id);
                }
            }
        }
        // 未登录状态下
        return cartService.details(token);
    }

    @PostMapping("/update")
    public ResultModel updateCart(@RequestBody AddCartRequest cartRequest,
            HttpServletRequest request) {
        if (Objects.isNull(cartRequest) || Objects.isNull(cartRequest.getPid())) {
            try {
                throw new CartException("参数有误");
            } catch (CartException e) {
                return ResultModel.error(e.getMessage());
            }
        }

        // 未登录
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (CookieConstant.CART_TOKEN.equals(cookie.getName())) {
                    String cart_token = cookie.getValue();
                    cartRequest.setToken(cart_token);
                    break;
                }
            }
        }
        return this.update(cartRequest);
    }

    private ResultModel update(AddCartRequest cartRequest) {
        // 已登陆
        String token = cartRequest.getToken();// jwt还是uuid？
        // 交给sso的验证是否已登陆接口来进行，如果反馈的state==1表示已登陆
        if (!StringUtils.isEmpty(token)) {
            ResultModel resultModel = loginService.checkLogin(token);
            Object result = resultModel.getResult();
            if (Objects.nonNull(result)) {
                JSONObject jsonObject = (JSONObject) result;
                int state = (int) jsonObject.get("state");
                if (state == 1) {
                    // 已登陆
                    Claims claims = Jwts.parser().setSigningKey(JWTConstant.SIGN_KEY).parseClaimsJws(token).getBody();
                    String id = claims.getId();
                    cartRequest.setToken(id);
                    CartModelRequest cartModelRequest = new CartModelRequest();
                    BeanUtils.copyProperties(cartRequest, cartModelRequest);
                    return cartService.update(cartModelRequest);
                }
            }
        }
        CartModelRequest cartModelRequest = new CartModelRequest();
        BeanUtils.copyProperties(cartRequest, cartModelRequest);
        // 未登录
        return cartService.update(cartModelRequest);
    }

    @PostMapping("/merge")
    public ResultModel merge(@RequestBody CartMergeRequest cartMergeRequest) {
        if (StringUtils.isEmpty(cartMergeRequest.getLoginToken())) {
            return ResultModel.error("token有误");
        }
        // 合并
        return cartService.merge(cartMergeRequest);
    }

}

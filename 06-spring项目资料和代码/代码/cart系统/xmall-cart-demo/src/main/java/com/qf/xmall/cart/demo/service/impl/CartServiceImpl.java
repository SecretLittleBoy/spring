package com.qf.xmall.cart.demo.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.qf.xmall.cart.demo.common.constant.JWTConstant;
import com.qf.xmall.cart.demo.common.constant.RedisConstant;
import com.qf.xmall.cart.demo.common.po.CartItemPO;
import com.qf.xmall.cart.demo.common.po.TbItemPO;
import com.qf.xmall.cart.demo.common.request.cart.CartMergeRequest;
import com.qf.xmall.cart.demo.common.request.cart.CartModelRequest;
import com.qf.xmall.cart.demo.common.result.ResultModel;
import com.qf.xmall.cart.demo.common.util.RedisUtil;
import com.qf.xmall.cart.demo.common.util.UUIDUtil;
import com.qf.xmall.cart.demo.dao.TbItemMapper;
import com.qf.xmall.cart.demo.service.ICartService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: 索尔
 */
@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private RedisTemplate redisTemplate;
    
    @Autowired
    private TbItemMapper mapper;


    /**
     *         未登录状态：
     *         1.未登录状态下没有购物车，怎么得知未登录状态下有没有购物车？通过cookie来判断
     *         2.未登录状态下有购物车没有该商品
     *         3.未登录状态下有购物车且有该商品
     *
     *  如果是未登录状态下第一次访问：没有cartToken才是第一次访问
     *    1.创建cartToken 2.根据cartToken创建rediskey，3.把购物车商品信息存入到redis中。4.返回cartToken存入到cookie
     *  如果是未登录状态下非第一次访问：只要有cartToken，就是非第一次访问
     *
     * @param request
     * @return
     */
    @Override
    public ResultModel<String> addUnLoginCart(CartModelRequest request) {
        //获得token
        String token = request.getToken();
        if(StringUtils.isEmpty(token)){
            //第一次访问,随机生成一个token
            String cartToken = UUIDUtil.getUUID();
            //1.创建redis的键 cart:token:123123uo1i2u3
            String redisKey = RedisUtil.crtRedisKey(RedisConstant.CART_TOKEN_PRE, cartToken);
            //2.向redis中添加购物车 hash结构 cart:token:123123uo1i2u3 -> {pid1001:2}  cart:token:123123uo1i2u3 -> {pid1001:2,pid1002:3}
            redisTemplate.opsForHash().put(redisKey,request.getPid(),request.getCount());
            //3.把cartToken返回，保存在用户浏览器的cookie中
            return ResultModel.success(cartToken);
        }
        /*
        token有值，非第一次访问，需要考虑下面两种情况
         - 未登录状态下没有购物车
         - 未登录状态下有购物车没有该商品
         - 未登录状态下有购物车且有该商品
         */
        //1.先从redis里把购物车拿到
        //1.1 先组织redis的key
        String redisKey = RedisUtil.crtRedisKey(RedisConstant.CART_TOKEN_PRE, token);
        //1.2从redis里把购物车拿到 cart:token:123123uo1i2u3 -> {pid1001:2,pid1002:3}
        Map cart = redisTemplate.opsForHash().entries(redisKey);
        if(Objects.isNull(cart) || cart.size()==0){
            //未登录状态下没有购物车
            redisTemplate.opsForHash().put(redisKey,request.getPid(),request.getCount());
            return ResultModel.success(token);
        }
        //有购物车
        if(!cart.containsKey(request.getPid())){
            //未登录状态下有购物车，但是没有该商品，直接put
            redisTemplate.opsForHash().put(redisKey,request.getPid(),request.getCount());
            return ResultModel.success(token);
        }
        //未登录状态下有购物车且有该商品：将商品数量相加后
        int sum = ((int) cart.get(request.getPid()))+ request.getCount();
        //再更新回redis中
        redisTemplate.opsForHash().put(redisKey,request.getPid(),sum);
        return ResultModel.success(token);
    }

    /**
     * 1.没有token
     * 2.有token但是redis中没有购物车
     * 3.有token且有购物车
     * @param token
     * @return
     */
    @Override
    public ResultModel<List<CartItemPO>> details(String token) {
        //1.没有token
        if(StringUtils.isEmpty(token)){
            return ResultModel.success("没有购物车",null);
        }
        //2.有token但是redis中没有购物车
        //2.1组织redis的key
        String redisKey = RedisUtil.crtRedisKey(RedisConstant.CART_TOKEN_PRE, token);
        Map<Long,Integer> cart = redisTemplate.opsForHash().entries(redisKey);
        if(Objects.isNull(cart)&&cart.size()==0){
            return ResultModel.success("没有购物车",null);
        }
        //3.有token且有购物车
        List<CartItemPO> cartItemPOS = new ArrayList<>();
        for (Map.Entry<Long, Integer> entry : cart.entrySet()) {
            //获得商品id
            Long pid = entry.getKey();
            Integer count = entry.getValue();
            CartItemPO cartItemPO = new CartItemPO();
            //去数据库查询商品详情
            TbItemPO tbItemPO = mapper.selectByPrimaryKey(pid);
            if(Objects.nonNull(tbItemPO)){
                //属性的复制
                BeanUtils.copyProperties(tbItemPO,cartItemPO);
                //封装数量
                cartItemPO.setCount(count);
                cartItemPOS.add(cartItemPO);
            }
        }
        return ResultModel.success(cartItemPOS);

    }

    @Override
    public ResultModel update(CartModelRequest cartModelRequest) {
        //1.没有token
        String token = cartModelRequest.getToken();
        if(StringUtils.isEmpty(token)){
            return ResultModel.success("当前没有购物车",null);
        }
        //2.有token但没有购物车
        //2.1组织redisKey
        String redisKey = RedisUtil.crtRedisKey(RedisConstant.CART_TOKEN_PRE, token);
        Map<Long,Integer> cart = redisTemplate.opsForHash().entries(redisKey);
        if(Objects.isNull(cart)||cart.size()==0){
            return ResultModel.success("当前没有购物车",null);
        }
        //3.有购物车
        redisTemplate.opsForHash().put(redisKey,cartModelRequest.getPid(),cartModelRequest.getCount());
        return ResultModel.success("更新成功",null);
    }

    /**
     * 添加商品到已登陆状态下的购物车
     * 1.当前用户没有购物车
     * 2.当前用户有购物车，但是购物车中没有商品
     * 3.当前用户有购物车且购物车中有当前这个商品
     * @param cartModelRequest
     * @return
     */
    @Override
    public ResultModel<String> addLoginCart(CartModelRequest cartModelRequest) {
        //先找到当前用户的购物车：redis（rediskey：cart_token_用户id ==》 hash结构的购物车）
        //获得用户id ==》解析jwt
        String token = cartModelRequest.getToken();
        Claims claims = Jwts.parser().setSigningKey(JWTConstant.SIGN_KEY).parseClaimsJws(token).getBody();
        String id = claims.getId();
        //去redis中获得该用户的购物车
        //组织redis的键
        String redisKey = RedisUtil.crtRedisKey(RedisConstant.CART_TOKEN_PRE, id);
        Map<Long,Integer> cart = redisTemplate.opsForHash().entries(redisKey);
        if(Objects.isNull(cart)||cart.size()==0){
            //1.当前用户没有购物车
            redisTemplate.opsForHash().put(redisKey,cartModelRequest.getPid(),cartModelRequest.getCount());
            return ResultModel.success();
        }
        //2.当前用户有购物车，但是购物车中没有商品
        if(!cart.containsKey(cartModelRequest.getPid())){
            redisTemplate.opsForHash().put(redisKey,cartModelRequest.getPid(),cartModelRequest.getCount());
            return ResultModel.success();
        }
        //3.当前用户有购物车且购物车中有当前这个商品
        int sum = ((int) cart.get(cartModelRequest.getPid()))+ cartModelRequest.getCount();
        redisTemplate.opsForHash().put(redisKey,cartModelRequest.getPid(),sum);
        return ResultModel.success();
    }

    @Override
    public ResultModel merge(CartMergeRequest cartMergeRequest) {
        //获得已登陆的token==jwt中的用户id
        String loginToken = cartMergeRequest.getLoginToken();
        Claims claims = Jwts.parser().setSigningKey(JWTConstant.SIGN_KEY).parseClaimsJws(loginToken).getBody();
        String id = claims.getId();
        //组织redisKey
        String loginRedisKey = RedisUtil.crtRedisKey(RedisConstant.CART_TOKEN_PRE, id);
        //1.获得redis中已登陆状态下的购物车
        Map<Long,Integer> loginCart = redisTemplate.opsForHash().entries(loginRedisKey);
        //2.获得未登陆状态下的购物车
        String cartToken = cartMergeRequest.getCartToken();
        if(StringUtils.isEmpty(cartToken)){
            //未登录状态下没有购物车
            return ResultModel.success("合并成功",null);
        }
        String unLoginRedisKey = RedisUtil.crtRedisKey(RedisConstant.CART_TOKEN_PRE, cartToken);
        Map<Long,Integer> unLoginCart = redisTemplate.opsForHash().entries(unLoginRedisKey);
        if(Objects.isNull(unLoginCart)||unLoginCart.size()==0){
            //未登录状态下没有购物车
            return ResultModel.success("合并成功",null);
        }
        //未登录状态下有购物车，已登陆状态下没有购物车
        if(Objects.isNull(loginCart)||loginCart.size()==0){
            //把未登录状态下购物车转换成已登陆状态下的购物车
            redisTemplate.opsForHash().putAll(loginRedisKey,unLoginCart);
            return ResultModel.success("合并成功",null);
        }
        //未登录状态下有购物车，已登陆状态下也有购物车
        for (Map.Entry<Long, Integer> cartItem : unLoginCart.entrySet()) {
            Long pid = cartItem.getKey();
            Integer unLoginCount = cartItem.getValue();
            //判断当前商品是否在已登陆状态购物车中存在
            if(!loginCart.containsKey(pid)){
                //不存在，直接把该商品放到已登陆的购物车中
                redisTemplate.opsForHash().put(loginRedisKey,pid,unLoginCount);
                continue;
            }
            //当前商品在已登陆状态购物车中存在
            Integer loginCount = loginCart.get(pid);
            int sum = loginCount+unLoginCount;
            //更新到已登陆的购物车中
            redisTemplate.opsForHash().put(loginRedisKey,pid,sum);
        }
        //删除未登录状态下的购物车
        redisTemplate.delete(unLoginRedisKey);
        return ResultModel.success("合并成功",null);
    }
}

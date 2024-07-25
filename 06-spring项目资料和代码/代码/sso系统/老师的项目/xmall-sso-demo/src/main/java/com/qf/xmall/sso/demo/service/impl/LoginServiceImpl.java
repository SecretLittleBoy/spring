package com.qf.xmall.sso.demo.service.impl;

import com.qf.xmall.sso.demo.common.constant.JWTConstant;
import com.qf.xmall.sso.demo.common.constant.RedisConstant;
import com.qf.xmall.sso.demo.common.dto.LoginDTO;
import com.qf.xmall.sso.demo.common.dto.LoginModelRequest;
import com.qf.xmall.sso.demo.common.dto.LoginResponse;
import com.qf.xmall.sso.demo.common.po.TbMemberPO;
import com.qf.xmall.sso.demo.common.request.CartMergeRequest;
import com.qf.xmall.sso.demo.common.result.ResultModel;
import com.qf.xmall.sso.demo.common.utils.RedisUtil;
import com.qf.xmall.sso.demo.common.utils.StringUtil;
import com.qf.xmall.sso.demo.dao.TbMemberMapper;
import com.qf.xmall.sso.demo.service.CartService;
import com.qf.xmall.sso.demo.service.LoginService;
import io.jsonwebtoken.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private TbMemberMapper mapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public ResultModel<LoginResponse> login(LoginModelRequest loginModelRequest) {
        // 1.根据用户名查询用户信息
        TbMemberPO tbMemberPO = mapper.selectByName(loginModelRequest.getUserName());
        // 没有查询到用户
        if (Objects.isNull(tbMemberPO)) {
            return ResultModel.error("用户名或者密码错误");
        }
        // 2.根据业务需要封装实体对象（DTO）
        LoginDTO loginDTO = new LoginDTO();
        BeanUtils.copyProperties(tbMemberPO, loginDTO);
        // 把用户输入的密码转换成md5的秘文
        String password = DigestUtils.md5DigestAsHex(loginModelRequest.getUserPwd().getBytes());
        // 3.判断两个密码是否相同
        if (password.equals(loginDTO.getPassword())) {// 密码正确
            // 3.1 生成jwt
            // 1)创建JWT对象：封装了用户信息
            JwtBuilder jwtBuilder = Jwts.builder().setId(String.valueOf(loginDTO.getId()))// 用户id
                    .setSubject(loginDTO.getUsername())// 用户名
                    .setIssuedAt(new Date())// 签发时间
                    // .setExpiration(new Date())
                    .signWith(SignatureAlgorithm.HS256, JWTConstant.SIGN_KEY);// 设置签名
            // 2）从jwt对象中得到一个token
            String token = jwtBuilder.compact();
            // 3.2 存入到redis中 redis的键 login:token:用户id
            String redisKey = StringUtil.contact(RedisConstant.LOGIN_TOKEN_PRE, String.valueOf(loginDTO.getId()));
            // 向redis中保存键值对
            RedisUtil.save(redisTemplate, redisKey, loginDTO);
            // 3.3 返回token
            LoginResponse loginResponse = new LoginResponse();
            BeanUtils.copyProperties(loginDTO, loginResponse);
            loginResponse.setToken(token);
            loginResponse.setState(1); // 1表示用户存在，登录成功；0表示用户不存在，登录失败
            return ResultModel.success("登录成功", loginResponse);
        }
        return ResultModel.error("用户名或者密码错误");
    }

    /**
     * 验证是否已登录
     */
    @Override
    public ResultModel<LoginResponse> checkLogin(String token) {
        LoginResponse loginResponse = new LoginResponse();
        try {
            // 1.解析jwt
            Claims claims = Jwts.parser().setSigningKey(JWTConstant.SIGN_KEY).parseClaimsJws(token).getBody();
            // 2.获得用户的id
            String id = claims.getId();
            // 3.去redis中去除该id对应的用户对象
            String redisKey = StringUtil.contact(RedisConstant.LOGIN_TOKEN_PRE, String.valueOf(id));
            LoginDTO loginDTO = (LoginDTO) RedisUtil.get(redisTemplate, redisKey);
            // 3.1 loginDTO是空的，有多种可能，一种超时了，一种是用户修改过密码
            if (Objects.isNull(loginDTO)) {
                loginResponse.setState(0);
                return ResultModel.success("用户登录已过期", loginResponse);
            }
            // 3.2 成功获得redis中的对象，返回给上游
            BeanUtils.copyProperties(loginDTO, loginResponse);
            // 告诉前端当前用户已登录
            loginResponse.setState(1);
            return ResultModel.success(loginResponse);
        } catch (Exception e) {
            // jwt超时了
            loginResponse.setState(0);
            return ResultModel.success("用户登录已过期", loginResponse);
        }
    }

    /**
     * 注销功能
     */
    @Override
    public ResultModel logout(String token) {
        try {
            // 1.解析jwt，获得jwt中的id
            Claims claims = Jwts.parser().setSigningKey(JWTConstant.SIGN_KEY).parseClaimsJws(token).getBody();
            String id = claims.getId();
            // 2.去redis中把该id对应的数据删掉
            String redisKey = StringUtil.contact(RedisConstant.LOGIN_TOKEN_PRE, String.valueOf(id));
            RedisUtil.delete(redisTemplate, redisKey);
            return ResultModel.success("注销成功");
        } catch (Exception e) {
            // jwt超时了
            return ResultModel.success("注销成功");
        }
    }
}

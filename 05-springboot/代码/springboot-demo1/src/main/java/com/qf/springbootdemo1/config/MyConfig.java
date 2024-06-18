package com.qf.springbootdemo1.config;

import com.qf.springbootdemo1.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 相当于是一个XML配置文件:向spring容器中注入bean
 * @Author: 索尔
 */
@Configuration
public class MyConfig {
    //<bean id="user" class="com.qf.springbootdemo1.entity.User"/>
    @Bean(name = "user1")
    public User user2(){
        User user = new User("小明");
        return user;
    }
}

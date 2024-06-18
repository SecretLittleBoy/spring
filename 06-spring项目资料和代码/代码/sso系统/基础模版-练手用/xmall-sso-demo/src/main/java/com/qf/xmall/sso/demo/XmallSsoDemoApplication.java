package com.qf.xmall.sso.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.qf.xmall.sso.demo.dao")
public class XmallSsoDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(XmallSsoDemoApplication.class, args);
    }

}

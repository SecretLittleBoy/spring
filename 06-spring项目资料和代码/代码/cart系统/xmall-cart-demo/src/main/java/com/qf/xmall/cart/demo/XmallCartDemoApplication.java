package com.qf.xmall.cart.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.qf.xmall.cart.demo.dao")
public class XmallCartDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(XmallCartDemoApplication.class, args);
    }

}

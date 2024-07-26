package com.spring.user;

import com.spring.springframework.SpringApplicationContext;
import com.spring.user.service.UserService;

public class MyApplication {

    public static void main(String[] args) {
        SpringApplicationContext applicationContext = new SpringApplicationContext(AppConfig.class);
        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.test();
    }
}

package com.qf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: 索尔
 */
@Controller // 声明一个控制器
@RequestMapping("/hello") // 访问路径
public class HelloController {
    @RequestMapping("/test1") // web接口
    public String hello() {
        System.out.println("hello world");
        return "redirect:/index.jsp";
    }

}

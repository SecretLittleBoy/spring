package com.qf.controller;

import com.qf.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: 索尔
 */
@Controller
@RequestMapping("/demo")
public class ParamController {

    @RequestMapping("/test1")
    public String test1(String username){
        System.out.println(username);
        return "redirect:/index.jsp";
    }

    @RequestMapping("/test2")
    public String test2(@RequestParam(value = "name",defaultValue = "xiaoqiang") String username){
        System.out.println(username);
        return "redirect:/index.jsp";
    }

    /**
     * 处理复杂类型的参数
     * @param user
     * @return
     */
    @RequestMapping("/test3")
    public String test3(User user){
        System.out.println(user);
        return "redirect:/index.jsp";
    }



}

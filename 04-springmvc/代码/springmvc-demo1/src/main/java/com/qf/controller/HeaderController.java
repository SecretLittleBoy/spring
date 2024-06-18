package com.qf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: 索尔
 */
@Controller
public class HeaderController {

    @GetMapping("/header/getHedaer")
    @ResponseBody
    public String getHeader(@RequestHeader("User-agent") String userAgent){
        System.out.println(userAgent);
        return userAgent;
    }

    @GetMapping("/header/getView")
    public String getView(@RequestHeader("User-agent") String userAgent){
        System.out.println(userAgent);
        return "param";
    }

    @GetMapping("/cookie/getCookie")
    public String getCookie(@CookieValue("JSESSIONID") String jsessionId){
        System.out.println(jsessionId);
        return "param";
    }

}

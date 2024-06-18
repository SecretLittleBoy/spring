package com.qf.aspect;

import com.sun.tracing.dtrace.FunctionAttributes;
import com.sun.xml.internal.ws.addressing.WsaActionUtil;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 切面，封装一些操作被代理对象的公共代码
 * @Author: 索尔 VX：214490523
 * @技术交流社区： qfjava.cn
 */
@Aspect
@Component
public class Agent {

    @Before("execution(* com.qf.star..*.*(..))")
    public void before(){
        System.out.println("联系主办方");
    }

    @AfterReturning("execution(* com.qf.star..*.*(..))")
    public void afterReturning(){
        System.out.println("经费核算");
    }

    @AfterThrowing("execution(* com.qf.star..*.*(..))")
    public void afterThrowing(){
        System.out.println("特殊情况处理");
    }

    @After("execution(* com.qf.star..*.*(..))")
    public void after(){
        System.out.println("收尾工作");
    }




}

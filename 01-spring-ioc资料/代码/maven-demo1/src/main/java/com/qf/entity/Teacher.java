package com.qf.entity;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @Author: 索尔 QQ：214490523
 * @技术交流社区： qfjava.cn
 */
public class Teacher implements InitializingBean, DisposableBean {
    @Override
    public void destroy() throws Exception {
        System.out.println("Teacher即将销毁");

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Teacher实例化了");
    }

    public void init(){
        System.out.println("teacher init...");
    }

    public void destroy1(){
        System.out.println("teacher destroy1...");
    }
}

package com.qf.test;

import com.qf.star.JayZhou;
import com.qf.star.MayDay;
import com.qf.star.SuperStar;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: 索尔 VX：214490523
 *          @技术交流社区： qfjava.cn
 */
public class AOPTest {

    /**
     * 测试周杰伦的切点
     */
    @Test
    public void test1() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml")) {
            // JayZhou jay = context.getBean(JayZhou.class);
            // 当我们使用了Spring的AOP之后，此时向Spring容器里注册的就不再是被代理对象本身，而是代理对象。
            SuperStar jay = context.getBean(SuperStar.class);
            System.out.println(jay.show("杭州"));
            System.out.println(jay.getClass());
        }
    }

    /**
     * 测试五月天的切点
     */
    @Test
    public void test2() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml")) {
            MayDay mayDay = context.getBean(MayDay.class);
            System.out.println(mayDay.show("杭州"));
            System.out.println(mayDay.getClass());
        }
    }
}

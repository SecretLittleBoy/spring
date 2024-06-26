package com.qf.test;

import com.qf.star.JayZhou;
import com.qf.star.MayDay;
import com.qf.star.SuperStar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: 索尔 VX：214490523
 *          @技术交流社区： qfjava.cn
 */
public class AOPTest {

    /**
     * 测试周杰伦的切点
     * 当我们使用了Spring的AOP之后，此时向Spring容器里注册的就不再是被代理对象本身，而是代理对象。
     */
    @Test
    public void testJayZhou() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml")) {
            context.getBean(JayZhou.class);
            assertTrue(false);
        } catch (NoSuchBeanDefinitionException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }

    /**
     * 测试周杰伦的切点
     */
    @Test
    public void testJayZhouAspect() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml")) {
            SuperStar jay = context.getBean(SuperStar.class);
            System.setOut(new PrintStream(baos));
            assertEquals(jay.show("杭州"), "演唱会太精彩了，观众都嗨晕了");
            System.out.flush();
            String output = baos.toString();
            int idx1 = output.indexOf("联系主办方");
            int idx2 = output.indexOf("杰伦在" + "杭州" + "举行了无与伦比演唱会");
            int idx3 = output.indexOf("经费核算");
            int idx4 = output.indexOf("收尾工作");
            assertTrue(idx1 != -1);
            assertTrue(idx2 != -1);
            assertTrue(idx3 != -1);
            assertTrue(idx4 != -1);
            assertTrue(idx1 < idx2);
            assertTrue(idx2 < idx3);
            assertTrue(idx3 < idx4);
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    public void testJayZhouAspectAfterThrowing() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml")) {
            SuperStar jay = context.getBean(SuperStar.class);
            System.setOut(new PrintStream(baos));
            try {
                jay.show(null);
            } catch (RuntimeException e) {
                System.out.flush();
                String output = baos.toString();
                int idx1 = output.indexOf("联系主办方");
                int idx2 = output.indexOf("特殊情况处理");
                int idx3 = output.indexOf("收尾工作");
                assertTrue(idx1 != -1);
                assertTrue(idx2 != -1);
                assertTrue(idx3 != -1);
                assertTrue(idx1 < idx2);
                assertTrue(idx2 < idx3);

                assertEquals(e.getMessage(), "断电了");
            }
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    public void testJayZhouClass() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml")) {
            SuperStar jay = context.getBean(SuperStar.class);
            assertTrue(jay.getClass().getName().contains("com.sun.proxy.$Proxy"));
        }
    }

    /**
     * 测试五月天的切点
     */
    @Test
    public void testMayDay() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml")) {
            MayDay mayDay = context.getBean(MayDay.class);
            System.setOut(new PrintStream(baos));
            assertEquals(mayDay.show("杭州"), "演唱会太精彩了，观众都嗨晕了");
            System.out.flush();
            String output = baos.toString();
            int idx1 = output.indexOf("联系主办方");
            int idx2 = output.indexOf("五月天" + "杭州" + "举行了诺亚方舟演唱会");
            int idx3 = output.indexOf("经费核算");
            int idx4 = output.indexOf("收尾工作");
            assertTrue(idx1 != -1);
            assertTrue(idx2 != -1);
            assertTrue(idx3 != -1);
            assertTrue(idx4 != -1);
            assertTrue(idx1 < idx2);
            assertTrue(idx2 < idx3);
            assertTrue(idx3 < idx4);
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    public void testMayDayClass() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml")) {
            MayDay mayDay = context.getBean(MayDay.class);
            assertTrue(mayDay.getClass().getName().contains("MayDay$$EnhancerBySpringCGLIB$$"));
        }
    }
}

package com.qf.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.druid.pool.DruidDataSource;

public class TestSpring2XML {
    @Test
    public void testLazyInit() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-2.xml")) {
            System.setOut(new PrintStream(baos));
            Object classA = context.getBean("classA"); // classA is not lazy-init
            assertNotNull(classA);
            System.out.flush();
            String output = baos.toString();
            assertFalse(output.contains("ClassA Constructor"));

            baos.reset();
            Object classA1 = context.getBean("classA1"); // ClassA1 is lazy-init
            assertNotNull(classA1);
            System.out.flush();
            output = baos.toString();
            assertTrue(output.contains("ClassA Constructor"));
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    public void testDependsOn() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();// Create a stream to hold the output
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(baos));// Redirect System.out to the new stream
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-2.xml")) {
            Object classB = context.getBean("classB");
            assertNotNull(classB);

            System.out.flush();

            String output = baos.toString();// Get the captured output

            assertTrue(output.contains("ClassA Constructor"));
            assertTrue(output.contains("ClassB Constructor"));
            assertTrue(output.indexOf("ClassA Constructor") < output.indexOf("ClassB Constructor"));
        } finally {
            System.setOut(originalOut);
        }
    }

    /**
     * 单例,多例
     */
    @Test
    public void testPrototype() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-2.xml")) {
            Object product1 = context.getBean("product"); // product is prototype scope: 多例
            Object product2 = context.getBean("product");
            assertNotNull(product1);
            assertNotNull(product2);
            assertFalse(product1 == product2);
        }
    }

    /**
     * 静态工厂方法实例化
     */
    @Test
    public void testStaticFactory() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-2.xml")) {
            Object book = context.getBean("book");
            assertNotNull(book);
        }
    }

    /**
     * 非静态工厂方法实例化bean
     */
    @Test
    public void testFactory() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-2.xml")) {
            Object englishBook = context.getBean("englishBook");
            assertNotNull(englishBook);
            Object javaBook = context.getBean("javaBook");
            assertNotNull(javaBook);
            assertTrue(englishBook != javaBook);
            assertNotEquals(englishBook, javaBook);
        }
    }

    /**
     * 生命周期回调
     */
    @Test
    public void testCallback() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(baos));
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-2.xml")) {
            Object teacher = context.getBean("teacher");
            assertNotNull(teacher);
            System.out.flush();
            assertTrue(baos.toString().contains("Teacher afterPropertiesSet()"));
            baos.reset();
        }
        System.out.flush();
        String output = baos.toString();
        assertTrue(output.contains("Teacher destroy()"));
        System.setOut(originalOut);
    }

    /**
     * 生命周期回调2
     */
    @Test
    public void testCallback2() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(baos));
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-2.xml")) {
            Object teacher1 = context.getBean("teacher1");
            assertNotNull(teacher1);
            System.out.flush();
            assertTrue(baos.toString().contains("teacher init..."));
            baos.reset();
        }
        System.out.flush();
        String output = baos.toString();
        assertTrue(output.contains("teacher destroy..."));
        System.setOut(originalOut);
    }

    /**
     * 配置第三方bean
     */
    @Test
    public void test11() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-2.xml")) {
            DruidDataSource dataSource = context.getBean("dataSource", DruidDataSource.class);
            assertNotNull(dataSource);
            assertEquals(dataSource.getUsername(), "root");
            assertEquals(dataSource.getPassword(), "root");
        }
    }
}

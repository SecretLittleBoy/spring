package com.qf.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring2XML {

    @Test
    public void testLazyInit() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-2.xml")) {
            Object classA1 = context.getBean("classA1");
            assertNotNull(classA1);
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }

    @Test
    public void testDependsOn() {
        // Create a stream to hold the output
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        PrintStream newOut = new PrintStream(baos);

        try {
            // Redirect System.out to the new stream
            System.setOut(newOut);

            // Load the Spring context
            try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-2.xml")) {
                Object classB = context.getBean("classB");
                assertNotNull(classB);
            }

            System.out.flush();

            // Reset the standard output stream
            System.setOut(originalOut);

            // Get the captured output
            String output = baos.toString();

            // Check if the output contains the expected constructor calls
            assertTrue(output.contains("ClassA Constructor"));
            assertTrue(output.contains("ClassB Constructor"));

        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        } finally {
            // Ensure System.out is reset even if an exception occurs
            System.setOut(originalOut);
        }
    }

    /**
     * 单例
     */
    @Test
    public void test7() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-2.xml");
        Object product = context.getBean("product");
        Object product1 = context.getBean("product");

    }

    /**
     * 静态工厂方法实例化
     */
    @Test
    public void test8() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-2.xml");
        Object book = context.getBean("book");
        System.out.println(book);
    }

    /**
     * 非静态工厂方法实例化bean
     */
    @Test
    public void test9() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-2.xml");
        Object englishBook = context.getBean("englishBook");
        System.out.println(englishBook);
        Object javaBook = context.getBean("javaBook");
        System.out.println(javaBook);
    }

    /**
     * 生命周期回调
     */
    @Test
    public void test10() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-2.xml");
        Object teacher = context.getBean("teacher");
        System.out.println(teacher);
        context.close();
    }

    /**
     * 配置第三方bean
     */
    @Test
    public void test11() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-2.xml");
        Object dataSource = context.getBean("dataSource");
        System.out.println(dataSource);
    }
}

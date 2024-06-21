package com.qf.test;

import com.qf.controller.StudentController;
import com.qf.entity.User;
import com.qf.entity.Worker;
import com.qf.service.StudentService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;

/**
 * @Author: 索尔 VX：214490523
 *          @技术交流社区： qfjava.cn
 */
public class TestSpring {

    /**
     * 测试三层架构
     */
    @Test
    public void testService() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml")) {
            StudentService studentService = context.getBean("StudentService", StudentService.class);
            assertTrue(false); // will not reach this line
        } catch (NoSuchBeanDefinitionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试四大注解+Autowired注解
     */
    @Test
    public void testAutowired() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml")) {
            StudentController studentController = context.getBean("studentController", StudentController.class);
            assertNotNull(studentController);
            Long randomId = (long) (Math.random() * 100);
            assertNotNull(studentController.findStudent(randomId));
            assertEquals(studentController.findStudent(randomId).getId(), randomId);
        }
    }

    /**
     * 测试@Required注解
     */
    @Test
    public void testRequired() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml")) {
            User user = context.getBean("user", User.class);
            assertNotNull(user);
            assertNotNull(user.getAddress());

        }
    }

    /**
     * 测试@Autowired注解
     */
    @Test
    public void testAutowired2() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(baos));
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml")) {
            Worker worker = context.getBean("worker", Worker.class);
            assertNotNull(worker);
            assertNotNull(worker.getAddress());

            String output = baos.toString();
            assertTrue(output.contains("show address:"));
            assertTrue(output.contains("Address{address='default address'}"));
        } finally {
            System.setOut(originalOut);
        }
    }

    /**
     * 测试四大注解+Autowired注解
     */
    @Test
    public void testAutowired3() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml")) {
            StudentController studentController = context.getBean("studentController", StudentController.class);
            assertNotNull(studentController);

            System.setOut(new PrintStream(baos));
            studentController.showStudent();
            String output = baos.toString();
            assertTrue(output.contains("PythonStudent"));
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    public void testValue1() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml")) {
            User user = context.getBean("user", User.class);
            assertNotNull(user);
            assertEquals(user.getName(), "xiaoli");
        }
    }

    @Test
    public void testValue2() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml")) {
            Worker worker = context.getBean("worker", Worker.class);
            assertNotNull(worker);
            assertEquals(worker.getName(), "xiaoli");
        }
    }

}

package com.qf.test;

import com.qf.controller.StudentController;
import com.qf.entity.User;
import com.qf.entity.Worker;
import com.qf.service.StudentService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: 索尔 VX：214490523
 * @技术交流社区： qfjava.cn
 */
public class TestSpring {

    /**
     * 测试三层架构
     */
    @Test
    public void test1(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        StudentService studentService = context.getBean("studentService", StudentService.class);
        System.out.println(studentService.findStudent(1L));
    }

    /**
     * 测试四大注解+Autowired注解
     */
    @Test
    public void test2(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        StudentController studentController = context.getBean("studentController", StudentController.class);
        System.out.println(studentController.findStudent(1L));

    }

    /**
     * 测试@Required注解
     */
    @Test
    public void test3(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        User user = context.getBean("user", User.class);
        System.out.println(user);

    }

    /**
     * 测试@Autowired注解
     */
    @Test
    public void test4(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        Worker worker = context.getBean("worker", Worker.class);
        System.out.println(worker);
    }

    /**
     * 测试四大注解+Autowired注解
     */
    @Test
    public void test5(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        StudentController studentController = context.getBean("studentController", StudentController.class);
        studentController.showStudent();

    }
}

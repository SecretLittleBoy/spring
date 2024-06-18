package com.qf.test;

import com.qf.MyJavaConfig;
import com.qf.entity.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: 索尔 VX：214490523
 * @技术交流社区： qfjava.cn
 */
public class TestJavaConfig {

    ApplicationContext context;
    @Before
    public void before(){
        context = new AnnotationConfigApplicationContext(MyJavaConfig.class);

    }

    @Test
    public void test1(){
        ApplicationContext context = new AnnotationConfigApplicationContext(MyJavaConfig.class);
        Object dataSource = context.getBean("dataSource");
        System.out.println(dataSource);
    }

    /**
     * 测试@Scope的单例和多例
     */
    @Test
    public void test2(){
        Object dataSource1 = context.getBean("a");
        Object dataSource2 = context.getBean("b");
        Object dataSource3 = context.getBean("c");
        System.out.println(dataSource1.hashCode());
        System.out.println(dataSource2.hashCode());
        System.out.println(dataSource3.hashCode());

    }

    /**
     * 测试使用bean注解注册内部的类的对象
     */
    @Test
    public void test3(){
        Teacher teacher = context.getBean(Teacher.class);
        System.out.println(teacher);
    }

    /**
     * 注入bean时的内部依赖
     */
    @Test
    public void test4(){
        Student student = context.getBean(Student.class);
        System.out.println(student.getTeacher().getName());
    }

    /**
     * 注入bean时的内部依赖,通过自动装配的方式
     */
    @Test
    public void test5(){
        Student myStudent = context.getBean("myStudent", Student.class);
        System.out.println(myStudent.getTeacher().getName());
    }

    /**
     * 通过@Import直接注册一个bean
     */
    @Test
    public void test6(){
        EnglishTeacher englishTeacher = context.getBean(EnglishTeacher.class);
        System.out.println(englishTeacher.getMajor());
    }

    /**
     * ImportSelector接口实现一次性自动注册多个bean
     */
    @Test
    public void test7(){
//        JavaStudent javaStudent = context.getBean(JavaStudent.class);
        JavaStudent javaStudent = context.getBean("javaStudent", JavaStudent.class);
        JavaTeacher javaTeacher = context.getBean(JavaTeacher.class);
        System.out.println(javaStudent.getName());
        System.out.println(javaTeacher.getName());
    }

    /**
     * 测试ImportBeanDefinitionRegistrar
     */
    @Test
    public void test8(){
        MathTeacher mathTeacher = context.getBean(MathTeacher.class);
        System.out.println(mathTeacher.getName());
    }


}

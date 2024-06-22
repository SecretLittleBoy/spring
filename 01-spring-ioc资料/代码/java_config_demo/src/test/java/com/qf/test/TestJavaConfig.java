package com.qf.test;

import com.alibaba.druid.pool.DruidDataSource;
import com.qf.MyJavaConfig;
import com.qf.entity.*;
import com.qf.controller.StudentController;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestJavaConfig {

    AnnotationConfigApplicationContext context;

    @Before
    public void before() {
        context = new AnnotationConfigApplicationContext(MyJavaConfig.class);
    }

    @After
    public void after() {
        context.close();
    }

    @Test
    public void testDataSource() {
        DruidDataSource lastDataSource = null;
        for (String beanName : new String[] { "a", "b", "c", "dataSource" }) {
            DruidDataSource dataSource = (DruidDataSource) context.getBean(beanName);
            assertNotNull(dataSource);
            assertEquals(dataSource.getName(), "root");
            assertEquals(dataSource.getPassword(), "root");

            // test @Scope("prototype")
            assertTrue(dataSource != lastDataSource);
            lastDataSource = dataSource;
        }
    }

    /**
     * 测试使用bean注解注册内部的类的对象
     */
    @Test
    public void testTeacher() {
        Teacher teacher = context.getBean(Teacher.class);
        assertNotNull(teacher);
        assertEquals(teacher.getName(), "Thor");
        assertEquals(teacher.getMajor(), "Java");

        Teacher teacher1 = context.getBean(Teacher.class);
        assertTrue(teacher == teacher1);
    }

    /**
     * 注入bean时的内部依赖
     */
    @Test
    public void testStudent() {
        Student[] students = new Student[] { context.getBean(Student.class),
                context.getBean("myStudent", Student.class) };
        assertTrue(students[0] != students[1]);
        for (Student student : students) {
            assertNotNull(student);
            assertEquals(student.getTeacher().getName(), "Thor");
            assertEquals(student.getTeacher().getMajor(), "Java");

            Teacher teacher = context.getBean(Teacher.class);
            assertTrue(student.getTeacher() == teacher);
        }
    }

    /**
     * 通过@Import直接注册一个bean
     */
    @Test
    public void testImport() {
        EnglishTeacher englishTeacher = context.getBean(EnglishTeacher.class);
        assertNotNull(englishTeacher);
        assertEquals(englishTeacher.getName(), "Thor");
        assertEquals(englishTeacher.getMajor(), "English");
    }

    @Test
    public void testController() {
        StudentController studentController = context.getBean(StudentController.class);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(baos));
        try {
            studentController.run();
            String output = baos.toString();
            assertTrue(output.contains("StudentController..."));
            assertTrue(output.contains("teacher: "));
            assertFalse(output.contains("null"));
        } finally {
            System.setOut(originalOut);
        }
    }

    /**
     * ImportSelector接口实现一次性自动注册多个bean
     */
    @Test
    public void testImportSelector() {
        try {
            // JavaStudent是ImportSelector接口注册，没有@Component注解
            // 所以无法通过context.getBean("javaStudent", JavaStudent.class)获取
            JavaStudent errorObject = context.getBean("javaStudent", JavaStudent.class);
            assertTrue(false);
        } catch (NoSuchBeanDefinitionException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }

        JavaStudent javaStudent = context.getBean(JavaStudent.class);
        JavaTeacher javaTeacher = context.getBean(JavaTeacher.class);
        assertNotNull(javaStudent);
        assertNotNull(javaTeacher);
    }

    /**
     * 测试ImportBeanDefinitionRegistrar
     */
    @Test
    public void test8() {
        MathTeacher mathTeacher = context.getBean(MathTeacher.class);
        System.out.println(mathTeacher.getName());
    }

}

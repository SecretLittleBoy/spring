package com.qf.test;

import com.alibaba.druid.pool.DruidDataSource;
import com.qf.MyJavaConfig;
import com.qf.entity.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
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

    /**
     * ImportSelector接口实现一次性自动注册多个bean
     */
    @Test
    public void test7() {
        // JavaStudent javaStudent = context.getBean(JavaStudent.class);
        JavaStudent javaStudent = context.getBean("javaStudent", JavaStudent.class);
        JavaTeacher javaTeacher = context.getBean(JavaTeacher.class);
        System.out.println(javaStudent.getName());
        System.out.println(javaTeacher.getName());
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

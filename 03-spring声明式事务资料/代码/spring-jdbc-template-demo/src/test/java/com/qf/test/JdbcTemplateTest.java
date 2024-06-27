package com.qf.test;

import com.qf.entity.Student;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcTemplateTest {
    ClassPathXmlApplicationContext context;

    @Before
    public void before() {
        context = new ClassPathXmlApplicationContext("classpath:spring.xml");
    }

    @After
    public void after() {
        context.close();
    }

    /**
     * 查询
     */
    @Test
    public void test1() {
        JdbcTemplate jdbcTemplate = context.getBean("jdbcTemplate", JdbcTemplate.class);
        // 使用jdbcTemplate执行sql实现查询
        Integer result = jdbcTemplate.queryForObject("select count(*) from student", Integer.class);
        System.out.println(result);
    }

    /**
     * 携带参数查询
     */
    @Test
    public void test2() {
        JdbcTemplate jdbcTemplate = context.getBean("jdbcTemplate", JdbcTemplate.class);
        // 使用jdbcTemplate执行sql实现查询
        Integer result = jdbcTemplate.queryForObject("select count(*) from student where sage>?", Integer.class, 19);
        System.out.println(result);
    }

    /**
     * 查询实体方式一
     */
    @Test
    public void test3() {
        JdbcTemplate jdbcTemplate = context.getBean("jdbcTemplate", JdbcTemplate.class);
        // 把sql语句查询出来的结果集中的内容，封装在一个Student对象里面并返回
        Student student1 = jdbcTemplate.queryForObject("select * from student where sno=?", (resultSet, rowNum) -> {
            Student student = new Student();
            student.setSno(resultSet.getLong("sno"));
            student.setSname(resultSet.getString("sname"));
            student.setSsex(resultSet.getString("ssex"));
            student.setSage(resultSet.getInt("sage"));
            student.setSdept(resultSet.getString("sdept"));
            return student;
        }, 201215121L);
        System.out.println(student1);
    }

    /**
     * 查询实体方式二
     */
    @Test
    public void test4() {
        JdbcTemplate jdbcTemplate = context.getBean("jdbcTemplate", JdbcTemplate.class);
        // 使用BeanPropertyRowMapper快速自动封装，前提是数据库表中的列名和Student实体的属性名相同
        Student student = jdbcTemplate.queryForObject("select * from student where sno=?",
                new BeanPropertyRowMapper<>(Student.class), 201215121L);
        System.out.println(student);
    }

    /**
     * 查询多个实体
     */
    @Test
    public void test5() {
        JdbcTemplate jdbcTemplate = context.getBean("jdbcTemplate", JdbcTemplate.class);
        List<Student> students = jdbcTemplate.query("select * from student",
                new BeanPropertyRowMapper<>(Student.class));
        System.out.println(students);
    }

    @Test
    public void test51() {
        JdbcTemplate jdbcTemplate = context.getBean("jdbcTemplate", JdbcTemplate.class);
        List<Student> students = jdbcTemplate.query("select * from student", (resultSet, rowNum) -> {
            Student student = new Student();
            student.setSno(resultSet.getLong("sno"));
            student.setSname(resultSet.getString("sname"));
            student.setSsex(resultSet.getString("ssex"));
            student.setSage(resultSet.getInt("sage"));
            student.setSdept(resultSet.getString("sdept"));
            return student;
        });
        System.out.println(students);
    }

    /**
     * 插入数据
     */
    @Test
    public void test6() {
        JdbcTemplate jdbcTemplate = context.getBean("jdbcTemplate", JdbcTemplate.class);
        int update = jdbcTemplate.update("insert into student(sno,sname) values(?,?)", 2001L, "xiaoming");
        System.out.println(update);
    }

    /**
     * 更新数据
     */
    @Test
    public void test7() {
        JdbcTemplate jdbcTemplate = context.getBean("jdbcTemplate", JdbcTemplate.class);
        int update = jdbcTemplate.update("update student set sname=? where sno=?", "xiaohong", 2001L);
        System.out.println(update);
    }

    /**
     * 删除数据
     */
    @Test
    public void test8() {
        JdbcTemplate jdbcTemplate = context.getBean("jdbcTemplate", JdbcTemplate.class);
        int update = jdbcTemplate.update("delete from student where sno=?", 2001L);
        System.out.println(update);
    }

    /**
     * NamedParameterJdbcTemplate
     */
    @Test
    public void test9() {
        DataSource dataSource = context.getBean("dataSource", DataSource.class);
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        // SQL查询需要的参数封装进Map中
        Map<String, Object> map = new HashMap<>();
        map.put("sno", 201215121L);
        map.put("sname", "李勇");
        Integer result = namedParameterJdbcTemplate
                .queryForObject("select count(*) from student where sno=:sno and sname=:sname", map, Integer.class);
        System.out.println(result);
    }

}

package com.qf.springbootdemo1.controller;

import com.qf.springbootdemo1.config.ImgConfig;
import com.qf.springbootdemo1.entity.Student;
import com.qf.springbootdemo1.entity.User;
import com.qf.springbootdemo1.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: 索尔
 */
@RestController
public class TestController {

    @Autowired
    private StudentMapper studentMapper;

    @Resource(name = "user1")
    private User user;

    @RequestMapping("/test")
    public String test(){
        return "hello spring boot!";
    }

    @RequestMapping("/user")
    public User getUser(){
        return user;
    }


    @Autowired
    private ImgConfig imgConfig;

    @RequestMapping("/img")
    public String getImgExtend(){
        return imgConfig.getExtend();
    }

    @RequestMapping("/students")
    public List<Student> getStudents(){
        return studentMapper.findAll();
    }
}

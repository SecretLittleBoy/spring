package com.qf.controller;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

import com.qf.entity.Teacher;

@Controller
public class StudentController {

    @Resource
    private Teacher teacher;

    public void run() {
        System.out.println("StudentController...");
        System.out.println("teacher: " + teacher);
    }
}

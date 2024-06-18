package com.qf.springbootdemo1.mapper;

import com.qf.springbootdemo1.entity.Student;

import java.util.List;

public interface StudentMapper {

    List<Student> findAll();

}

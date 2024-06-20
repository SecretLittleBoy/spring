package com.qf.dao;

import com.qf.entity.Student;

public interface StudentDao {
    Student selectStudentById(Long id);
}

package com.qf.service.impl;

import com.qf.entity.Student;
import com.qf.service.StudentService;
import org.springframework.stereotype.Service;

/**
 * @Author: 索尔 VX：214490523
 * @技术交流社区： qfjava.cn
 */
@Service
public class EnglishStudentServiceImpl implements StudentService {
    @Override
    public Student findStudent(Long id) {
        Student student = new Student();
        student.setId(2L);
        student.setName("xiaoming");
        student.setAge(20);
        return student;
    }
}

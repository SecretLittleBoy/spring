package com.qf.service.impl;

import com.qf.dao.StudentDao;
import com.qf.entity.Student;
import com.qf.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @Author: 索尔 VX：214490523
 *          @技术交流社区： qfjava.cn
 */
@Service
// @Primary
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public Student findStudent(Long id) {
        return studentDao.selectStudentById(id);
    }

    public StudentDao getStudentDao() {
        return studentDao;
    }

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }
}

package com.qf.dao.impl;

import com.qf.dao.StudentDao;
import com.qf.entity.Student;
import org.springframework.stereotype.Repository;

/**
 * @Author: 索尔 VX：214490523
 *          @技术交流社区： qfjava.cn
 */
@Repository
public class StudentDaoImpl implements StudentDao {
    @Override
    public Student selectStudentById(Long id) {
        // 模拟数据库根据id查询出该学生的信息
        Student student = new Student();
        student.setId(id);
        student.setName("Thor");
        student.setAge(18);
        return student;
    }
}

package com.qf.controller;

import com.qf.entity.JavaStudent;
import com.qf.entity.PythonStudent;
import com.qf.entity.Student;
import com.qf.service.BaseService;
import com.qf.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * @Author: 索尔 VX：214490523
 * @技术交流社区： qfjava.cn
 */
@Controller
public class StudentController {

//    @Autowired
//    @Qualifier("englishStudentServiceImpl")
    @Resource(name = "studentServiceImpl")
    private StudentService studentService;

    @Autowired
    private BaseService<PythonStudent> baseService;

    public void showStudent(){
        baseService.getStudent();
    }


    public Student findStudent(Long id){
        return studentService.findStudent(id);
    }


}

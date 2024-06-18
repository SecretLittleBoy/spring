package com.qf.service.impl;

import com.qf.entity.PythonStudent;
import com.qf.service.BaseService;
import org.springframework.stereotype.Service;

/**
 * @Author: 索尔 VX：214490523
 * @技术交流社区： qfjava.cn
 */
@Service
public class PythonStudentServiceImpl implements BaseService<PythonStudent> {
    @Override
    public PythonStudent getStudent() {
        System.out.println("PythonStudent");
        return null;
    }
}

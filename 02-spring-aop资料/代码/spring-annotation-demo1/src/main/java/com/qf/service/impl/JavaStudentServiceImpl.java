package com.qf.service.impl;

import com.qf.entity.JavaStudent;
import com.qf.service.BaseService;
import org.springframework.stereotype.Service;

/**
 * @Author: 索尔 VX：214490523
 * @技术交流社区： qfjava.cn
 */
@Service
public class JavaStudentServiceImpl implements BaseService<JavaStudent> {
    @Override
    public JavaStudent getStudent() {
        System.out.println("JavaStudent");
        return null;
    }
}

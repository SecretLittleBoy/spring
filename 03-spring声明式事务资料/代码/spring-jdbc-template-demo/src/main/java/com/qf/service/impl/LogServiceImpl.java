package com.qf.service.impl;

import com.qf.dao.AccountDao;
import com.qf.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: 索尔 VX：214490523
 * @技术交流社区： qfjava.cn
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private AccountDao accountDao;
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void log() {
        System.out.println("记录操作日志");
        accountDao.reduce();//模拟出对数据库中数据进行修改的效果
    }
}

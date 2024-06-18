package com.qf.service.impl;

import com.qf.dao.AccountDao;
import com.qf.service.AccountService;
import com.qf.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: 索尔 VX：214490523
 * @技术交流社区： qfjava.cn
 */
@Service
//@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private LogService logService;

/*
    @Override
    public void transfer() {
//        connection.begin();开启事务
        try {
            System.out.println("小红转出");
            accountDao.reduce();
            int i = 10/0;
            System.out.println("小明转入");
            accountDao.add();
        } catch (Exception e) {
            throw new RuntimeException(e);
            //connection.rollback();回滚事务
        }
        // connection.commit();提交事务
    }
*/

    @Transactional
    @Override
    public void transfer() {
//        logService.log();
        System.out.println("小红转出");
        this.reduce();
//        int i = 10/0;
        System.out.println("小明转入");
        this.add();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void log() {
        System.out.println("记录操作日志");
        reduce();//模拟出对数据库中数据进行修改的效果
    }

    @Transactional
    @Override
    public void reduce() {
        accountDao.reduce();
        this.show();
    }

    @Transactional
    @Override
    public void add() {
        accountDao.add();
        int i = 10/0;
        this.show();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void show(){
//        accountDao.add();
//        int i = 10/0;
        System.out.println("查询余额");
    }
}

package com.qf.test;

import com.qf.service.AccountService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TransactionTest {

    ClassPathXmlApplicationContext context;

    @Before
    public void before() {
        context = new ClassPathXmlApplicationContext("classpath:spring.xml");
    }

    @After
    public void after() {
        context.close();
    }

    @Test
    public void test1() {
        AccountService accountService = context.getBean(AccountService.class);
        accountService.transfer();
    }

    @Test
    public void test2() {
        AccountService accountService = context.getBean(AccountService.class);
        accountService.show();
    }
}

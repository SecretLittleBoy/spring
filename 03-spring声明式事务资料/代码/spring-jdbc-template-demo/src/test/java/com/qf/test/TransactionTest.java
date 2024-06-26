package com.qf.test;

import com.qf.service.AccountService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TransactionTest {

    @Test
    public void test1() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
        AccountService accountService = context.getBean(AccountService.class);
        accountService.transfer();
    }

    @Test
    public void test2() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
        AccountService accountService = context.getBean(AccountService.class);
        accountService.show();
    }
}

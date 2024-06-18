package com.qf.service;

public interface AccountService {
    /**
     * 转账
     */
    void transfer();

    /**
     * 转出
     */
    void reduce();

    /**
     * 转入
     */
    void add();

    void show();
}

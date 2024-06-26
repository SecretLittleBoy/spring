package com.qf.dao.impl;

import com.qf.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

/**
 * 使用JdbcTemplate实现对数据的访问
 */
@Repository
public class AccountDaoImpl implements AccountDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * 小红转出500
     */
    @Override
    public void reduce() {
        String sql = "update t_account set money = money - ? where id = ?";
        jdbcTemplate.update(sql, 500, 2);
    }

    /**
     * 小明转入500
     */
    @Override
    public void add() {
        String sql = "update t_account set money = money + ? where id = ?";
        jdbcTemplate.update(sql, 500, 1);
    }
}

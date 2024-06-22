package com.qf;

import com.alibaba.druid.pool.DruidDataSource;
import com.qf.entity.Student;
import com.qf.entity.Teacher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "com.qf")
// 关联配置文件
@PropertySource("classpath:db.properties")
// @Import注解作用：1.引入其他的配置类，一个或多个 2.直接自动注册一个bean
@Import(OtherJavaConfig.class)
public class MyJavaConfig {
    /*
     * 从配置文件中获取值
     */
    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String password;
    @Value("${db.url}")
    private String url;
    @Value("${db.driverClassName}")
    private String driverClassName;

    /**
     * 注册第三方的bean
     * 1.@Bean注解是打在方法上的
     * 2.方法的返回值类型就是向IoC容器注入的Bean的类型
     * 3.方法名就是这个bean的Id
     * 4.参数列表，可以让Spring容器进行自动装配
     */
    @Bean(name = { "a", "b", "c", "dataSource" })
    @Scope("prototype")
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setName(username);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driverClassName);
        return dataSource;
    }

}

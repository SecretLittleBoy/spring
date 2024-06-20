package com.qf.entity;

import com.sun.corba.se.spi.orbutil.threadpool.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @Author: 索尔 VX：214490523
 *          @技术交流社区： qfjava.cn
 */
@Component
@Lazy
@DependsOn("address")
@Scope("singleton")
public class Worker {

    private Address address;

    // @Value("${worker.name}")
    @Value("#{user.name}")
    private String name;

    // @Autowired
    public Worker(Address address) {
        this.address = address;
    }

    @Autowired
    public void show(Address address) {
        System.out.println("address");
    }

    @Override
    public String toString() {
        return "Worker{" +
                "address=" + address +
                ", name='" + name + '\'' +
                '}';
    }
}

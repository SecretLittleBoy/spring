package com.qf.entity;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;

import javax.xml.ws.RequestWrapper;

/**
 * @Author: 索尔 VX：214490523
 *          @技术交流社区： qfjava.cn
 */
public class User {

    @Value("${User.name}")
    private String name;
    private int age;

    private Address address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    @Required
    public void setAddress(Address address) {
        this.address = address;
    }
}

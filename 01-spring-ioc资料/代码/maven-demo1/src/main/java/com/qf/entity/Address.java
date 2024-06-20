package com.qf.entity;

/**
 * @Author: 索尔 QQ：214490523
 * @技术交流社区： qfjava.cn
 */
public class Address {
    private String position;
    private Long zipCode;

    public Address() {
        System.out.println("Address Constructor");
    }

    @Override
    public String toString() {
        return "Address{" +
                "position='" + position + '\'' +
                ", zipCode=" + zipCode +
                '}';
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Long getZipCode() {
        return zipCode;
    }

    public void setZipCode(Long zipCode) {
        this.zipCode = zipCode;
    }
}

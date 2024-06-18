package com.qf.entity;

/**
 * 这段代码定义了一个名为"Product"的类，位于"com.qf.entity"包中。该类有两个私有实例变量："id"为Long类型，"name"为String类型。类还有一个默认构造函数，在创建类的实例时打印"product init.."。
 * 该类具有用于"id"和"name"变量的getter和setter方法。getter方法返回变量的值，setter方法设置变量的值。
 * 该类还重写了toString()方法，该方法返回对象的字符串表示。在这种情况下，它返回一个包含"id"和"name"变量值的字符串。
 * 总体而言，这段代码定义了一个基本的产品类，具有获取和设置产品id和name的功能，并且能够获取产品的字符串表示。
 * @Author: 索尔 VX：214490523
 * @技术交流社区： qfjava.cn
 */
public class Product {

    private Long id;
    private String name;

    public Product(){
        System.out.println("product init..");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

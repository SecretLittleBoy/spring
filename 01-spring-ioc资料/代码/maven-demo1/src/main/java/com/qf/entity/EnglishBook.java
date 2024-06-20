package com.qf.entity;

/**
 * @Author: 索尔 QQ：214490523
 * @技术交流社区： qfjava.cn
 */
public class EnglishBook extends Book {
    String address;
    public EnglishBook() {
        super(1L, "English Book");
        this.address = "Beijing";
    }
    // Example method with a clearer purpose
    void calculateSum() {
        int number1 = 10;
        int number2 = 20;
        int sum = number1 + number2;
        System.out.println("Sum: " + sum);
    }
}
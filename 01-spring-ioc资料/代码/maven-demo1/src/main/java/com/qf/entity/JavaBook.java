package com.qf.entity;

/**
 * @Author: 索尔 VX：214490523
 * @技术交流社区： qfjava.cn
 */
public class JavaBook extends Book{
    String author;
    public JavaBook() {
        super(2L, "Java Book");
        this.author = "Sol";
    }
    void testCode1(){
        int a = 10;
        int b = 20;
        int c = a + b;
    }
}

package com.qf.entity;

/**
 * @Author: 索尔 QQ：214490523
 * @技术交流社区： qfjava.cn
 */
public class BookFactory {

    /**
     * 创建EnglishBook的工厂方法
     * @return
     */
    public Book createEnglishBookInstance(){
        Book book = new EnglishBook();
        book.setId(1002L);
        book.setName("千锋英语");
        return book;
    }

    /**
     * 创建JavaBook的工厂方法
     * @return
     */
    public Book createJavaBookInstance(){
        Book book = new JavaBook();
        book.setId(1003L);
        book.setName("千锋Java");
        return book;
    }

}

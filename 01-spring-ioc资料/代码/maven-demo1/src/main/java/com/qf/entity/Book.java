package com.qf.entity;

/**
 * @Author: 索尔 QQ：214490523
 * @技术交流社区： qfjava.cn
 */
public class Book {
    private Long id;
    private String name;

    private Book() {
    }

    public Book(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    private static Book book = new Book();

    public static Book createInstance() {
        return book;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
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
}

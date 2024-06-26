package com.qf.test;

import com.qf.service.IProductService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: 索尔 VX：214490523
 *          @技术交流社区： qfjava.cn
 */
public class AOPTest {
    @Test
    public void test1() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml")) {
            IProductService productService = context.getBean(IProductService.class);
            productService.getProductById(2L);
        }
    }

    @Test
    public void test2() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml")) {
            IProductService productService = context.getBean(IProductService.class);
            productService.deleteById(null);
        }
    }
}

package controller;

import entity.Product;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.ProductService;
import service.impl.ProductServiceImpl;

/**
 * @Author: 索尔 QQ：214490523
 * @技术交流社区： qfjava.cn
 */
public class TestDemo1 {
    public static void main(String[] args) {
//        ProductService productService = new ProductServiceImpl();
        //从Spring容器里获得productService对象
        ApplicationContext ioc = new ClassPathXmlApplicationContext("spring.xml");
        ProductService productService = ioc.getBean(ProductService.class);
        //调用方法，来测试
        Product product = productService.getProductById();
        //....
    }
}

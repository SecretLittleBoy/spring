package com.qf.test;

import com.qf.entity.Product;
import com.qf.entity.Student;
import com.qf.entity.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: 索尔 QQ：214490523
 * @技术交流社区： qfjava.cn
 */
public class TestProduct {

    @Test
    public void test1(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        Product product = context.getBean("product12", Product.class);
        System.out.println(product);
    }

    @Test
    public void test2(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        //1.通过类的方式获取bean
//        Product product = context.getBean(Product.class);
//        System.out.println(product);
        //2.通过bean的id来获取bean
//        Object product1 = context.getBean("product1");
//        System.out.println(product1);
        //3.通过bean的name来获取bean
//        Object product11 = context.getBean("product11");
//        System.out.println(product11);
        //4.通过名字+类型来获得bean
        Product product11 = context.getBean("product11", Product.class);
        System.out.println(product11);

    }

    @Test
    public void test3(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        Object qfproduct = context.getBean("qfproduct");
        System.out.println(qfproduct);
    }

    @Test
    public void test4(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        User user = context.getBean("user", User.class);
        System.out.println(user);
    }

    @Test
    public void test5(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        Student student = context.getBean(Student.class);
        System.out.println(student);
    }

    /**
     * depends on
     */
    @Test
    public void test6(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-2.xml");

        Object classA1 = context.getBean("classA1");
        System.out.println(classA1);
    }

    /**
     * 单例
     */
    @Test
    public void test7(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-2.xml");
        Object product = context.getBean("product");
        Object product1 = context.getBean("product");

    }

    /**
     * 静态工厂方法实例化
     */
    @Test
    public void test8(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-2.xml");
        Object book = context.getBean("book");
        System.out.println(book);
    }

    /**
     * 非静态工厂方法实例化bean
     */
    @Test
    public void test9(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-2.xml");
        Object englishBook = context.getBean("englishBook");
        System.out.println(englishBook);
        Object javaBook = context.getBean("javaBook");
        System.out.println(javaBook);
    }

    /**
     * 生命周期回调
     */
    @Test
    public void test10(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-2.xml");
        Object teacher = context.getBean("teacher");
        System.out.println(teacher);
        context.close();
    }

    /**
     * 配置第三方bean
     */
    @Test
    public void test11(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-2.xml");
        Object dataSource = context.getBean("dataSource");
        System.out.println(dataSource);
    }









    @Test
    public void test12(){
        int[] arr = {9, 5, 1, 6, 3, 8, 4, 2, 7};
        quickSort(arr, 0, arr.length - 1);
        System.out.println("排序后的数组：");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }


    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivot = partition(arr, low, high); // 获取分区点的索引
            quickSort(arr, low, pivot - 1); // 对分区点左侧进行快速排序
            quickSort(arr, pivot + 1, high); // 对分区点右侧进行快速排序
        }
    }
    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[low]; // 选择第一个元素作为分区点
        while (low < high) {
            while (low < high && arr[high] >= pivot) {
                high--;
            }
            arr[low] = arr[high]; // 将小于分区点的元素移到左侧
            while (low < high && arr[low] <= pivot) {
                low++;
            }
            arr[high] = arr[low]; // 将大于分区点的元素移到右侧
        }
        arr[low] = pivot; // 将分区点放置到最终位置
        return low; // 返回分区点的索引
    }




}

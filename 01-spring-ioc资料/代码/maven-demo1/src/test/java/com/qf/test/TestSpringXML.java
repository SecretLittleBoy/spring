package com.qf.test;

import com.qf.entity.Product;
import com.qf.entity.Student;
import com.qf.entity.User;
import com.qf.entity.Address;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Date;

import org.junit.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: 索尔 QQ：214490523
 *          @技术交流社区： qfjava.cn
 */
public class TestSpringXML {

    @Test
    public void testGetBySameName() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml")) {
            Product product1 = context.getBean("productName1", Product.class);
            Product product2 = context.getBean("productName1", Product.class);
            assertTrue(product1 == product2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetByDifferentName() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml")) {
            Product product1 = context.getBean("productName1", Product.class);
            Product product2 = context.getBean("productName2", Product.class);
            assertTrue(product1 != product2);
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }

    @Test
    public void testAlias() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml")) {
            Product product1 = context.getBean("productName1", Product.class);
            Product product2 = context.getBean("aliasForProductName1", Product.class);
            assertTrue(product1 == product2);
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }

    @Test
    public void testGetById() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml")) {
            Object productById = context.getBean("productId1");
            Object productByName = context.getBean("productName1");
            assertNotNull(productById);
            assertNotNull(productByName);
            assertTrue(productById == productByName);
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }

    @Test
    public void testGetByClass() {
        // one class have multiple beans, get by class will throw exception
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml")) {
            Product product = context.getBean(Product.class);
            assertTrue(false); // This line will not be executed
        } catch (NoUniqueBeanDefinitionException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }

    @Test
    public void testProperty() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml")) {
            Address address = context.getBean("address", Address.class);
            assertNotNull(address);
            assertEquals(address.getPosition(), "北京市海淀区");
            assertEquals(address.getZipCode(), Long.valueOf(100001));
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }

    @Test
    public void testUser() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml")) {
            User user = context.getBean("user", User.class);
            assertNotNull(user);
            assertEquals(user.getId(), Integer.valueOf(1001));
            assertEquals(user.getPassword(), "123456");
            assertEquals(user.getSex(), "male");
            assertEquals(user.getAge(), Integer.valueOf(20));
            assertEquals(user.getBornDate(), Date.valueOf("1990-01-01"));

            assertEquals(user.getHobbys().length, 3);
            assertEquals(user.getHobbys()[0], "Run");
            assertEquals(user.getHobbys()[1], "Swim");
            assertEquals(user.getHobbys()[2], "Climb");

            assertEquals(user.getPhones().size(), 3);
            assertTrue(user.getPhones().contains("13777777777"));
            assertTrue(user.getPhones().contains("13888888888"));
            assertTrue(user.getPhones().contains("13999999999"));

            assertEquals(user.getNames().size(), 3);
            assertEquals(user.getNames().get(0), "Jack");
            assertEquals(user.getNames().get(1), "Tom");
            assertEquals(user.getNames().get(2), "Marry");

            assertEquals(user.getCountries().size(), 3);
            assertEquals(user.getCountries().get("CN"), "China");
            assertEquals(user.getCountries().get("US"), "America");
            assertEquals(user.getCountries().get("KR"), "Korea");

            assertEquals(user.getFiles().size(), 3);
            assertEquals(user.getFiles().get("first"), "One");
            assertEquals(user.getFiles().get("second"), "Two");
            assertEquals(user.getFiles().get("third"), "Three");

            assertNotNull(user.getAddress());
            assertTrue(user.getAddress() == context.getBean("address", Address.class));
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }

    @Test
    public void testStudent() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml")) {
            Student student = context.getBean(Student.class);
            assertNotNull(student);
            assertEquals(student.getId(), Integer.valueOf(12345));
            assertEquals(student.getName(), "tom");
            assertEquals(student.getAge(), Integer.valueOf(20));
            assertEquals(student.getSex(), "male");
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }
}

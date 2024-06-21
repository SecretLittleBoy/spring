package com.qf;

import com.qf.entity.MathTeacher;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Author: 索尔 VX：214490523
 * @技术交流社区： qfjava.cn
 * SpringIoC实现Bean的注册：
 * 1.xml的方式
 * 2.xml+@Component @Controller @Service @Repository
 * 3.@Configuration+@Bean  @Import ImportSelector\
 * SpringIoC如何实现自动装配
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        // 获得beanDefinition图纸对象
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        // 让图纸对象关联MathTeacher类，此时beanDefinition就是MathTeacher的图纸
        beanDefinition.setBeanClass(MathTeacher.class);
        // 注册该图纸，于是spring容器就可以根据该图纸生产指定的bean
        registry.registerBeanDefinition("mathTeacher", beanDefinition);

    }
}

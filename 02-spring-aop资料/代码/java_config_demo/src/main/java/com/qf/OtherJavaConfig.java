package com.qf;

import com.qf.entity.EnglishTeacher;
import com.qf.entity.Student;
import com.qf.entity.Teacher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Author: 索尔 VX：214490523
 * @技术交流社区： qfjava.cn
 */
@Configuration
@Import({EnglishTeacher.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
public class OtherJavaConfig {
    /**
     * 使用@Bean注解注册内部bean
     * @return
     */
    @Bean
    public Teacher teacher(){
        Teacher teacher = new Teacher();
        teacher.setName("Thor");
        teacher.setMajor("Java");
        return teacher;
    }

    @Bean
    public Student student(){
        Student student = new Student(teacher());
        return student;
    }

    @Bean(name = "myStudent")
    public Student student1(Teacher teacher){
        Student student = new Student(teacher);
        return student;
    }

}

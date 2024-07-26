package com.spring.springframework;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.beans.Introspector;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpringApplicationContext {

    private Class configClass;
    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();
    private Map<String, Object> singletonObjects = new HashMap<>();

    public SpringApplicationContext(Class configClass) {
        this.configClass = configClass;
        // 扫描配置类
        scan(configClass);
        for (String beanName : beanDefinitionMap.keySet()) {
            //从bean定义的map中获得bean定义
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            if (beanDefinition.getScope().equals("singleton") && !beanDefinition.isLazy()) {
                //如果是单例且非懒加载则创建bean
                Object bean = createBean(beanName, beanDefinition);
                //把单例bean存入到单例池中
                singletonObjects.put(beanName, bean);
            }
        }

    }

    private Object createBean(String beanName, BeanDefinition beanDefinition){
        //获得bean定义中的字节码对象
        Class clazz = beanDefinition.getType();
        try {
            //反射的方式来创建
            Object o = clazz.newInstance();
            //遍历属性
            for (Field field : clazz.getDeclaredFields()) {
                //如果属性上有Autowired注解，则进行依赖注入
                if (field.isAnnotationPresent(Autowired.class)) {
                    //获得属性值
                    Object bean = getBean(field.getName());
                    field.setAccessible(true);
                    //为属性赋值
                    field.set(o, bean);
                }
            }
            //如果实现了BeanNameAware接口
            if (o instanceof BeanNameAware) {
                //设置bean的名字
                ((BeanNameAware)o).setBeanName(beanName);
            }
            //如果实现了ApplicationContextAware接口
            if (o instanceof ApplicationContextAware) {
                //存入ApplicationContext对象
                ((ApplicationContextAware)o).setApplicationContext(this);
            }
            //如果打上了事务注解，则通过cglib动态代理对方法进行增强，实现开启和提交事务
            if (clazz.isAnnotationPresent(Transactional.class)) {
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(clazz);
                Object target = o;
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object proxy, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        System.out.println("开启事务");
                        Object result = method.invoke(target, objects);
                        System.out.println("提交事务");
                        return result;
                    }
                });
                o = enhancer.create();
            }

            return o;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void scan(Class configClass) {
        // 获取要扫描的包路径
        if (configClass.isAnnotationPresent(ComponentScan.class)) {
            ComponentScan annotation = (ComponentScan) configClass.getAnnotation(ComponentScan.class);
            String path = annotation.value();
            //把com.spring.user==转换成==>com/spring/user 文件夹形式的路径
            path = path.replace(".", "/");
            ClassLoader classLoader = this.getClass().getClassLoader();
            URL resource = classLoader.getResource(path);
            File file = new File(resource.getFile());
            //存储类文件的集合
            List<File> classFile = new ArrayList<>();
            if (file.isDirectory()) {
                for (File f : file.listFiles()) {
                    if (f.isDirectory()) {
                        for (File f1 : f.listFiles()) {
                            if (!f1.isDirectory()) {
                                classFile.add(f1);
                            }
                        }
                    } else {
                        classFile.add(f);
                    }

                }
            }
            //遍历类集合
            for (File cFile : classFile) {
                String absolutePath = cFile.getAbsolutePath();
                String className = absolutePath.substring(absolutePath.indexOf("com"), absolutePath.indexOf(".class"))
                        .replace("/", ".");
                try {
                    //加载类并获得类的字节码对象 com.spring.user.UserService
                    Class<?> clazz = classLoader.loadClass(className);
                    //为@Component注解的类创建bean
                    if (clazz.isAnnotationPresent(Component.class)) {
                        BeanDefinition beanDefinition = new BeanDefinition();
                        //设置字节码对象
                        beanDefinition.setType(clazz);
                        //设置是否懒加载
                        beanDefinition.setLazy(clazz.isAnnotationPresent(Lazy.class));
                        //设置单例or多例
                        if (clazz.isAnnotationPresent(Scope.class)) {
                            beanDefinition.setScope(clazz.getAnnotation(Scope.class).value());
                        } else {
                            beanDefinition.setScope("singleton");
                        }
                        //获得bean的名字
                        String beanName = clazz.getAnnotation(Component.class).value();
                        if (beanName.isEmpty()) {
                            //参考源码，如果名字为空时，使用类的名字，首字母小写
                            beanName = Introspector.decapitalize(clazz.getSimpleName());
                        }
                        //放到bean定义的map中
                        beanDefinitionMap.put(beanName, beanDefinition);

                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }

        }
    }

    public Object getBean(String beanName){
        //获取bean定义的map
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) {
            throw new NullPointerException();
        }
        if ("singleton".equals(beanDefinition.getScope())) {
            ////如果是单例，则尝试从单例池获取，如果单例池中没有则创建bean，并存入到单例池中
            Object result = singletonObjects.get(beanName);
            if (result == null) {
                result = createBean(beanName, beanDefinition);
                singletonObjects.put(beanName, result);
            }
            return result;
        } else {
            //如果非单例，则创建bean
            return createBean(beanName, beanDefinition);
        }
    }
}

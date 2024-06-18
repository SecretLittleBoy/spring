package com.qf.proxy2;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author: 索尔 VX：214490523
 * @技术交流社区： qfjava.cn
 */
public class CglibProxyeTest {

    public static void main(String[] args) {
        MayDay mayDay = new MayDay();
        //创建Cglib字节码增强对象
        Enhancer enhancer = new Enhancer();
        //设置要增强的类
        enhancer.setSuperclass(MayDay.class);
        //设置回调函数
        enhancer.setCallback(new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                try {
                    System.out.println("start...");
                    Object result = method.invoke(mayDay, objects);
                    System.out.println("end...");
                    return result;
                } catch (Exception e) {
                    System.out.println("error...");
                } finally {
                    System.out.println("finally...");
                    return null;
                }

            }
        });
        //创建的动态代理对象
        MayDay proxy = (MayDay) enhancer.create();
        System.out.println(proxy.show("上海"));


    }
}

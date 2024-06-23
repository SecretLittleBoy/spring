package com.qf.proxy2;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;

import java.lang.reflect.Method;

/**
 * @Author: 索尔 VX：214490523
 *          @技术交流社区： qfjava.cn
 */
public class CglibProxyTest {

    public static void main(String[] args) {
        // 创建被代理类的对象
        MayDay mayDay = new MayDay();
        // 创建Cglib字节码增强对象
        Enhancer enhancer = new Enhancer();
        // 设置要增强的类（被代理类）
        enhancer.setSuperclass(MayDay.class);
        // 设置回调函数
        enhancer.setCallback(new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                try {
                    System.out.println("start....");
                    Object result = method.invoke(mayDay, objects);
                    System.out.println("end....");
                    return result;
                } catch (Exception e) {
                    System.out.println("throw exception...");
                    throw new RuntimeException(e);
                } finally {
                    System.out.println("finally....");
                }
            }
        });
        // 创建动态代理对象
        MayDay proxy = (MayDay) enhancer.create();
        System.out.println(proxy.show("杭州"));
    }

}

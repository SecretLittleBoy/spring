
package com.qf.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *
 * 测试Jdk的动态代理
 * Proxy.newProxyInstanc参数：
 * 1.ClassLoader: 被代理类的类加载器
 * 2.interfaces: 被代理类实现的接口
 * 3.InvocationHandler:执行调用方法时的处理器
 * @Author: 索尔 VX：214490523
 * @技术交流社区： qfjava.cn
 */
public class JdkProxyTest {
    public static void main(String[] args) {
        //通过jdk的动态代理获得一个代理对象:通过反射实现
        IHomeRent proxy = (IHomeRent) Proxy.newProxyInstance(
                HomeOwnerPlus.class.getClassLoader(),
                new Class[]{IHomeRent.class},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //辅助功能
                        System.out.println("中介发布房屋信息");
                        System.out.println("中介带租客看房");
                        //核心功能
                        HomeOwnerPlus homeOwnerPlus = new HomeOwnerPlus();
                        Object result = method.invoke(homeOwnerPlus, args);
                        //辅助功能
                        System.out.println("中介负责房屋维修");
                        System.out.println("中介负责房屋转租");
                        return result;
                    }
                }
        );
        //调用代理对象的方法实现辅助功能+核心功能
        proxy.rent();

    }
}

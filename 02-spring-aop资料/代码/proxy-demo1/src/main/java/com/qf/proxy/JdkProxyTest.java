package com.qf.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author: 索尔 VX：214490523
 *          @技术交流社区： qfjava.cn
 */
public class JdkProxyTest {

    public static void main(String[] args) {
        IHomeRent homeRent = (IHomeRent) Proxy.newProxyInstance(
                HomeOwnerPlus.class.getClassLoader(),
                new Class[] { IHomeRent.class }, // 或 HomeOwnerPlus.class.getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("发布房屋信息");
                        System.out.println("带租客看房");
                        Object result = method.invoke(new HomeOwnerPlus(), args);
                        System.out.println("维修");
                        System.out.println("转租");
                        return result;
                    }
                });

        homeRent.rent();
    }

}

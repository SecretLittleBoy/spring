package com.qf.proxy2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author: 索尔 VX：214490523
 * @技术交流社区： qfjava.cn
 */
public class JayShowTest {

    public static void main(String[] args) {
        SuperStar superStar = (SuperStar)Proxy.newProxyInstance(
                JayZhou.class.getClassLoader(),
                JayZhou.class.getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        JayZhou jay = new JayZhou();
                        System.out.println("与主办方洽谈协商");
                        Object result = method.invoke(jay, args);
                        return result;
                    }
                }
        );
        String result = superStar.show("北京");
        System.out.println(result);
    }
}

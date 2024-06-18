package com.qf.proxy2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 使用jdk动态代理实现周杰伦演唱会商业洽谈到演出的经纪人的职责
 * @Author: 索尔 VX：214490523
 * @技术交流社区： qfjava.cn
 */
public class JayShowTest {

    public static void main(String[] args) {
         SuperStar proxy = (SuperStar)Proxy.newProxyInstance(
                JayZhou.class.getClassLoader(),
                JayZhou.class.getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        JayZhou jay = new JayZhou();
                        //经纪人要做的辅助工作
                        System.out.println("start....");
                        //核心功能：演出
                        Object result = method.invoke(jay, args);
                        System.out.println("end....");
                        return result;
                    }
                }
        );
        String result = proxy.show("北京");
        System.out.println(result);
    }
}

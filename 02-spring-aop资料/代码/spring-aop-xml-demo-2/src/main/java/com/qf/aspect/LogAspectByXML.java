package com.qf.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Author: 索尔 VX：214490523
 *          @技术交流社区： qfjava.cn
 */
// @Aspect
// @Component
public class LogAspectByXML {
    /*
     * execution(访问权限修饰符 返回值类型 包名.类名.方法名(参数列表) 抛出异常类型)
     * 
     * 例如：
     * com.qf.service.impl.ProductServiceImpl.public void deleteById(Long id)
     * @Before("execution(public void com.qf.service.impl.ProductServiceImpl.deleteById(Long))")
     */

    // @Pointcut("execution(* com.qf.service.impl.ProductServiceImpl.*(..)) &&
    // args(java.lang.Long)")
    // public void myPonitcut() {
    // }

    // @Before("myPonitcut()")
    public void before(JoinPoint joinPoint) {
        // 获得当前切入的方法的方法名
        String name = joinPoint.getSignature().getName();
        // 获得调用方法时传入的参数
        Object[] args = joinPoint.getArgs();
        System.out.println("前置通知，方法名：" + name + ",参数：" + Arrays.asList(args));
    }

    // @After("execution(* com.qf..ProductServiceImpl.*(..))")
    public void after() {
        System.out.println("后置通知");
    }

    // @AfterReturning("within(com.qf..*)")
    // @AfterReturning("this(com.qf.service.IProductService)")
    // @AfterReturning("target(com.qf.service.IProductService)")
    // @AfterReturning("args(java.lang.String)")
    // 得到切入的方法执行后返回的结果。
    // @AfterReturning(value = "myPonitcut()",returning ="returnValue" )
    public void afterReturning(Object returnValue) {
        System.out.println("后置返回通知,返回的结果：" + returnValue);
    }

    // 得到方法执行时出现的异常对象
    // @AfterThrowing(value = "execution(* *(..))",throwing = "exception")
    public void afterThrowing(Exception exception) {
        System.out.println("后置异常通知,异常信息：" + exception.getMessage());
    }

    /**
     * 环绕通知
     * 
     * @param joinPoint
     * @return
     */
    // @Around("myPonitcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        // 获得当前切入的方法的方法名
        String name = joinPoint.getSignature().getName();
        // 获得调用方法时传入的参数
        Object[] args = joinPoint.getArgs();
        Object result = null;
        try {
            System.out.println("环绕前置通知，方法名：" + name + ",参数：" + Arrays.asList(args));
            // 方法的执行
            result = joinPoint.proceed(args);
            System.out.println("环绕返回通知，返回结果：" + result);
        } catch (Throwable e) {
            System.out.println("环绕后置异常通知：方法名：" + name + ",异常信息：" + e.getMessage());
            throw e;
        } finally {
            System.out.println("环绕后置通知，方法名：" + name);
        }
        return result;
    }
}

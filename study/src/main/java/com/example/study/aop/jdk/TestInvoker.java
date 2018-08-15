package com.example.study.aop.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 *额外功能类
 */

public class TestInvoker implements InvocationHandler {

    private TestJDKAOP testJDKAOP;

    public TestInvoker(TestJDKAOP testJDKAOP) {
        this.testJDKAOP = testJDKAOP;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy before ==========");
        Object invoke = method.invoke(testJDKAOP, args);
        System.out.println("proxy after ===========");

        return invoke;
    }
}
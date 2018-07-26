package study.chenji.spring.aop.jdk;

import java.lang.reflect.Proxy;

public class JDKAOPTest {
    public static void main(String[] args){
        //代理目标对象
        TestJDKAOP testJDKAOP = new TestJDKAOPImpl();
        //获取类加载器
        ClassLoader classLoader = JDKAOPTest.class.getClassLoader();
        //获取实现的接口
        Class<?>[] interfaces = testJDKAOP.getClass().getInterfaces();
        //创建额外功能类
        TestInvoker testInvoker = new TestInvoker(testJDKAOP);

        //创建代理对象
        TestJDKAOP testJDKAOPProxy = (TestJDKAOP)Proxy.newProxyInstance(
                classLoader,
                interfaces,
                testInvoker);

        String baozi = testJDKAOPProxy.getName("baozi");

        System.out.println(baozi + "=========");
    }

}

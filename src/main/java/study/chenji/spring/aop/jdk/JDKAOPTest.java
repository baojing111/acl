package study.chenji.spring.aop.jdk;

import sun.misc.ProxyGenerator;
import java.io.FileOutputStream;
import java.lang.reflect.Modifier;
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
        //获取字节码
        byte[] bytes = ProxyGenerator.generateProxyClass(
                testJDKAOPProxy.getClass().getSimpleName(), interfaces, Modifier.PUBLIC);
        //将字节码输出到本地文件
        FileOutputStream fileOUt = null;
        try {
             fileOUt = new FileOutputStream(testJDKAOPProxy.getClass().getSimpleName() + ".class");
             fileOUt.write(bytes);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (fileOUt != null){
                try {
                    fileOUt.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}

package study.chenji.spring.aop;

import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

public class TestAop {
    public static void main(String[] args) {
        //手工创建一个实例
        ProxyService aspectJService = new ProxyServiceImpl();
        //使用AspectJ语法 自动创建代理对象
        AspectJProxyFactory aspectJProxyFactory = new AspectJProxyFactory(aspectJService);
        //添加切面和通知类
        aspectJProxyFactory.addAspect(AopAdviceConfig.class);
        //创建代理对象
        ProxyService proxyService = aspectJProxyFactory.getProxy();
        //进行方法调用
        proxyService.testProxy();
    }
}

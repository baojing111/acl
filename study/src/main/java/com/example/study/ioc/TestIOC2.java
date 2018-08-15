package com.example.study.ioc;


import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

public class TestIOC2 {
    public static void main(String[] args){
        //声明容器
        DefaultListableBeanFactory beanRegistry = new DefaultListableBeanFactory();
        //加载xml文件并注册到beanRegistry
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanRegistry);
        reader.loadBeanDefinitions("IOCbean.xml");

        IOCOneService iOCOneService = (IOCOneService)beanRegistry.getBean("iOCOneService");
        IOCTwoService iOCTwoService = (IOCTwoService)beanRegistry.getBean("iOCTwoService");

        System.out.println(iOCOneService.getIOCTwoService().getClass().getSimpleName());
        System.out.println(iOCTwoService.getIOCOneService().getClass().getSimpleName());

    }
}

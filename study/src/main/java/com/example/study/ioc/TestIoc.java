package com.example.study.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestIoc {
    public static void main(String[] args){
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("IOCbean.xml");

        IOCOneService iOCOneService = (IOCOneService)applicationContext.getBean("iOCOneService");
        IOCTwoService iOCTwoService = (IOCTwoService)applicationContext.getBean("iOCTwoService");

        System.out.println(iOCOneService.getIOCTwoService().getClass().getSimpleName());
        System.out.println(iOCTwoService.getIOCOneService().getClass().getSimpleName());

    }
}

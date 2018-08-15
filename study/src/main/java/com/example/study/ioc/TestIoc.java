package study.chenji.spring.ioc;

import org.springframework.beans.*;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.*;
import org.springframework.beans.factory.support.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.DecoratingClassLoader;
import org.springframework.core.NamedThreadLocal;
import org.springframework.core.ResolvableType;
import org.springframework.core.convert.ConversionService;
import org.springframework.lang.Nullable;
import org.springframework.util.*;

import java.beans.PropertyEditor;
import java.security.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

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

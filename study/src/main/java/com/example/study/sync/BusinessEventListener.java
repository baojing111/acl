package com.example.study.sync;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class BusinessEventListener implements ApplicationListener {
    @Async
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        BusinessEvent businessEvent = null;
        if (event instanceof BusinessEvent){
            businessEvent =  (BusinessEvent)event;
        }
        if (businessEvent.getBusinessType().equals("first_event")){
            System.out.println("first_event success");
        }
    }
}

package com.example.study.sync;


import org.springframework.context.ApplicationContext;

public class TestAsync {
    public void publishEvent(ApplicationContext context) {
        context.publishEvent(new BusinessEvent(this, "first_event", null));

    }
}

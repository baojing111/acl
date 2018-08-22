package com.example.study.sync;

import org.springframework.context.ApplicationEvent;

public class BusinessEvent<T> extends ApplicationEvent {
    private String businessType;
    private T param;
    public BusinessEvent(Object source, String businessType, T param) {
        super(source);
        this.businessType = businessType;
        this.param = param;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public T getParam() {
        return param;
    }

    public void setParam(T param) {
        this.param = param;
    }
}

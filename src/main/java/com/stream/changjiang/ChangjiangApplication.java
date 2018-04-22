package com.stream.changjiang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.stream.changjiang.*"})
public class ChangjiangApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChangjiangApplication.class, args);
    }
}

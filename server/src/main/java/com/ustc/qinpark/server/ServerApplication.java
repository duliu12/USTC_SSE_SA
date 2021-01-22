package com.ustc.qinpark.server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableScheduling
public class ServerApplication {

    @Value("${register.onlineapi}")
    String register;

    @PostConstruct
    public void regist() {
    }

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

}

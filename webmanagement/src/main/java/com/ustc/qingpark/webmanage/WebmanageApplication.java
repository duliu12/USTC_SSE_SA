package com.ustc.qingpark.webmanage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ustc.qingpark.webmanage.mapper")
public class WebmanageApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebmanageApplication.class, args);
    }

}

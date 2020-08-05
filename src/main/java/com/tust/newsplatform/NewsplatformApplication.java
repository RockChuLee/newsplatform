package com.tust.newsplatform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = "com.tust.newsplatform.mapper")
@SpringBootApplication
public class NewsplatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewsplatformApplication.class, args);
    }

}

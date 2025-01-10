package com.draw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.draw.mapper")
public class DrawApplication {
    public static void main(String[] args) {
        SpringApplication.run(DrawApplication.class, args);
    }
} 
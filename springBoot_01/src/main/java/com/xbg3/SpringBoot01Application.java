package com.xbg3;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.xbg3.mapper")

public class SpringBoot01Application {

    public static void main(String[] args) {
        try {

            SpringApplication.run(SpringBoot01Application.class, args);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}

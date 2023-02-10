package com.chenfj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan("com.chenfj.mapper")
public class SpringbootSecurityDemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SpringbootSecurityDemoApplication.class, args);
        System.out.println("====");
    }

}

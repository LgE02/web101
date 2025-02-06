package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
//스프링 부트의 자동 설정, 스프링 Bean 생성 및 읽기 등의 기능이 자동으로 설정
public class Application {
    public static void main(String[] args) {
        // project initialization
        SpringApplication.run(Application.class, args);
    }
}
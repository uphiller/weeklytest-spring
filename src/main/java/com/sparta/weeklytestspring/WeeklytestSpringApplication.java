package com.sparta.weeklytestspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class WeeklytestSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeeklytestSpringApplication.class, args);
    }

}

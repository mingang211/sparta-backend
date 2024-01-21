package com.sparta.spartacoding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpartacodingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpartacodingApplication.class, args);
    }

}

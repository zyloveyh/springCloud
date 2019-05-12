package com.zy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SchoolrelationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolrelationApplication.class, args);
    }

}

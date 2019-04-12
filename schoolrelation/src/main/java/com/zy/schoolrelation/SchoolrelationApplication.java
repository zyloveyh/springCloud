package com.zy.schoolrelation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaClient
@SpringBootApplication
public class SchoolrelationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolrelationApplication.class, args);
    }

}

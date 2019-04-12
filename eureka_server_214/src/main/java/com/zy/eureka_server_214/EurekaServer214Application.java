package com.zy.eureka_server_214;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaServer214Application {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServer214Application.class, args);
    }

}

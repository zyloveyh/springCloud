package com.zy;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EnableAutoConfiguration(exclude={DruidDataSourceAutoConfigure.class})
@EnableCaching
public class SchoolrelationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolrelationApplication.class, args);
    }

}

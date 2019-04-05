package com.zy.ribbonconsumer.controller;

import com.netflix.discovery.converters.Auto;
import com.zy.ribbonconsumer.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private HelloService helloService;

    @RequestMapping(value = "/helloConsumer", method = RequestMethod.GET)
    public String helloConsumer() {
//        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://HELLO-SERVICE/hello", String.class);
//        restTemplate.getForObject("http://HELLO-SERVICE/helloBody",String.class);
//        return forEntity.getBody();
        return helloService.helloService();
    }
}

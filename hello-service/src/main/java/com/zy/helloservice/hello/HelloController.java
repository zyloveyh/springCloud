package com.zy.helloservice.hello;

import com.zy.helloservice.entity.Hello;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
//@RequestMapping("/hello")
public class HelloController {

    private final Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    private DiscoveryClient client;

    @RequestMapping("/hello")
    public String sayHello(){
        ServiceInstance instance = client.getLocalServiceInstance();
        logger.info("/hello,host:"+instance.getHost()+" , server_id="+instance.getServiceId());
        return "Hello";
    }

    @RequestMapping("/helloBody")
    public Hello sayHelloBody(){
        ServiceInstance instance = client.getLocalServiceInstance();
        logger.info("/hello,host:"+instance.getHost()+" , server_id="+instance.getServiceId());
        return new Hello("zy","hello");
    }
}

package com.zy.demo;

import com.zy.entity.Students;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisSearchApplicationTests {

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisTemplate<Object,String> sRedisTemplate;
    @Autowired
    RedisTemplate<Object,Students> studentsRedisTemplate;
    @Test
    public void contextLoads() {
        sRedisTemplate.opsForValue().append("msg", "hello");
    }
    @Test
    public void setValueObject() {
        studentsRedisTemplate.opsForHash().put("s","msg", Students.builder().address("adf").age(12).build());

    }
    @Test
    public void getValueObject() {
        Students o = (Students) studentsRedisTemplate.opsForHash().get("s", "msg");
        System.out.println(o);

    }

    @Test
    public void getString() {
        Object msg = sRedisTemplate.opsForValue().get("msg");
        System.out.println(msg);
    }



}

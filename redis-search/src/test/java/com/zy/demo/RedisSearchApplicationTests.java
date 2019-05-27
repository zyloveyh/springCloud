package com.zy.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisSearchApplicationTests {


    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Test
    public void testSetValue() {
        stringRedisTemplate.opsForValue().set("msg", "hello");//设置值
        stringRedisTemplate.opsForValue().append("msg", "world");// 追加
    }

    @Test
    public void testSetValueTime() {
        stringRedisTemplate.opsForValue().set("msg1", "helloworld", 10, TimeUnit.SECONDS);
        //设置的是10秒失效，十秒之内查询有结果，十秒之后返回为null
    }

    @Test
    public void testSetValueOverwride() {
        stringRedisTemplate.opsForValue().set("msg", "fuck", 4);
        //set void set(K key, V value, long offset);
        //覆写(overwrite)给定 key 所储存的字符串值，从偏移量 offset 开始
    }

    @Test
    public void testValueGetAndSet() {
        String andSet = stringRedisTemplate.opsForValue().getAndSet("msg", "secondHello");
        //获取原来的值,并将新增按0开始便宜修改旧值
        System.out.println(andSet);
    }

    @Test
    public void testValueSize() {
        Long msg = stringRedisTemplate.opsForValue().size("msg");
        System.out.println(msg);
    }
}

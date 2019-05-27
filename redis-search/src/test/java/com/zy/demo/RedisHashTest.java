package com.zy.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisHashTest {

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    RedisTemplate studentsRedisTemplate;
    @Test
    public void testDelete() {
        Long delete = stringRedisTemplate.opsForHash().delete("hash", "name");
        System.out.println(delete);
    }
    @Test
    public void testPut() {
        try {
            stringRedisTemplate.opsForHash().put("hash","age","name");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testGet() {
        //从键中的哈希获取给定hashKey的值
        String o = (String) stringRedisTemplate.opsForHash().get("hash", "age");
        System.out.println(o);
    }
    @Test
    public void testHashKey() {
        //确定哈希hashKey是否存在
        Boolean aBoolean = stringRedisTemplate.opsForHash().hasKey("hash", "age");
        System.out.println(aBoolean);
        //从键中的哈希获取给定hashKey的值
        Set<Object> hash = stringRedisTemplate.opsForHash().keys("hash");
        System.out.println(hash);
    }
    @Test
    public void testEntries() {
        Map<Object, Object> hash = stringRedisTemplate.opsForHash().entries("hash");
        System.out.println(hash);
    }
}

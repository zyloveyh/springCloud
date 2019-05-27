package com.zy.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisZSetTest {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    RedisTemplate studentsRedisTemplate;

    @Test
    public void testAdd() {
        Boolean add = stringRedisTemplate.opsForZSet().add("zset", "z1", 13);
        stringRedisTemplate.opsForZSet().add("zset", "z2", 13);
        stringRedisTemplate.opsForZSet().add("zset", "z3", 14);
        stringRedisTemplate.opsForZSet().add("zset", "z4", 15);
        stringRedisTemplate.opsForZSet().add("zset", "z5", 16);
        System.out.println(add);
    }

    @Test
    public void testRemove() {
        Long remove = stringRedisTemplate.opsForZSet().remove("zset", "z3");
        //从有序集合中移除一个或者多个元素
        System.out.println(remove);
    }

    @Test
    public void testRank() {
        //返回有序集中指定成员的排名，其中有序集成员按分数值递增(从小到大)顺序排列
        Set<String> sets = stringRedisTemplate.opsForZSet().range("zset", 0, -1);
        Long rank = stringRedisTemplate.opsForZSet().rank("zset", "z2");
        System.out.println(sets);
        System.out.println(rank);

    }
}

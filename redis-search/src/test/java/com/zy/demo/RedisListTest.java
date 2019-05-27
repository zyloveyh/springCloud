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
public class RedisListTest {


    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    RedisTemplate studentsRedisTemplate;

    String[] strs = new String[]{"java", "php", "c#"};

    @Test
    public void testLeftPull() {
        stringRedisTemplate.opsForList().leftPush("list", "myname");
    }

    @Test
    public void testLeftPullAll() {
        stringRedisTemplate.opsForList().leftPushAll("list", "java", "fef");
    }

    @Test
    public void testset() {
        stringRedisTemplate.opsForList().set("list", 1, "python");
        //从0开始,设置下标为1的值为某某
    }

    @Test
    public void tesRemove() {
        System.out.println(stringRedisTemplate.opsForList().range("list", 0, -1));
        stringRedisTemplate.opsForList().remove("list", 1, "setValue");
        //将删除列表中存储的列表中第一次次出现的“setValue”。
//        Long remove (K key,long count, Object value);
//        从存储在键中的列表中删除等于值的元素的第一个计数事件。
//        计数参数以下列方式影响操作：
//        count> 0：删除等于从头到尾移动的值的元素。
//        count <0：删除等于从尾到头移动的值的元素。
//        count = 0：删除等于value的所有元素
        System.out.println(stringRedisTemplate.opsForList().range("list", 0, -1));
    }

    @Test
    public void tesIndex() {
        System.out.println(stringRedisTemplate.opsForList().index("list", 2));
        //根据下表获取列表中的值，下标是从0开始的
    }

    @Test
    public void testLeftPullObject() {
        Students student = Students.builder().address("shengz").name("zy").age(18).lastName("Y").teacher(2).build();
        studentsRedisTemplate.opsForList().leftPush("list", student);
        studentsRedisTemplate.opsForList().leftPush("list", student);
        Students list = (Students) studentsRedisTemplate.opsForList().leftPop("list");
        System.out.println(list);
    }
}

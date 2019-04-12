package com.zy.helloservice;

import com.zy.helloservice.dao.domain.Students;
import com.zy.helloservice.dao.mapper.StudentsMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloServiceApplicationTests {
    private static final Logger logger = LoggerFactory.getLogger(HelloServiceApplicationTests.class);

    @Autowired
    private StudentsMapper studentsMapper;

    @Test
    public void tsetmapper() {
        Students students = studentsMapper.selectById(3);
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
        System.out.println(students.getLastName());
    }

    @Test
    public void testinsert() {
        Students students = Students.builder().address("湖北").sex("男").age(23).name("zpl").teacher(2).lastName("厉害了").build();
        logger.info(students.toString());
        int insert = studentsMapper.insert(students);
        System.out.println(insert);
        System.out.println(students.getId());
    }
}
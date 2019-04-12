package com.zy.schoolrelation;

import com.netflix.discovery.converters.Auto;
import com.zy.schoolrelation.mp.entity.Students;
import com.zy.schoolrelation.mp.mapper.StudentsMapper;
import com.zy.schoolrelation.mp.service.IStudentsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SchoolrelationApplicationTests {

    @Autowired
    private StudentsMapper studentsMapper;

    @Autowired
    private IStudentsService studentsService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testStudentsMapper() {
        Students students = studentsMapper.selectById(5);
        System.out.println(students);
    }
}

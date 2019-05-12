/*
package com.zy.schoolrelation;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zy.schoolrelation.mp.entity.Students;
import com.zy.schoolrelation.mp.mapper.StudentsMapper;
import com.zy.schoolrelation.mp.service.IStudentsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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

    @Test
    public void testqueryWrapper() {
        QueryWrapper<Students> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "zpl")
                .ge("age", 19);
        List<Students> students = studentsMapper.selectList(queryWrapper);


     */
/*  更新
                int update = studentsMapper.update(
                Students.builder().age(23).lastName("hhaf").build(),
                new UpdateWrapper<Students>().set("age", 15).like("name","pl")
        );*//*


        List<Students> students1 = studentsMapper.selectList(
                new QueryWrapper<Students>()
                        .select("age", "id", "last_name").like("name", "pl"));
        System.out.println(students1);

    }

    @Test
    public void testUpdateWrapper() {

        List<Students> students = studentsMapper.selectList(
                new QueryWrapper<Students>().select("address"));
        System.out.println(students);

    }



}
*/

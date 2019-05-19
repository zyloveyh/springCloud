package com.zy.schoolrelation.mp.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.schoolrelation.mp.entity.Students;
import com.zy.schoolrelation.mp.mapper.StudentsMapper;
import com.zy.schoolrelation.mp.service.IStudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zy
 * @since 2019-04-10
 */
@Service
public class StudentsServiceImpl extends ServiceImpl<StudentsMapper, Students> implements IStudentsService {

    @Autowired
    private StudentsMapper studentsMapper;

    @DS("slave_1")
    public Students getById1(String key) {
        Students student = Students.builder().age(18).address("深圳").lastName("z").name("zu").sex("男").teacher(2).build();
         studentsMapper.insert(student);
        return studentsMapper.selectById(key);
    }

    @DS("slave_2")
    public Students getById2(String key) {
        return studentsMapper.selectById(key);
    }

    @DS("slave_1")
    @Transactional
    public Integer addstudent1() {
        Students student = Students.builder().age(18).address("深圳").lastName("z").name("zu").sex("男").teacher(2).build();
        return studentsMapper.insert(student);
    }

    @DS("slave_2")
    @Transactional
    public Integer addstudent2() {
        Students student = Students.builder().age(18).address("深圳").lastName("z").name("zu").sex("男").teacher(2).build();
        return studentsMapper.insert(student);
    }
}

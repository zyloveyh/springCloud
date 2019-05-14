package com.zy.schoolrelation.mp.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.schoolrelation.mp.entity.Students;
import com.zy.schoolrelation.mp.mapper.StudentsMapper;
import com.zy.schoolrelation.mp.service.IStudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
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
        return studentsMapper.selectById(key);
    }

    @DS("slave_2")
    public Students getById2(String key) {
        return studentsMapper.selectById(key);
    }
}

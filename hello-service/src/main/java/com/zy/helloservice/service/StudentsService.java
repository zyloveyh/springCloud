package com.zy.helloservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zy.helloservice.dao.domain.Students;
import com.zy.helloservice.dao.mapper.StudentsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class StudentsService {

    @Autowired
    private StudentsMapper studentsMapper;

    @Transactional
    public Students getStudentsByKey(Integer key) {

        return studentsMapper.selectById(key);
    }
}

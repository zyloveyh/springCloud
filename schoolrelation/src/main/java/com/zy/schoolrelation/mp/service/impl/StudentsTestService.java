package com.zy.schoolrelation.mp.service.impl;

import com.zy.schoolrelation.mp.mapper.StudentsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentsTestService {
    @Autowired
    private StudentsMapper studentsMapper;
    @Autowired
    private StudentsServiceImpl studentsService;

    @Transactional
    public void addStudent() {
        studentsService.addstudent1();
        studentsService.addstudent2();
    }
}

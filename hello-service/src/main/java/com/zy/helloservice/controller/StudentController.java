package com.zy.helloservice.controller;

import com.zy.helloservice.dao.domain.Students;
import com.zy.helloservice.dao.mapper.StudentsMapper;
import com.zy.helloservice.service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/student", produces = "application/json;charset=UTF-8")
public class StudentController {

    @Autowired
    private StudentsService studentsService;

    @Autowired
    private StudentsMapper studentsMapper;

    @RequestMapping(value = "/get/primary", method = RequestMethod.GET)
    public Students getStudents(Integer key) {
        System.out.println("dfaaadfdes");
        return studentsService.getStudentsByKey(key);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Students get(Integer key) {
        return studentsMapper.selectById(key);
    }
}

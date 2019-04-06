package com.zy.helloservice.controller;

import com.zy.helloservice.dao.domain.Students;
import com.zy.helloservice.service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentsService studentsService;

    @RequestMapping("/get/primary")
    public Students getStudents(Integer key) {
        return studentsService.getStudentsByKey(key);
    }
}

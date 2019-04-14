package com.zy.schoolrelation.mp.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.netflix.discovery.converters.Auto;
import com.zy.schoolrelation.mp.entity.Students;
import com.zy.schoolrelation.mp.mapper.StudentsMapper;
import com.zy.schoolrelation.mp.service.impl.StudentsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zy
 * @since 2019-04-10
 */
@RestController
@RequestMapping(value = "/mp/students", produces = "application/json;charset=UTF-8")
public class StudentsController {

    @Autowired
    private StudentsServiceImpl studentsService;

    @Autowired
    private StudentsMapper studentsMapper;

    @RequestMapping("getByKey")
    public Students getByKey(String key) {
        return studentsService.getById(key);
    }

    @RequestMapping("getByColumn")
    public Students getByColumn(String key) {
        /*return studentsMapper.selectList(
                new QueryWrapper<Students>()
                        .select("id", "name", "age", "address")
                        .eq("id", key)).get(0);*/


        return null;
    }
}

package com.zy.schoolrelation.mp.controller;


import com.zy.schoolrelation.config.Config;
import com.zy.schoolrelation.mp.entity.Students;
import com.zy.schoolrelation.mp.mapper.StudentsMapper;
import com.zy.schoolrelation.mp.service.impl.StudentsServiceImpl;
import com.zy.schoolrelation.mp.service.impl.StudentsTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
public class StudentsController extends HttpServlet {
    @Override
    public void init() throws ServletException {
        System.out.println("初始化");
        super.init();
    }

    @Override
    public void destroy() {
        System.out.println("结束");
        super.destroy();
    }

    @Autowired
    private StudentsServiceImpl studentsService;

    @Autowired
    private Config config;
    @Autowired
    private StudentsMapper studentsMapper;
    @Autowired
    private StudentsTestService testService;

    @RequestMapping("/getByKey1")
    @Cacheable(cacheNames = "zy1")
    public Students getByKey1(String key) {
        return studentsService.getById1(key);
    }

    @RequestMapping("/getByKey2")
    public Students getByKey2(String key) {
        return studentsService.getById2(key);
    }


    @RequestMapping("/addStudent")
    public void addStudent(String key) {
        testService.addStudent();
    }
    @RequestMapping("/getConfig")
    public String getConfig() {
        return config.getZyAge();
    }

    @RequestMapping("/localtion")
    public void locution(HttpServletRequest request, HttpServletResponse response) throws IOException, InterruptedException {
        String contextPath = request.getContextPath();
        System.out.println("contextPath:" + contextPath);

        //        Thread.sleep(1000);
//        response.sendRedirect("http://www.taobao.com");
    }

}

package com.zy.learning.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class Person {
    @Autowired
    Son son;

    String name;
    String age;
    private String address;
    public void test(){
        System.out.println(son.getClass().getTypeName());
    }
}

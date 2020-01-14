package com.zy.learning.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.validation.constraints.NotBlank;

@Controller
public class Person {
    @Autowired
    Son son;

    String name;
    @NotBlank
    String ageName;
    @NotBlank
    int intName;
private void sayhello(){
    System.out.println("person say hello");
}

protected void  say(){
    sayhello();
}
    private String address;
    public void test(){
        System.out.println(son.getClass().getTypeName());
    }
}

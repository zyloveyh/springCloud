package com.zy.learning.annotation;

import org.apache.ibatis.annotations.Mapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Mapper
public class Son  extends  Person{
    @NotNull
    @NotBlank
    private String name;

    @NotNull
    private Integer integer;

    @NotNull Person person;

    public Son() {
    }

    @Override
    protected void say() {
        super.say();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    private void sayhello(){
        System.out.println("son say hello");
    }

}

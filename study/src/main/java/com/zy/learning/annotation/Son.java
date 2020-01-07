package com.zy.learning.annotation;

import org.apache.ibatis.annotations.Mapper;

import javax.validation.constraints.NotNull;

@Mapper
public class Son {
    @NotNull
    private String name;

    public Son() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

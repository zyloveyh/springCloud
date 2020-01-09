package com.zy.learning.annotation;

import org.apache.ibatis.annotations.Mapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Mapper
public class Son {
    @NotNull
    @NotBlank
    private String name;

    @NotNull
    private Integer integer;

    @NotNull Person person;
    public Son() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

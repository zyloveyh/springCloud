package com.zy.learning.annotation.demotest.model;

import javax.validation.constraints.NotNull;

public class Base<Param> {

    @NotNull
    private Param param;

    private String name;
    @NotNull
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Param getParam() {
        return param;
    }

    public void setParam(Param param) {
        this.param = param;
    }
}

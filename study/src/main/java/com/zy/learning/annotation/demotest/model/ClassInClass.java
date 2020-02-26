package com.zy.learning.annotation.demotest.model;


import javax.validation.constraints.NotNull;

public class ClassInClass {
    private Integer age;
    @NotNull
    private String name;
    @NotNull
    private InnerClass innerClass;

    public ClassInClass() {
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InnerClass getInnerClass() {
        return innerClass;
    }

    public void setInnerClass(InnerClass innerClass) {
        this.innerClass = innerClass;
    }
}

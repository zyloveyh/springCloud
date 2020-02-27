package com.zy.learning.annotation.demotest.model;


import javax.validation.constraints.NotNull;

public class ClassInClass {
//    private Integer age;
//    @NotNull
//    private String name;
//    @NotNull
//    private InnerClass innerClass;

//    @NotNull
//    private ExecutionResult ee;
    @NotNull
    private TEmployee te;

    public TEmployee getTe() {
        return te;
    }

    public void setTe(TEmployee te) {
        this.te = te;
    }

    public ClassInClass() {
    }

}

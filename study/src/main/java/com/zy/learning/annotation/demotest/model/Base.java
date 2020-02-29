package com.zy.learning.annotation.demotest.model;

import javax.validation.constraints.NotNull;

public class Base<Param, TD> {

    @NotNull
    private Param param;

    public Param getParam() {
        return param;
    }

    public void setParam(Param param) {
        this.param = param;
    }
}

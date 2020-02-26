package com.zy.learning.annotation.demotest.model;

import javax.validation.constraints.NotNull;

public class InnerClass {
    @NotNull
    private Integer ageIN;
    private String nameIN;
    @NotNull
    private TEmployee tEmployee;

    public TEmployee gettEmployee() {
        return tEmployee;
    }

    public void settEmployee(TEmployee tEmployee) {
        this.tEmployee = tEmployee;
    }
    public InnerClass() {
    }

    public Integer getAgeIN() {
        return ageIN;
    }

    public void setAgeIN(Integer ageIN) {
        this.ageIN = ageIN;
    }

    public String getNameIN() {
        return nameIN;
    }

    public void setNameIN(String nameIN) {
        this.nameIN = nameIN;
    }
}

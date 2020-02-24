package com.zy.learning.annotation.demotest.model;

public class One<TE> {
    private TE te;
    private String name;

    public TE getTe() {
        return te;
    }

    public void setTe(TE te) {
        this.te = te;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

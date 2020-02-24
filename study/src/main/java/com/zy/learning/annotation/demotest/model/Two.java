package com.zy.learning.annotation.demotest.model;

public class Two<TT> {

    private TT tt;
    private String age;

    public TT getTt() {
        return tt;
    }

    public void setTt(TT tt) {
        this.tt = tt;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}

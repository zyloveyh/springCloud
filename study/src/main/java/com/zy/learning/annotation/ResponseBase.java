package com.zy.learning.annotation;

public class ResponseBase<T> {
    private  T t;
    private String string;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}

package com.zy.learning.enumType;

public enum SingleTon {
    INSTANCE;
    private String name;
    private String age;

    SingleTon() {
    }

    public String sayHello() {
        System.out.println("hello" +name);
        return "hello";
    }

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
}

package com.zy.learning.enumType;

enum Day {
    MONDAY("星期一"),
    TUESDAY("星期一"),
    WEDNESDAY("星期一"),
    THURSDAY("星期一"),
    FRIDAY("星期一"),
    SATURDAY("星期一"),
    SUNDAY("星期一");
    private String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    Day(String desc){
        this.desc = desc;
    }
}

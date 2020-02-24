package com.zy.learning.annotation.demotest.model;

public class ReturnParameterInfo {
    public static final String CUSTOM_TYPE = "customType";
    public static final String JAVA_TYPE = "javaType";

    private String type;
    private String returnName;
    private String value;

    public ReturnParameterInfo() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReturnName() {
        return returnName;
    }

    public void setReturnName(String returnName) {
        this.returnName = returnName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

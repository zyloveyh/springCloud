package com.zy.learning.annotation.demotest.model;

import javax.validation.constraints.NotNull;

public class StatementLine {
    public static final String FIELD="FieldAssignment";//属性赋值
    public static final String OBJECT="ObjectInstantiation";//对象实例化

    private String type;
    @NotNull
    private Object returnType ;
    private String returnName;
    private String process;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getReturnType() {
        return returnType;
    }

    public void setReturnType(Object returnType) {
        this.returnType = returnType;
    }

    public String getReturnName() {
        return returnName;
    }

    public void setReturnName(String returnName) {
        this.returnName = returnName;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    @Override
    public String toString() {
        return null;
    }
}

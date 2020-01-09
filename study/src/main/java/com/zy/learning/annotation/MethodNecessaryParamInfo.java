package com.zy.learning.annotation;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class MethodNecessaryParamInfo {
    /**
     * 方法名称
     */
    private String methodName;
    /**
     *  key: 类 全限定名 ; value:必须有值得属性名称
     */
    private Map<String, Set<String>> necessaryParamInfo;

    public MethodNecessaryParamInfo() {
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Map<String, Set<String>> getNecessaryParamInfo() {
        return necessaryParamInfo;
    }

    public void setNecessaryParamInfo(Map<String, Set<String>> necessaryParamInfo) {
        this.necessaryParamInfo = necessaryParamInfo;
    }

    @Override
    public String toString() {
        return "MethodNecessaryParamInfo{" +
                "methodName='" + methodName + '\'' +
                ", necessaryParamInfo=" + necessaryParamInfo +
                '}';
    }
}

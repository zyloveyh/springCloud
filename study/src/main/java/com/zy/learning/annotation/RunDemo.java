package com.zy.learning.annotation;

import com.zy.learning.annotation.demotest.model.ReturnParameterInfo;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import javax.validation.constraints.Size;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;

public class RunDemo {
    private final static String NEWLINE = java.lang.System.getProperty("line.separator");

    @Test
    public void testgetMethodValidParam() {
        StringBuilder testSuit = new StringBuilder();
        String targetClass = "com.zy.learning.annotation.demotest.controller.TEmployeeController";

        Map<String, Method> classDeclareMethods = ClassTypeUtil.getClassDeclareMethods(targetClass);
        for (Map.Entry<String, Method> stringMethodEntry : classDeclareMethods.entrySet()) {
            Method method = stringMethodEntry.getValue();
            Map<String, List<Parameter>> requireParameter = ClassTypeUtil.analysisMethod(method);
            Map<Parameter, ReturnParameterInfo> map = new HashMap<>();
            Map<Class, Integer> classNumMap = new HashMap<>();
            for (Parameter parameter : requireParameter.get(ClassTypeUtil.REQUIRE)) {

                ReturnParameterInfo rpi = ClassTypeUtil.generateParameter(parameter, ClassTypeUtil.LOW, classNumMap);
                map.put(parameter, rpi);
            }
            for (Map.Entry<Parameter, ReturnParameterInfo> returnInfoEntry : map.entrySet()) {
                ReturnParameterInfo rpi = returnInfoEntry.getValue();
                if (rpi.getType() != null && ReturnParameterInfo.CUSTOM_TYPE.equals(rpi.getType())) {
                    testSuit.append(rpi.getValue());
                }
            }
            map.putAll(getUnRequire(requireParameter));
            String parameterString = getParameterString(method, requireParameter, map);
            String name = "类名";


            String returnClass = "";
            String returnName = "";

            Class<?> returnType = method.getReturnType();

            if ("void".equalsIgnoreCase(returnType.getName())) {
                //没有返回值
                testSuit.append(ClassTypeUtil.INNER_BLOCK_SPACE + name + "." + stringMethodEntry.getKey() + "(" + parameterString + ");");
                testSuit.append(NEWLINE);
            } else {
                //有返回值
//                System.out.println(returnType);
                Type genericReturnType = method.getGenericReturnType();
                if (genericReturnType instanceof ParameterizedType) {
                    //返回值包含泛型
                    Type rawType = ((ParameterizedType) genericReturnType).getRawType();
                    returnClass = ClassTypeUtil.getClassSimpleByTypeName(rawType.getTypeName());
                    returnName = ClassTypeUtil.lowerFirstCapse(returnClass);
//                    String genericString = ClassTypeUtil.getGenericStringFromType(actualTypeArguments);
                    returnClass = ClassTypeUtil.getActualTypeStringByTypes(new Type[]{genericReturnType});

                } else {
                    //返回值 不含泛型
                    returnClass = returnType.getSimpleName();
                    returnName = ClassTypeUtil.lowerFirstCapse(returnClass);
                }
                testSuit.append(ClassTypeUtil.INNER_BLOCK_SPACE + returnClass + " " + returnName + " = " + name +
                        "." + stringMethodEntry.getKey() + "(" + parameterString + ");");
                testSuit.append(NEWLINE);
                testSuit.append(ClassTypeUtil.INNER_BLOCK_SPACE + "assertNotNull(" + returnName + ");");
                testSuit.append(NEWLINE);
            }


        }


        System.out.println(testSuit);
    }


    private Map<Parameter, ReturnParameterInfo> getUnRequire(Map<String, List<Parameter>> allParameter) {
        Map<Parameter, ReturnParameterInfo> result = new HashMap<>();
        List<Parameter> unReqireList = allParameter.get(ClassTypeUtil.UN_REQUIRE);
        for (Parameter parameter : unReqireList) {
            ReturnParameterInfo rpi = new ReturnParameterInfo();
            Class<?> type = parameter.getType();
            String randomValue = ClassTypeUtil.getRandomValue(type);
            if (null != randomValue) {
                rpi.setType(ReturnParameterInfo.JAVA_TYPE);
                rpi.setValue(randomValue);
                result.put(parameter, rpi);
            } else {
                rpi.setType(ReturnParameterInfo.CUSTOM_TYPE);
                rpi.setReturnName("null");
                result.put(parameter, rpi);
            }
        }
        return result;
    }

    private String getParameterString(Method method, Map<String, List<Parameter>> parameters, Map<Parameter,
            ReturnParameterInfo> map) {
        String parameterString = "";
        Parameter[] parameterArray = method.getParameters();
        for (Parameter parameter : parameterArray) {
            ReturnParameterInfo returnParameterInfo = map.get(parameter);
            if (ReturnParameterInfo.CUSTOM_TYPE.equals(returnParameterInfo.getType())) {
                parameterString += returnParameterInfo.getReturnName() + ", ";
            } else if (ReturnParameterInfo.JAVA_TYPE.equals(returnParameterInfo.getType())) {
                parameterString += returnParameterInfo.getValue() + ", ";
            } else {
                parameterString += "null, ";
            }
        }
        if (StringUtils.isEmpty(parameterString)) {
            return "";
        }
        parameterString = parameterString.trim().substring(0, parameterString.length() - 2);
        return parameterString;
    }

    @Test
    public void test2() throws ClassNotFoundException {
        String targetClass = "com.zy.learning.annotation.demotest.model.Base";
        Class clazz = Class.forName(targetClass);
        Field[] fs = clazz.getDeclaredFields(); // 得到所有的fields
        Map<String, Class> m = new HashMap<>();
        for (Field f : fs) {
            Class fieldClazz = f.getType(); // 得到field的class及类型全路径

            if (fieldClazz.isPrimitive()) continue;  //【1】 //判断是否为基本类型

//            if (fieldClazz.getName().startsWith("java.lang")) continue; //getName()返回field的类型全路径；


            Type fc = f.getGenericType(); // 关键的地方，如果是List类型，得到其Generic的类型

            if (fc == null) continue;

            if (fc instanceof ParameterizedType) // 【3】如果是泛型参数的类型
            {
                ParameterizedType pt = (ParameterizedType) fc;

                Class genericClazz = (Class) pt.getActualTypeArguments()[0]; //【4】 得到泛型里的class类型对象。

            }

        }
    }

    @Test
    public void test3() {
        System.out.println(String.class.isPrimitive());
        System.out.println(int.class.isPrimitive());
    }

    @Test
    public void test4() throws ClassNotFoundException {
        String targetClass = "com.zy.learning.annotation.demotest.model.TEmployee";
        Class clazz = Class.forName(targetClass);
        Field[] fs = clazz.getDeclaredFields(); // 得到所有的fields
        for (Field field : fs) {
            Size declaredAnnotation = field.getDeclaredAnnotation(Size.class);
            System.out.println(declaredAnnotation.min());
            System.out.println(declaredAnnotation.max());

        }
    }

    @Test
    public void test5() {
        String a = "\\";
        a = a.replace("\\", "\\\\");
        System.out.println(a);
    }


    @Test
    public void testInstanceOf() {
        List<String> list = new ArrayList<>();
        System.out.println(list instanceof Map);

    }
}

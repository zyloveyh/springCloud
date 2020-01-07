package com.zy.learning.annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;

public class ClassTypeUtil {
    private static Logger logger = LoggerFactory.getLogger(ClassTypeUtil.class);
    private static String controllerTypeName = "org.springframework.stereotype.Controller";
    private static String restControllerTypeName = "org.springframework.web.bind.annotation.RestController";
    private static String mapperTypeName = "org.apache.ibatis.annotations.Mapper";
    private static String autowiredTypeName = "org.springframework.beans.factory.annotation.Autowired";

    public static boolean controllerClass() {
        String targetClass = "com.zy.learning.annotation.Person";
        Class clazz = null;
        try {
            clazz = Class.forName(targetClass);
        } catch (ClassNotFoundException e) {
            logger.error("ClassNotFoundException class : " + targetClass);
            return false;
        }
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation annotation : annotations) {
            String typeName = annotation.annotationType().getTypeName();
            logger.error(typeName);
            if (controllerTypeName.equals(annotation.annotationType().getTypeName())
                    || restControllerTypeName.equals(annotation.annotationType().getTypeName())) {
                return true;
            }
        }
        return false;
    }

    public static boolean mapperClass() {
        String targetClass = "com.zy.learning.annotation.Son";
        Class clazz = null;
        try {
            clazz = Class.forName(targetClass);
        } catch (ClassNotFoundException e) {
            logger.error("ClassNotFoundException class : " + targetClass);
            return false;
        }
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation annotation : annotations) {
            String typeName = annotation.annotationType().getTypeName();
            if (mapperTypeName.equals(typeName)) {
                return true;
            }
        }
        return false;
    }

    public static List<FieldInfo> getAutowiredField() {
        List<FieldInfo> result = new ArrayList<>();
        String targetClass = "com.zy.learning.annotation.Person";
        Class clazz = null;
        try {
            clazz = Class.forName(targetClass);
        } catch (ClassNotFoundException e) {
            logger.error("ClassNotFoundException class : " + targetClass);
            return null;
        }
        System.out.println(lowerFirstCapse(clazz.getSimpleName()));

        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            Annotation[] annotations = declaredField.getAnnotations();
            if (annotations == null) {
                return null;
            }
            for (Annotation annotation : annotations) {
                String typeName = annotation.annotationType().getTypeName();
                if (autowiredTypeName.equals(typeName)) {
                    FieldInfo fieldInfo = new FieldInfo();
                    fieldInfo.setName(declaredField.getName());
                    fieldInfo.setTypeName(declaredField.getType().getName());
                    fieldInfo.setTypeSimpleName(declaredField.getType().getSimpleName());
                    result.add(fieldInfo);
                }
            }

        }
        return result;
    }

    public static class FieldInfo {
        private String name;
        private String typeSimpleName;
        private String typeName;

        public FieldInfo() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTypeSimpleName() {
            return typeSimpleName;
        }

        public void setTypeSimpleName(String typeSimpleName) {
            this.typeSimpleName = typeSimpleName;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        @Override
        public String toString() {
            return "FieldInfo{" +
                    "name='" + name + '\'' +
                    ", typeSimpleName='" + typeSimpleName + '\'' +
                    ", typeName='" + typeName + '\'' +
                    '}';
        }

    }


    public static String lowerFirstCapse(String str) {
        char[] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

    public static String getMethodValidParam() {
        String targetClass = "com.zy.learning.annotation.TestController";
        Class klass = null;
        try {
            klass = Class.forName(targetClass);
        } catch (ClassNotFoundException e) {
            logger.error("ClassNotFoundException class : " + targetClass);
            return null;
        }
        Method[] declaredMethods = klass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            //方法
            declaredMethod.setAccessible(true);
            //需要使用getGenericParameterTypes 才能获取到泛型的类型
            Type[] genericParameterTypes = declaredMethod.getGenericParameterTypes();
//            Parameter[] parameters = declaredMethod.getParameters();
            for (Type genericParameterType : genericParameterTypes) {
                if (genericParameterType instanceof ParameterizedType)
                    // 判断获取的类型是否是参数类型
                {
                    Type[] types = ((ParameterizedType) genericParameterType).getActualTypeArguments();// 强制转型为带参数的泛型类型，
                    // getActualTypeArguments()方法获取类型中的实际类型，如map<String,Integer>中的
                    // String，integer因为可能是多个，所以使用数组
                    for (Type type : types) {
                        System.out.println(type.getTypeName());
                    }

                }

            }
        }
        return null;
    }

    public static void main(String[] args) {
        getMethodValidParam();

//        getAutowiredField();
//        System.out.println(mapperClass());
//        System.out.println(controllerClass());


    }


}

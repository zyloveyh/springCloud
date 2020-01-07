package com.zy.learning.annotation;

import org.apache.catalina.User;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class ParmReturnType {
    public static void main(String[] args) throws NoSuchMethodException, SecurityException, ClassNotFoundException {
        // 获取指定方法的返回值泛型信息
        System.out.println("----------test02获取返回值类型-------------");
        Class klass = Class.forName("com.zy.learning.annotation.ParmReturnType");
        Method method = klass.getMethod("test02", null);// 根据方法名和参数获取test02方法
        Type type = method.getGenericReturnType();// 获取返回值类型
        if (type instanceof ParameterizedType) { // 判断获取的类型是否是参数类型
            System.out.println(type);
            Type[] typesto = ((ParameterizedType) type).getActualTypeArguments();// 强制转型为带参数的泛型类型，
            // getActualTypeArguments()方法获取类型中的实际类型，如map<String,Integer>中的
            // String，integer因为可能是多个，所以使用数组
            for (Type type2 : typesto) {
                System.out.println("泛型类型" + type2);
            }
        }


        System.out.println("------------------------------------------------------");
        // 获取指定方法的参数泛型信息
        System.out.println("----------获取指定方法的参数泛型信息-------------");
        Method methodtwo = ParmReturnType.class.getMethod("test01", Map.class, List.class);
        Type[] types = methodtwo.getGenericParameterTypes();// 获取参数，可能是多个，所以是数组
        for (Type type2 : types) {
            if (type2 instanceof ParameterizedType)// 判断获取的类型是否是参数类型
            {
                System.out.println("-------------------------------------------------");
                System.out.println(type);
                Type[] typetwos = ((ParameterizedType) type2).getActualTypeArguments();// 强制转型为带参数的泛型类型，
                // getActualTypeArguments()方法获取类型中的实际类型，如map<String,Integer>中的
                // String，integer因为可能是多个，所以使用数组
                for (Type type3 : typetwos) {
                    System.out.println("参数类型" + type3);
                }

            }
        }
    }

    // 带参数的方法Test01
    public static void test01(Map<String, Integer> map, List<User> list) {
    }

    // 带返回值的方法Test02
    public static Map<String, Integer> test02() {
        return null;
    }

}

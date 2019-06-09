package com.zy.learning.classtest;

import com.zy.learning.entity.Student;
import com.zy.learning.entity.Woman;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class TestClass {

    @Test
    public void testClass() throws ClassNotFoundException {
        Class<?> aClass = Class.forName("com.zy.learning.entity.Woman");
        String name = aClass.getName();
        String s = aClass.toGenericString();
        System.out.println(s);
    }

    /**
     * 反射
     *
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    @Test
    public void testReflect() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> aClass = Class.forName("com.zy.learning.entity.Woman");
        Constructor<?> constructor = aClass.getConstructor();
        Woman woman = (Woman) constructor.newInstance();
        woman.setAge("18");
        woman.setName("zy");
        System.out.println(woman);

        Constructor<?> constructor3Params = aClass.getConstructor(String.class, String.class, String.class, String.class);
        Woman o = (Woman) constructor3Params.newInstance("zy", "fsdf", "d", "f");
        System.out.println(o);
    }

    @Test
    public void testField() throws NoSuchFieldException, ClassNotFoundException {
        Class<?> clazz = Class.forName("com.zy.learning.entity.Student");
        //获取指定字段名称的Field类,注意字段修饰符必须为public而且存在该字段,
        // 否则抛NoSuchFieldException
        Field field = clazz.getField("age");
        System.out.println("field:" + field);

        //获取所有修饰符为public的字段,包含父类字段,注意修饰符为public才会获取
        Field fields[] = clazz.getFields();
        for (Field f : fields) {
            System.out.println("f:" + f.getName() + "->" + f.getDeclaringClass());
        }

        System.out.println("================getDeclaredFields====================");
        //获取当前类所字段(包含private字段),注意不包含父类的字段
        Field fields2[] = clazz.getDeclaredFields();
        for (Field f : fields2) {
            System.out.println("f2:" + f.getName() + "->" + f.getDeclaringClass());
        }
        //获取指定字段名称的Field类,可以是任意修饰符的自动,注意不包含父类的字段
        Field field2 = clazz.getDeclaredField("address");
        System.out.println("field2:" + field2);
    }

    @Test
    public void testFieldUse() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        //获取Class对象引用
        Class<?> clazz = Class.forName("com.zy.learning.entity.Student");

        Student st = (Student) clazz.newInstance();
//获取父类public字段并赋值
        Field ageField = clazz.getField("age");
        ageField.set(st, 18);
        Field nameField = clazz.getField("name");
        nameField.set(st, "Lily");


        ///////////////一下代码会报错//////////////
//只获取当前类的字段,不获取父类的字段
        Field descField = clazz.getDeclaredField("desc");
        descField.set(st, "I am student");
        Field scoreField = clazz.getDeclaredField("score");
//设置可访问，score是private的
        scoreField.setAccessible(true);
        scoreField.set(st, 88);
        System.out.println(st.toString());

//输出结果：Student{age=18, name='Lily ,desc='I am student', score=88}

//获取字段值
        System.out.println(scoreField.get(st));
// 88

    }


}

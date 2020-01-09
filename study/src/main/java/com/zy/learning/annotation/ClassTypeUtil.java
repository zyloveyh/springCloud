package com.zy.learning.annotation;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

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
    private static String VALID_TYPE_NAME = "javax.validation.Valid";
    private static String NOTNULL_TYPE_NAME = "javax.validation.constraints.NotNull";
    private static String NOTEMPTY_TYPE_NAME = "javax.validation.constraints.NotEmpty";
    private static String NOTBLANK_TYPE_NAME = "javax.validation.constraints.NotBlank";

    public boolean controllerClass() {
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

    public boolean mapperClass() {
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

    public List<FieldInfo> getAutowiredField() {
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

    public class FieldInfo {
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
                    ", typeName='" + typeName +
                    '}';
        }
    }


    public String lowerFirstCapse(String str) {
        char[] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

    public List<MethodNecessaryParamInfo> getMethodValidParam() throws ClassNotFoundException {
        List<MethodNecessaryParamInfo> result = new ArrayList<>();
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
            MethodNecessaryParamInfo mnp = new MethodNecessaryParamInfo();
            //需要使用getGenericParameterTypes 才能获取到泛型的类型
            Type[] genericParameterTypes = declaredMethod.getGenericParameterTypes();
            //获取参数类型
            Parameter[] parameters = declaredMethod.getParameters();

            Map<String, Set<String>> params = new HashMap<>();

            for (Parameter parameter : parameters) {
                Set<String> necessaryFields = new HashSet<>();
                if (null != params.get(parameter.getType().getTypeName())) {
                    //重复的类,则跳过
                    continue;
                }
                for (Annotation annotation : parameter.getAnnotations()) {
                    if (VALID_TYPE_NAME.contains(annotation.annotationType().getName())) {
                        necessaryFields = getNecessaryField(parameter.getType());
                        if (!CollectionUtils.isEmpty(necessaryFields)) {
                            //necessaryFields不为空,说明此类 某些字段必须设置值,加入集合中记录
                            params.put(parameter.getType().getTypeName(), necessaryFields);
                        }
                        break;
                    }
                }
                /**
                 * 泛型下是否有必须要赋值的属性
                 */
                Type parameterizedType = parameter.getParameterizedType();
                if (parameterizedType instanceof ParameterizedType) {
                    //判断获取的类型是否是参数类型
                    ParameterizedType pt = (ParameterizedType) parameterizedType;
                    //actualTypeArguments: 泛型的参数,可能会是多个 例如: Map 集合
                    Type[] actualTypeArguments = pt.getActualTypeArguments();
                    for (Type actualTypeArgument : actualTypeArguments) {
                        if (null != params.get(actualTypeArgument.getTypeName())) {
                            //重复的类,跳过
                            continue;
                        }
                        Set<String> actualParamField = getNecessaryField((Class) actualTypeArgument);
                        if (!CollectionUtils.isEmpty(actualParamField)) {
                            //necessaryFields不为空,说明此类 某些字段必须设置值,加入集合中记录
                            params.put(actualTypeArgument.getTypeName(), actualParamField);
                        }
                    }

                }
            }

            mnp.setMethodName(declaredMethod.getName());
            mnp.setNecessaryParamInfo(params);
            result.add(mnp);
        }
        System.out.println(result);
        return result;
    }

    /**
     * 根据参数 的 类,返回必须设置值的属性名称
     *
     * @param paramType Class
     * @return Set<String> 必须设置值的属性名称的集合
     */
    private Set<String> getNecessaryField(Class paramType) {
        Field[] declaredFields = paramType.getDeclaredFields();
        Set<String> fieldName = new HashSet<>();
        for (Field declaredField : declaredFields) {
            Annotation[] fieldAnnotations = declaredField.getAnnotations();
            for (Annotation fieldAnnotation : fieldAnnotations) {
                if (NOTNULL_TYPE_NAME.contains(fieldAnnotation.annotationType().getName()) ||
                        NOTEMPTY_TYPE_NAME.contains(fieldAnnotation.annotationType().getName()) ||
                        NOTBLANK_TYPE_NAME.contains(fieldAnnotation.annotationType().getName())
                ) {
                    fieldName.add(declaredField.getName());
                    break;
                }
            }
        }
        return fieldName;
    }

    @Test
    public void main() throws ClassNotFoundException {


        String line = "taskAttributeController0.insert(baseRequest0, (TaskAttribute) null, \"\\u53C2\\u6570\\u6821\\u9A8C\\u5931\\u8D25\", \"\\u53C2\\u6570\\u6821\\u9A8C\\u5931\\u8D25\");";
        String mainClass = "taskAttributeController0";
        if (line.contains(mainClass)) {
            int start = line.indexOf(mainClass);
            int end = line.indexOf("(");
            System.out.println(start);
            System.out.println(end);
        }
//        getMethodValidParam();


//        getAutowiredField();
//        System.out.println(mapperClass());
//        System.out.println(controllerClass());


    }


}

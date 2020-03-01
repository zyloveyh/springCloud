package com.zy.learning.annotation;

import com.zy.learning.annotation.demotest.controller.TEmployeeController;
import com.zy.learning.annotation.demotest.model.ReturnParameterInfo;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;
import java.util.regex.Pattern;

public class ClassTypeUtil {
    public final static Logger logger = LoggerFactory.getLogger(ClassTypeUtil.class);
    public final static String controllerTypeName = "org.springframework.stereotype.Controller";
    public final static String restControllerTypeName = "org.springframework.web.bind.annotation.RestController";
    public final static String mapperTypeName = "org.apache.ibatis.annotations.Mapper";
    public final static String autowiredTypeName = "org.springframework.beans.factory.annotation.Autowired";
    public final static String VALID_TYPE_NAME = "javax.validation.Valid";
    public final static String NOTNULL_TYPE_NAME = "javax.validation.constraints.NotNull";
    public final static String NOTEMPTY_TYPE_NAME = "javax.validation.constraints.NotEmpty";
    public final static String NOTBLANK_TYPE_NAME = "javax.validation.constraints.NotBlank";

    public final static String MAIN_CLASS = "main";


    public final static List<Class> CLASS_ARRAY = Arrays.asList(byte.class, Byte.class, int.class, Integer.class,
            long.class, Long.class, float.class, Float.class, double.class, Double.class, String.class, Boolean.class,
            char.class, Character.class);
    public final static String SIZE_TYPE_NAME = "javax.validation.constraints.Size";
    public final static String REQUIRE = "require";
    public final static String UN_REQUIRE = "unRequire";
    public static final String METHOD_SPACE = "  ";
    public static final String BLOCK_SPACE = "    ";
    public static final String INNER_BLOCK_SPACE = "      ";
    public static final String INNER_INNER_BLOCK_SPACE = "        ";
    public static final String INNER_INNER_INNER_BLOCK_SPACE = "          ";
    public static final String LOW = "low";
    public static final String MIDDLE = "middle";
    public static final String HEIGHT = "height";
    private final static String NEWLINE = java.lang.System.getProperty("line.separator");

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

    public static List<FieldInfo> getAutowiredField(String targetClass) {
        List<FieldInfo> result = new ArrayList<>();
        if (null == targetClass) {
            targetClass = "com.zy.learning.annotation.Person";

        }
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
                    ", typeName='" + typeName +
                    '}';
        }
    }


    /*
       首字母小写的方法
       如果我们需要把一个大写字母转化成小写字母，那么我们只需要把这个 char 字符的ascii码值加上 32
        */
    public static String lowerFirstCapse(String str) {
        char[] chars = str.toCharArray();
        if (chars[0] <= 90 && chars[0] >= 65) {
            chars[0] += 32;
        }
        return String.valueOf(chars);
    }

    /*
    首字母大写的方法
    如果我们需要把一个小写字母转化成大写字母，那么我们只需要把这个 char 字符的ascii码值减上 32
 */
    public static String upperFirstCapse(String str) {
        char[] chars = str.toCharArray();
        if (chars[0] <= 122 && chars[0] >= 97) {
            chars[0] -= 32;
        }
        return String.valueOf(chars);
    }

    public Map<String, Map<String, Set<Field>>> getMethodValidParam(String targetClass) {
        Map<String, Map<String, Set<Field>>> result = new HashMap<>();

        Map<String, Method> classDeclareMethods = getClassDeclareMethods(targetClass);

        if (classDeclareMethods == null) return null;

        for (String s : classDeclareMethods.keySet()) {
            Method declaredMethod = classDeclareMethods.get(s);
            //需要使用getGenericParameterTypes 才能获取到泛型的类型
            Type[] genericParameterTypes = declaredMethod.getGenericParameterTypes();
            //获取参数类型
            Parameter[] parameters = declaredMethod.getParameters();

            Map<String, Set<Field>> params = new HashMap<>();

            for (Parameter parameter : parameters) {
                Set<Field> necessaryFields = new HashSet<>();
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

                        /**
                         * 泛型下是否有必须要赋值的属性
                         */
                        Type parameterizedType = parameter.getParameterizedType();

                        if (parameterizedType instanceof ParameterizedType) {
                            //判断获取的类型是否是参数类型
                            ParameterizedType pt = (ParameterizedType) parameterizedType;
                            String typeName = pt.getRawType().getTypeName();
                            //actualTypeArguments: 泛型的参数,可能会是多个 例如: Map 集合
                            Type[] actualTypeArguments = pt.getActualTypeArguments();
                            getGenericToParams(params, actualTypeArguments);
                        }

                        break;
                    }
                }

            }
            result.put(declaredMethod.getName(), params);
        }
        return result;
    }

    /**
     * 获取 Class 文件中的所有方法(包含私有方法)
     *
     * @param targetClass
     * @return
     */
    public static Map<String, Method> getClassDeclareMethods(String targetClass) {
        Class klass = null;
        try {
            klass = Class.forName(targetClass);
        } catch (ClassNotFoundException e) {
            logger.error("ClassNotFoundException class : " + targetClass);
            return null;
        }
        Method[] declaredMethods = klass.getDeclaredMethods();

        Map<String, Method> methodMap = new HashMap<>();
        for (Method declaredMethod : declaredMethods) {
            methodMap.put(declaredMethod.getName(), declaredMethod);
        }
        return methodMap;
    }


    private void getGenericToParams(Map<String, Set<Field>> params, Type[] types) {
        for (Type type : types) {
            if (type instanceof ParameterizedTypeImpl) {
                //其内部还有泛型 例如: List<Map<T,T>>
                getGenericToParams(params, ((ParameterizedTypeImpl) type).getActualTypeArguments());
            } else {
                Set<Field> actualParamField = getNecessaryField((Class) type);
                if (!CollectionUtils.isEmpty(actualParamField)) {
                    //necessaryFields不为空,说明此类 某些字段必须设置值,加入集合中记录
                    params.put(type.getTypeName(), actualParamField);
                }
            }
        }
    }

    /**
     * 根据参数 的 类,返回必须设置值的属性名称
     *
     * @param paramType Class
     * @return Set<String> 必须设置值的属性名称的集合
     */
    private static Set<Field> getNecessaryField(Class paramType) {
        Field[] declaredFields = paramType.getDeclaredFields();
        Set<Field> fieldName = new HashSet<>();
        for (Field declaredField : declaredFields) {
            Annotation[] fieldAnnotations = declaredField.getAnnotations();
            for (Annotation fieldAnnotation : fieldAnnotations) {
                if (NOTNULL_TYPE_NAME.contains(fieldAnnotation.annotationType().getName()) ||
                        NOTEMPTY_TYPE_NAME.contains(fieldAnnotation.annotationType().getName()) ||
                        NOTBLANK_TYPE_NAME.contains(fieldAnnotation.annotationType().getName())
                ) {
                    fieldName.add(declaredField);
                    break;
                }
            }
        }
        return fieldName;
    }

    private Map<String, Set<Field>> getNullFieldMap(String[] lines, Map<String, Set<Field>> fieldSetMap) {
        Map<String, Set<Field>> nullFieldMap = new HashMap<>();
        if (CollectionUtils.isEmpty(fieldSetMap)) {
            return nullFieldMap;
        }
        for (String s : fieldSetMap.keySet()) {
//            logger.error("INFO : s:" + s);
            String[] split = s.split("\\.");
            //获取到参数对象的类名
            String className = split[split.length - 1];
            Set<Field> fieldSet = fieldSetMap.get(s);
            boolean param = false;
            Set<Field> nullFieldSet = new HashSet<>();
            for (Field field : fieldSet) {
                boolean paramField = false;
                String fieldName = field.getName();
                for (String perLine : lines) {
                    // 判断是否有对这个 属性的值 进行赋值
                    String patternParam = ".*new\\s+" + ClassTypeUtil.upperFirstCapse(className) + "\\(.*";
                    String patternField = ".*" + ClassTypeUtil.lowerFirstCapse(className) + ".*" + "set" + fieldName + "\\(";
                    if (Pattern.matches(patternField, perLine)) {
                        //有对字段进行赋值
                        paramField = true;
//                        logger.error("INFO : 字段赋值 " + perLine);
                    }
                    if (Pattern.matches(patternParam, perLine)) {
                        //有实例化参数
                        param = true;
//                        logger.error("INFO : 参数实例化 " + perLine);
                    }
                }
                if (param && (!paramField)) {
                    //参数有实例化,当未对某个字段赋值
                    nullFieldSet.add(field);
                }

            }
            if (!CollectionUtils.isEmpty(nullFieldSet)) {
                nullFieldMap.put(s, nullFieldSet);
            }

        }
//        for (String s : nullFieldMap.keySet()) {
//            Set<Field> fieldSet = nullFieldMap.get(s);
//            for (Field field : fieldSet) {
//                logger.error("INFO : analyze result : Param: " + s + " field : " + field.getName());
//            }
//        }
        return nullFieldMap;
    }

    /**
     * 方法中的参数,哪些需要校验属性,哪些不需要
     *
     * @param method
     * @return
     */
    public static Map<String, List<Parameter>> analysisMethod(Method method) {
        Map<String, List<Parameter>> result = new HashMap<>();
        result.put(REQUIRE, new ArrayList<Parameter>());
        result.put(UN_REQUIRE, new ArrayList<Parameter>());
        //获取方法内的参数
        Parameter[] parameters = method.getParameters();
        for (Parameter parameter : parameters) {
            boolean require = false;
            //对每个参数进行操作
            for (Annotation annotation : parameter.getAnnotations()) {
                if (ClassTypeUtil.VALID_TYPE_NAME.contains(annotation.annotationType().getName())) {
                    require = true;
                    break;
                }
            }
            if (require) {
                result.get(REQUIRE).add(parameter);
            } else {
                result.get(UN_REQUIRE).add(parameter);
            }
        }
        return result;
    }

    public static Type[] getActualTypeArguments(Parameter parameter) {
        Type parameterizedType = parameter.getParameterizedType();
        if (parameterizedType instanceof ParameterizedType) {
            //判断获取的类型是否是参数类型
            ParameterizedType pt = (ParameterizedType) parameterizedType;
            //actualTypeArguments: 泛型的参数,可能会是多个 例如: Map 集合
            Type[] actualTypeArguments = pt.getActualTypeArguments();
            return actualTypeArguments;
        }
        return null;
    }

    /**
     * 判断此类是否属于 Collection 或者 Map
     *
     * @param klass
     * @return
     */
    private static boolean isCollectionMap(Class klass) {
        if (List.class.equals(klass) || ArrayList.class.equals(klass) || Set.class.equals(klass) || HashSet.class.equals(klass)
                || Queue.class.equals(klass) || Map.class.equals(klass) || HashMap.class.equals(klass)) {
            return true;
        }
        return false;
    }

    public static StringBuilder generatePrarmeterField(Parameter parameter, String name, String level,
                                                       Map<Class, Integer> classNumMap) {
        StringBuilder result = new StringBuilder();
        //parameter 是 Collection 或者 Map 提示用户自己输入值
        if (isCollectionMap(parameter.getType())) {
            String line = INNER_BLOCK_SPACE + "//TODO Add your value here ...";
            result.append(line);
            result.append(NEWLINE);
            result.append(NEWLINE);
            result.append(NEWLINE);
            return result;
        }
        Map<String, Map<String, Field>> stringMapMap = analysisParameter(parameter);
        Map<String, Field> requireFieldMap = stringMapMap.get(REQUIRE);
        for (String key : requireFieldMap.keySet()) {
            Field field = requireFieldMap.get(key);
            Type genericType = field.getGenericType();
            if (genericType instanceof TypeVariable) {
                //表明此类型是泛型类
                ParameterizedType pt = (ParameterizedType) parameter.getParameterizedType();
                //actualTypeArguments: 泛型的参数,可能会是多个 例如: Map 集合
                //泛型的实际参数
                Type[] actualTypeArguments = pt.getActualTypeArguments();

                //第一种情况: 只要一个泛型
                if (actualTypeArguments.length == 1) {
                    //只有一个值,说明,此字段就是泛型
                    Class type = (Class) actualTypeArguments[0];
                    generateParamFieldByField(name, level, classNumMap, result, field, type);

                }

                //第二种情况: 有多个泛型
                if (actualTypeArguments.length > 1) {
                    //泛型,字段属性
                    Class klass = (Class) pt.getRawType();
                    TypeVariable[] typeParameters = klass.getTypeParameters();
                    int size = typeParameters.length;
                    for (int i = 0; i < size; i++) {
                        if (typeParameters[i].getName().equalsIgnoreCase(field.getName())) {
                            //找到了字段 field 对应的实际泛型
                            Class type = (Class) actualTypeArguments[i];
                            generateParamFieldByField(name, level, classNumMap, result, field, type);
                        }
                    }

                }


            } else {
                //非泛型
                Class<?> type = field.getType();
                generateParamFieldByField(name, level, classNumMap, result, field, type);

            }
        }
        return result;
    }

    /**
     * 实现给接口参数 赋值字段值
     *
     * @param name        需要赋值的实例对象名称
     * @param level       生成属性值得级别 low middle height
     * @param classNumMap 已生成的对象集合
     * @param result      需要的结果值
     * @param field       字段信息
     * @param type        此字段真实的类型,因为如果是泛型,考虑到泛型擦除,那么用Field只能获取到Object 类
     */
    public static void generateParamFieldByField(String name, String level, Map<Class, Integer> classNumMap, StringBuilder result, Field field, Class type) {
        if (CLASS_ARRAY.contains(type)) {
            //非自定义类型,可以进行赋值
            String fieldName = field.getName();
            String value = getValue(field, level, type);
            result.append(INNER_BLOCK_SPACE + name + ".set" + ClassTypeUtil.upperFirstCapse(fieldName) + "(" + value + ");");
            result.append(NEWLINE);
            return;
        }

        //自定义类型,需要先new 再对字段进行赋值
        String fieldName = field.getName();
        ReturnParameterInfo rpi = generateNewObjectByClassType(type, level, classNumMap);
        if (null != rpi) {
            result.append(rpi.getValue());
            result.append(INNER_BLOCK_SPACE + name + ".set" + ClassTypeUtil.upperFirstCapse(fieldName) + "(" + rpi.getReturnName() + ");");
            result.append(NEWLINE);
        }

    }

    /**
     * 根据Class 类型生成 实例化的代码
     *
     * @param klass
     * @return
     */
    public static ReturnParameterInfo generateNewObjectByClassType(Class klass, String level,
                                                                   Map<Class, Integer> classNumMap) {
        if (isCollectionMap(klass)) {
            //TODO 此处可以完善,情况比较少见.暂时先按如下处理
            //是集合类型 ,暂且先不生成
            return null;
        }
        ReturnParameterInfo result = new ReturnParameterInfo();
        StringBuilder stringBuilder = new StringBuilder();
        String objectName = klass.getSimpleName();
        String returnName = getClassReturnName(klass, classNumMap);
        stringBuilder.append(INNER_BLOCK_SPACE + objectName + " " + returnName + " = new " + objectName + "();");
        stringBuilder.append(NEWLINE);
//        logger.error("INFO : analysisField Class ->" + klass.getName());
        Map<String, Map<String, Field>> stringMapMap = analysisField(klass);
        Map<String, Field> requireFieldMap = stringMapMap.get(REQUIRE);
//        logger.error("INFO : Class->" + klass.getSimpleName() + " requireField : " + requireFieldMap);
        for (Map.Entry<String, Field> stringFieldEntry : requireFieldMap.entrySet()) {
            Field field = stringFieldEntry.getValue();
            Class<?> type = field.getType();
            if (CLASS_ARRAY.contains(type)) {
                String value = getRandomValue(type);
                String line =
                        INNER_BLOCK_SPACE + returnName + ".set" + ClassTypeUtil.upperFirstCapse(stringFieldEntry.getKey()) +
                                "(" + value + ");";
                stringBuilder.append(line);
                stringBuilder.append(NEWLINE);
            } else {
                ReturnParameterInfo returnInfo = generateNewObjectByClassType(type, level, classNumMap);
                if (null != returnInfo) {
                    stringBuilder.append(returnInfo.getValue());
                }
            }
        }

        result.setType(ReturnParameterInfo.CUSTOM_TYPE);
        result.setReturnName(returnName);
        result.setValue(stringBuilder.toString());
        return result;
    }

    /**
     * 获取 Class 实例化对应的名称
     *
     * @param type
     * @param classNumMap
     * @return
     */
    public static String getClassReturnName(Class type, Map<Class, Integer> classNumMap) {
        Integer integer = classNumMap.get(type);
        if (null == integer) {
            classNumMap.put(type, 0);
            return ClassTypeUtil.lowerFirstCapse(type.getSimpleName()) + "0";
        }
        integer++;
        classNumMap.put(type, integer);
        return ClassTypeUtil.lowerFirstCapse(type.getSimpleName()) + integer;
    }


    public static String getValue(Field field, String level, Class type) {
        Size declaredAnnotation = field.getDeclaredAnnotation(Size.class);
        if (null == declaredAnnotation) {
            return getRandomValue(type);
        }
        int min = 0;
        int max = Integer.MAX_VALUE;
        min = declaredAnnotation.min();
        max = declaredAnnotation.max();
        return getRandomValueByLevel(type, level, min, max);
    }

    public static String getParamReturnName(Parameter parameter) {
        Class<?> type = parameter.getType();
        String simpleName = type.getSimpleName();
        String returnName = ClassTypeUtil.lowerFirstCapse(simpleName) + "0";
        return returnName;
    }

    public static ReturnParameterInfo generateParameter(Parameter parameter, String level, Map<Class, Integer> classNumMap) {
        ReturnParameterInfo rpi = new ReturnParameterInfo();
        StringBuilder line = new StringBuilder();
        Class<?> type = parameter.getType();
        String simpleName = type.getSimpleName();
        Type[] types = getActualTypeArguments(parameter);

        String returnName = getClassReturnName(parameter.getType(), classNumMap);

        String randomValue = ClassTypeUtil.getRandomValue(type);
        if (null != randomValue) {
            rpi.setType(ReturnParameterInfo.JAVA_TYPE);
            rpi.setReturnName(randomValue);
            rpi.setValue(randomValue);
            return rpi;
        }

        if (null == types) {
            //非泛型参数
            line.append(INNER_BLOCK_SPACE + simpleName + " " + returnName + " = new " + simpleName + "();");
        } else {
            //泛型参数
            String actualTypeString = getActualTypeStringByTypes(types);


            line.append(INNER_BLOCK_SPACE + simpleName + "<" + actualTypeString + "> " + returnName +
                    " = new " + simpleName + "<" + actualTypeString + ">" + "();");
        }

        line.append(NEWLINE);

        //生成此参数的字段属性值
        line.append(generatePrarmeterField(parameter, returnName, level, classNumMap));
//        System.out.println(line);
        rpi.setType(ReturnParameterInfo.CUSTOM_TYPE);
        rpi.setReturnName(returnName);
        rpi.setValue(line.toString());
        return rpi;
    }

    /**
     * 根据Type 获取泛型字符串(< > 内的字符串内容)
     * @param types  ParameterizedType.getActualTypeArguments 的结果
     * @return
     */
    public static String getActualTypeStringByTypes(Type[] types) {
        String actualTypeString = "";
        for (Type type : types) {
            if (type instanceof ParameterizedType) {
                //此类型还包含泛型
                String typeSimpleName = getClassSimpleByTypeName(((ParameterizedType) type).getRawType().getTypeName());

                ParameterizedType parameterizedType = (ParameterizedType) type;
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();

                String TypeString = getActualTypeStringByTypes(actualTypeArguments);
                actualTypeString += typeSimpleName + "<" + TypeString + ">, ";
            }else{
                //不包含泛型
                String typeSimpleName = getClassSimpleByTypeName(type.getTypeName());
                actualTypeString+=typeSimpleName+", ";
            }
        }
        actualTypeString = actualTypeString.trim().substring(0, actualTypeString.length() - 2);
        return actualTypeString;
    }

    public static Map<String, Map<String, Field>> analysisParameter(Parameter parameter) {
        Map<String, Map<String, Field>> result = new HashMap<>();
        result.put(REQUIRE, new HashMap<String, Field>());
        result.put(UN_REQUIRE, new HashMap<String, Field>());

        Class<?> paramType = parameter.getType();
        Field[] declaredFields = paramType.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            boolean require = false;
            Annotation[] fieldAnnotations = declaredField.getAnnotations();
            for (Annotation fieldAnnotation : fieldAnnotations) {
                if (NOTNULL_TYPE_NAME.contains(fieldAnnotation.annotationType().getName()) ||
                        NOTEMPTY_TYPE_NAME.contains(fieldAnnotation.annotationType().getName()) ||
                        NOTBLANK_TYPE_NAME.contains(fieldAnnotation.annotationType().getName())
                ) {
                    require = true;
                    break;
                }
            }
            if (require) {
                result.get(REQUIRE).put(declaredField.getName(), declaredField);
            } else {
                result.get(UN_REQUIRE).put(declaredField.getName(), declaredField);
            }
        }
        return result;
    }

    /**
     * 对某个对象的属性进行分析
     *
     * @param klass
     * @return
     */
    public static Map<String, Map<String, Field>> analysisField(Class klass) {
        Map<String, Map<String, Field>> result = new HashMap<>();
        result.put(REQUIRE, new HashMap<>());
        result.put(UN_REQUIRE, new HashMap<>());
        Field[] declaredFields = klass.getDeclaredFields();
//        logger.error("INFO : class->" + klass.getSimpleName()+" "+klass.getTypeName() +" fields-> "+Arrays.toString(declaredFields));
        for (Field field : declaredFields) {
            boolean require = false;
            for (Annotation annotation : field.getAnnotations()) {
                if (NOTNULL_TYPE_NAME.contains(annotation.annotationType().getName()) ||
                        NOTEMPTY_TYPE_NAME.contains(annotation.annotationType().getName()) ||
                        NOTBLANK_TYPE_NAME.contains(annotation.annotationType().getName())
                ) {
                    require = true;
                    break;
                }
            }
            if (require) {
                result.get(REQUIRE).put(field.getName(), field);
            } else {
                result.get(UN_REQUIRE).put(field.getName(), field);
            }
//            用户使用的注解可能不同
//            NotNull nullAnnotation = field.getAnnotation(NotNull.class);
//            NotBlank blankAnnotation = field.getAnnotation(NotBlank.class);
//            NotEmpty emptyAnnotation = field.getAnnotation(NotEmpty.class);
//            if (null == nullAnnotation && null == blankAnnotation && null == emptyAnnotation) {
//                result.get(UN_REQUIRE).put(field.getName(), field);
//            } else {
//                result.get(REQUIRE).put(field.getName(), field);
//            }
        }
        return result;
    }

    public static Map<String, Map<String, Field>> getAllFields(Parameter parameter) {
        Map<String, Map<String, Field>> result = new HashMap<>();


        /**
         * 泛型下是否有必须要赋值的属性
         */
        Type parameterizedType = parameter.getParameterizedType();


        if (parameterizedType instanceof ParameterizedType) {
            //判断获取的类型是否是参数类型

            ParameterizedType pt = (ParameterizedType) parameterizedType;
            //actualTypeArguments: 泛型的参数,可能会是多个 例如: Map 集合
            //泛型的实际参数
            Type[] actualTypeArguments = pt.getActualTypeArguments();
            //泛型,字段属性
            Class klass = (Class) pt.getRawType();
            TypeVariable[] typeParameters = klass.getTypeParameters();

            int size = typeParameters.length;
            for (int i = 0; i < size; i++) {

            }
        }


        return result;

    }


    public static String getRandomValue(Class type) {
        if (type.equals(byte.class) || type.equals(Byte.class)) {
            return String.valueOf(RandomUtil.getRandomForIntegerBounded(-128, 127));
        }
        if (type.equals(int.class) || type.equals(Integer.class)) {
            return String.valueOf(RandomUtils.nextInt());
        }
        if (type.equals(long.class) || type.equals(Long.class)) {
            return String.valueOf(RandomUtils.nextLong());
        }
        if (type.equals(float.class) || type.equals(Float.class)) {
            return String.valueOf(RandomUtils.nextFloat());
        }
        if (type.equals(double.class) || type.equals(Double.class)) {
            return String.valueOf(RandomUtils.nextDouble());
        }
        if (type.equals(String.class)) {
            String randomString = RandomStringUtils.randomAscii(RandomUtil.getRandomForIntegerBounded(6, 10));
            randomString = randomString.replace("\\", "");
            randomString = randomString.replace("\"", "\\\"");
            return "\"" + randomString + "\"";
        }
        if (type.equals(Boolean.class)) {
            return String.valueOf(RandomUtils.nextBoolean());
        }

        return null;
    }

    private static String getRandomValueByLevel(Class type, String level, int min, int max) {

        if (type.equals(byte.class) || type.equals(Byte.class)) {
            return String.valueOf(randomByteValue(min, max, level));
        }
        if (type.equals(int.class) || type.equals(Integer.class)) {
            return String.valueOf(randomIntegerValue(min, max, level));
        }
        if (type.equals(long.class) || type.equals(Long.class)) {
            //TODO 按level 生成
            return String.valueOf(RandomUtils.nextLong());
        }
        if (type.equals(float.class) || type.equals(Float.class)) {
            //TODO 按level 生成
            return String.valueOf(RandomUtils.nextFloat());
        }
        if (type.equals(double.class) || type.equals(Double.class)) {
            //TODO 按level 生成
            return String.valueOf(RandomUtils.nextDouble());
        }
        if (type.equals(String.class)) {
            String randomString = RandomUtil.getRandomStringByLevel(min, max, level);
            randomString = randomString.replace("\\", "");
            randomString = randomString.replace("\"", "\\\"");
            return "\"" + randomString + "\"";

        }
        if (type.equals(Boolean.class)) {
            return String.valueOf(RandomUtils.nextBoolean());
        }
        return null;
    }

    private static Byte randomByteValue(Integer min, Integer max, String level) {
        if ((min < -128 || max > 127)) {
            return RandomUtil.getRandomIntegerValueByLevel(-128, 127, level).byteValue();
        }
        return RandomUtil.getRandomIntegerValueByLevel(min, max, level).byteValue();
    }

    private static Integer randomIntegerValue(Integer min, Integer max, String level) {
        //TODO 修改min  max 的情况
        return RandomUtil.getRandomIntegerValueByLevel(min, max, level);
    }


    public Set<Class> getFieldClassImport(Class targetClass) {
        Set<Class> result = new HashSet<>();
        //获取 方法
        Method[] methods = targetClass.getDeclaredMethods();
        for (Method method : methods) {
            Map<String, List<Parameter>> stringListMap = analysisMethod(method);
            List<Parameter> parameterList = stringListMap.get(REQUIRE);
            for (Parameter parameter : parameterList) {
                Class<?> type = parameter.getType();
                recursionField(type, result);
            }
        }


        return result;
    }

    private void recursionField(Class klass, Set<Class> result) {
        Map<String, Map<String, Field>> stringMapMap = analysisField(klass);
        Map<String, Field> requireFieldMap = stringMapMap.get(REQUIRE);
        for (Map.Entry<String, Field> stringFieldEntry : requireFieldMap.entrySet()) {
            Field field = stringFieldEntry.getValue();
            Class<?> type = field.getType();
            if (!CLASS_ARRAY.contains(type)) {
                result.add(type);
                recursionField(type, result);
            }
        }
    }

    public static String getGenericStringFromType(Type[] types) {
        String genericString = "";
        for (Type type : types) {
            String genericSimpleName = getClassSimpleByTypeName(type.getTypeName());
            genericString += genericSimpleName + ", ";
        }
        genericString = genericString.trim().substring(0, genericString.length() - 2);
        if (StringUtils.isEmpty(genericString)) {
            return "";
        }
        return genericString;
    }

    public static String getClassSimpleByTypeName(String typeName) {
        String[] split = typeName.split("\\.");
        return split[split.length - 1];
    }

    @Test
    public void main() throws ClassNotFoundException {
        String ssss = "com.zy.hello.Controller";
        String line0 = "TestController testController0 = new TestController();";
        String line1 = "TaskAttributeMapper taskAttributeMapper0 = mock(TaskAttributeMapper.class, new ViolatedAssumptionAnswer());";
        String line2 = "Injector.inject(taskAttributeController0, (Class<?>) TaskAttributeController.class, \"taskAttributeMapper\", (Object) taskAttributeMapper0);";
        String line3 = "Injector.validateBean(taskAttributeController0, (Class<?>) TaskAttributeController.class);";
        String line4 = "BaseRequest<TaskAttribute> baseRequest0 = new BaseRequest<TaskAttribute>();";
        String line5 = "Person person0 = new Person();";
        String line6 = "BaseResponse<Object> baseResponse0 = testController0.insert(baseRequest0, taskAttribute0, \"47a eG@hb|\", \"47a eG@hb|\");";
        String line7 = "assertNotNull(baseResponse0);";
       /* List<String> lines = new ArrayList<>();
        lines.add(line0);
        lines.add(line1);
        lines.add(line2);
        lines.add(line3);
        lines.add(line4);
        lines.add(line5);
        lines.add(line6);
        lines.add(line7);*/
        String[] lines = new String[]{line0, line1, line2, line3, line4, line5, line6, line7};

        String mainClass = "^.*=?\\s?taskAttributeController0\\..*\\(.*";
        String ccc = "taskAttributeController0";

//        for (String line : lines) {
//            if (Pattern.matches(mainClass,line)) {
//                String realMethodName = line.substring(line.indexOf(ccc)+ccc.length() + 1, line.indexOf("("));
//                System.out.println(realMethodName);
//                System.out.println(line);
//            }
//
//
//        }

        StringBuilder builder = new StringBuilder();
        for (String line : lines) {
            Map<String, Map<String, Set<Field>>> methodValidParam = getMethodValidParam("com.zy.learning.annotation.TestController");
            Map<String, Set<Field>> noAssignmentFieldMap = getNullFieldMap(lines, methodValidParam.get("getName"));
            for (String s : noAssignmentFieldMap.keySet()) {
                String[] split = s.split("\\.");
                //获取到参数对象的类名
                String className = split[split.length - 1];
                String patternParam = ".*new\\s+" + ClassTypeUtil.upperFirstCapse(className) + "\\(.*";
                if (Pattern.matches(patternParam, line)) {

                    Set<Field> fieldSet = noAssignmentFieldMap.get(s);
                    for (Field field : fieldSet) {
                        //需要新增的行
                        String newLine = "";
                        //参数名称
                        String paramName = "";
                        //此 line 的内容是 实例化参数对象 下面需要设置对象属性值
                        //TODO 获取 paramName 的值
                        paramName = line.split("=")[0];
                        paramName = paramName.replace(ClassTypeUtil.upperFirstCapse(className), "");
                        paramName = paramName.trim();
//                        logger.error("INFO : patternName is " + paramName);
                        Class<?> type = field.getType();
                        String valueString = getRandomValue(type);
                        newLine += paramName + ".set" + ClassTypeUtil.upperFirstCapse(field.getName()) + "(" + valueString + ");";
                        builder.append(newLine);
                    }
                }

            }
        }


//
//        String[] split = s.split("\\.");
//        String className = split[split.length - 1];
//        System.out.println(className);
//
//
//        System.out.println( upperFirstCapse("asdf"));
//        getMethodValidParam();


//        getAutowiredField();
//        System.out.println(mapperClass());
//        System.out.println(controllerClass());


    }

    @Test
    public void test01() {
        System.out.println(RandomUtil.getRandomForIntegerBounded(-128, 127));
        System.out.println(String.valueOf(RandomUtils.nextBoolean()));
        System.out.println(getRandomValue(String.class));
    }

    @Test
    public void test02() {
        System.out.println(null + "");
    }

    @Test
    public void testgetMethodValidParam() {
        String targetClass = "com.zy.learning.annotation.demotest.controller.TEmployeeController";
//        String targetClass = "com.zy.learning.annotation.Address";
        Map<String, Map<String, Set<Field>>> methodValidParam = getMethodValidParam(targetClass);
        System.out.println(methodValidParam);
    }


    @Test
    public void testInteger() {
        Integer i = 0;
        aaa(i);
    }

    private void aaa(Integer integer) {
        Method[] methods = TEmployeeController.class.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }
    }


    @Test
    public void testGetFieldClassImport() {

        Set<Class> fieldClassImport = getFieldClassImport(TEmployeeController.class);
        System.out.println(fieldClassImport);

    }
}

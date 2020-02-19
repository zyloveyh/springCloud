package com.zy.learning.annotation;

import net.sf.jsqlparser.statement.select.Select;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import sun.nio.ch.WindowsSelectorProvider;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.beans.BeanInfo;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public Map<String, Method> getClassDeclareMethods(String targetClass) {
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
                getGenericToParams(params,((ParameterizedTypeImpl) type).getActualTypeArguments());
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
    private Set<Field> getNecessaryField(Class paramType) {
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
                    //TODO 判断是否有对这个 属性的值 进行赋值
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

    private String getRandomValue(Class type) {
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
            return RandomStringUtils.randomAscii(RandomUtil.getRandomForIntegerBounded(6, 10));
        }
        if (type.equals(Boolean.class)) {
            return String.valueOf(RandomUtils.nextBoolean());
        }

        return null;
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
        Map<String, Map<String, Set<Field>>> methodValidParam = getMethodValidParam(
                "com.zy.learning.annotation.demotest.controller.TEmployeeController");
        System.out.println(methodValidParam);
    }


}

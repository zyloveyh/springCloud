package com.zy.learning.lambda;


import com.zy.learning.entity.Man;
import com.zy.learning.entity.Woman;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class lambdaLearning {

    @Test
    public void test2Stream() {
        //获取流的方法：
        /*
         *Collection 集合：
         */
        List<String> list = new ArrayList<>();
        Stream<String> stream1 = list.stream();

        Set<String> set = new HashSet<>();
        Stream<String> stream2 = set.stream();

        Vector<String> vector = new Vector<>();
        Stream<String> stream3 = vector.stream();
        /*
         *根据Map获取流
         */
        Map<String, String> map = new HashMap<>();
        Stream<String> keyStream = map.keySet().stream();
        Stream<String> valueStream = map.values().stream();
        Stream<Map.Entry<String, String>> entryStream = map.entrySet().stream();

        /**
         * 根据数组获取流
         */
        String[] array = {"张无忌", "张翠山", "张三丰", "张一元"};
        Stream<String> stream = Stream.of(array);
    }

    @Test
    public void testMethod() {
        List<String> list = new ArrayList<String>();
        list.add("zy");
        list.add("wf");
        list.add("af");
        list.add("gd");
        list.add("zwy");
        //filter 方法 过滤
        List<String> z = list.stream().filter(s -> s.startsWith("z")).collect(Collectors.toList());
        System.out.println(z);
        //统计个数：count
        Stream.of(10, 20, 30, 40, 50, 60, 70, 80, 90, 100)
                .parallel() //并发流
                .forEach(System.out::println);
    }

    @Test
    public void testStreamForEach() throws InterruptedException {

        List<Man> mans = new ArrayList<>();
        for (Integer i = 0; i < 1000; i++) {
            mans.add(new Man(i.toString(), "x"));
        }
        long start = System.currentTimeMillis();
       /* for (Man man : mans) {
            Thread.sleep(10);
//            if (man.getName() == "") {
//                //100W   7ms
//            }
        }*/
     /*   mans.forEach((man) -> {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            if (man.getName() == "") {
//                //100W   31ms
//            }
        });*/
        mans.stream().parallel().forEach((man) -> {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            if (man.getName() == "") {
//                //100W  普通流  31ms
            //并发流时间更短
//            }
        });
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    @Test
    public void testStreamMap(){
        List<Man> mans = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mans.add(new Man(RandomStringUtils.randomAlphanumeric(5),"man"));
        }
        List<Woman> woman = mans.stream().parallel().map((s) -> {
            Woman w = new Woman();
            w.setAge(RandomStringUtils.randomNumeric(2));
            w.setName(s.getName());
            w.setSex("woman");
            return w;
        }).collect(Collectors.toList());
        System.out.println(woman);

    }

}

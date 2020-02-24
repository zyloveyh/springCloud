package com.zy.learning.annotation;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;

import java.util.Random;

public class RandomUtil {
    /**
     * 随机数Int的生成
     */
    // 随机数生成无边界的Int
    public static int getRandomForIntegerUnbounded() {
        int intUnbounded = new Random().nextInt();
        return intUnbounded;
    }

    // 生成有边界的Int
    public static int getRandomForIntegerBounded(int min, int max) {
        int intBounded = min + ((int) (new Random().nextFloat() * (max - min)));
        return intBounded;
    }

    /**
     * 随机数Long的生成
     */
    // 随机数生成无边界的Long
    public static long getRandomForLongUnbounded() {
        long unboundedLong = new Random().nextLong();
        return unboundedLong;
    }

    // 因为Random类使用的种子是48bits，所以nextLong不能返回所有可能的long值，long是64bits。
    // 使用Random生成有边界的Long
    public static long getRandomForLongBounded(long min, long max) {
        long rangeLong = min + (((long) (new Random().nextDouble() * (max - min))));
        return rangeLong;
    }

    /**
     * 随机数Float的生成
     */
    // 随机数Float的生成生成0.0-1.0之间的Float随机数
    public static float getRandomForFloat0To1() {
        float floatUnbounded = new Random().nextFloat();
        return floatUnbounded;
    }

    // 以上只会生成包含0.0而不包括1.0的float类型随机数生成有边界的Float随机数
    public static float getRandomForFloatBounded(float min, float max) {
        float floatBounded = min + new Random().nextFloat() * (max - min);
        return floatBounded;
    }

    /**
     * 随机数Double的生成
     */
    // 生成0.0d-1.0d之间的Double随机数
    public static double getRandomForDouble0To1() {
        double generatorDouble = new Random().nextDouble();
        return generatorDouble;
    }

    // 与Float相同，以上方法只会生成包含0.0d而不包含1.0d的随机数生成带有边界的Double随机数
    public static double getRandomForDoubleBounded(double min, double max) {
        double boundedDouble = min + new Random().nextDouble() * (max - min);
        return boundedDouble;
    }


    public static String getRandomStringByLevel(Integer min, Integer max, String level) {
        Integer i = randomLevelInt(min, max, level);
        return RandomStringUtils.randomAscii(i.intValue());
    }

    public static Integer getRandomIntegerValueByLevel(Integer min, Integer max, String level) {
        return randomLevelInt(min, max, level);
    }

    private static Integer randomLevelInt(Integer min, Integer max, String level) {
        if (min>max) {
            return getRandomForIntegerUnbounded();
        }
        Integer minTem = (max - min) / 3 + min;
        Integer maxTem = max - (max - min) / 3;
        if (ClassTypeUtil.LOW.equals(level)) {
            return RandomUtil.getRandomForIntegerBounded(min, minTem);
        } else if (ClassTypeUtil.MIDDLE.equals(level)) {
            return RandomUtil.getRandomForIntegerBounded(minTem, maxTem);
        } else if (ClassTypeUtil.HEIGHT.equals(level)) {
            return RandomUtil.getRandomForIntegerBounded(maxTem, max + 1);
        }
        return RandomUtil.getRandomForIntegerBounded(min, max + 1);
    }


}

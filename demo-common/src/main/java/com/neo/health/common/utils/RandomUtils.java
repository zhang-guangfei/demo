package com.neo.health.common.utils;

import cn.hutool.core.util.RandomUtil;

public class RandomUtils {


    public static String str(int min, int max) {
        return RandomUtil.randomString(max).substring(0, RandomUtil.randomInt(min, max));
    }

}

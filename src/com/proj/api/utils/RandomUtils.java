package com.proj.api.utils;


import java.util.Random;
import java.util.UUID;

import java.util.UUID;

import static java.lang.Math.abs;

/**
 * Created by jangitlau on 2017/11/2.
 */
public class RandomUtils {
    public static String getRandomString(int _iSize) {
        String sList = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String sRandStr = "";
        java.util.Random random = new java.util.Random(System.nanoTime());
        char str;
        for (int i = 0; i < _iSize; i++) {
            str = sList.charAt(abs(random.nextInt()) % (sList.length() - 1));
            sRandStr += str;
        }
        return sRandStr;
    }

    public static String getRandomNumStr(int _iSize) {
        String sRandStr = "";
        java.util.Random random = new java.util.Random(System.nanoTime());
        return String.valueOf(random.nextInt());
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}

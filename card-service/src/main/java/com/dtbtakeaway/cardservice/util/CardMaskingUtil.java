package com.dtbtakeaway.cardservice.util;

public class CardMaskingUtil {

    public static String maskPan(String pan) {
        if (pan.length() < 8) return "****";
        return pan.substring(0, 4) + "****" + pan.substring(pan.length() - 4);
    }

    public static String maskCvv(String cvv) {
        return "***";
    }
}


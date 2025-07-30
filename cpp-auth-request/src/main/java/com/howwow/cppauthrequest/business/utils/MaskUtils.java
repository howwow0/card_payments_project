package com.howwow.cppauthrequest.business.utils;

public class MaskUtils {
    public static String maskCardNumber(String cardNumber) {
        return cardNumber == null ? "****"
                : cardNumber.replaceAll("(?<=.{6}).(?=.{4})", "*");
    }
}

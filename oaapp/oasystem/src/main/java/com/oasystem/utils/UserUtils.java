package com.oasystem.utils;

public class UserUtils {
    private static String token = "";


    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        assert token != null;
        UserUtils.token = token;
    }
}

package com.lovetravel.mws.util;

/**
 * Utility function to deal with String
 */
public class StringUtil {
    public static String trimStr(String str) {
        if (str != null) {
            return str.trim();
        } else {
            return str;
        }
    }

    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static boolean isNonEmpty(String str) {
        return str != null && str.trim().length() != 0;
    }
    
    public static boolean isEmptyWithNoTrim(String str) {
        return str == null || str.length() == 0;
    }
}

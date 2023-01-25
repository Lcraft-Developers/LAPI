package de.lcraft.api.javaUtils;

public class StringUtils {

    public static String replaceEnd(String str, String replace, String replaced) {
        int startIndex = str.indexOf(replace);
        int lastIndex = startIndex + replace.length();

        StringBuilder stringBuilder = new StringBuilder(str);
        if(startIndex > -1) stringBuilder.replace(startIndex, lastIndex, replaced);
        return stringBuilder.toString();
    }

}

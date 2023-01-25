package de.lcraft.api.javaUtils;

public class StringUtils {

    public static String replaceLast(String str, String replace, String replaced) {
        int startIndex = str.indexOf(replace);
        int lastIndex = startIndex + replace.length();

        StringBuilder stringBuilder = new StringBuilder(str);
        if(startIndex > -1) stringBuilder.replace(startIndex, lastIndex, replaced);
        return stringBuilder.toString();
    }
    private static int lengthLetters(int type, String str) {
        int amount = 0;
        for(char cha : str.toCharArray()) {
            if(type == 0 && Character.isLowerCase(cha)) amount++;
            if(type == 1 && Character.isUpperCase(cha)) amount++;
            if(type == 2 && Character.isSpaceChar(cha)) amount++;
        }
        return amount;
    }
    public static int lengthAllLowerCaseLetters(String str) {
        return lengthLetters(0, str);
    }
    public static int lengthAllUpperCaseLetters(String str) {
        return lengthLetters(1, str);
    }
    public static int lengthAllSpaces(String str) {
        return lengthLetters(2, str);
    }

}

package de.lcraft.api.javaUtils;

public class ArrayUtils {

    public static boolean containsFromStringArray(String[] array, String cont) {
        for(String c : array) {
            if(c.equalsIgnoreCase(cont)) return true;
        }
        return false;
    }

}

package de.lcraft.api.plugin.modules.java.utils;

import java.util.ArrayList;

public class ArrayHelper {

    public static String[] makeArrayListToStringArray(ArrayList<String> arrayList) {
        String[] all = new String[arrayList.size()];
        for(int i = 0; i < arrayList.size(); i++) {
            all[i] = arrayList.get(i);
        }
        return all;
    }

    public static boolean containsFromStringArray(String[] array, String cont) {
        for(String c : array) {
            if(c.equalsIgnoreCase(cont)) {
                return true;
            }
        }
        return false;
    }

}

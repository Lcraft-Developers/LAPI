package de.lcraft.api.javaUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListUtils extends ArrayUtils {

    public static String[] makeListToStringArray(List<String> arrayList) {
        String[] all = new String[arrayList.size()];
        for(int i = 0; i < arrayList.size(); i++) {
            all[i] = arrayList.get(i);
        }
        return all;
    }
    public static ArrayList<String> makeStringArrayToArrayList(String... array) {
        return new ArrayList<>(Arrays.asList(array));
    }

}

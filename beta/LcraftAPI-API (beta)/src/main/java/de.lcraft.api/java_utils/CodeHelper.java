package de.lcraft.api.java_utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CodeHelper {

    public String[] makeArrayListToStringArray(ArrayList<String> arrayList) {
        String[] all = new String[arrayList.size()];
        for(int i = 0; i < arrayList.size(); i++) {
            all[i] = arrayList.get(i);
        }
        return all;
    }
    public ArrayList<String> makeStringArrayToArrayList(String... array) {
        ArrayList<String> all = new ArrayList<>();
        for(String c : array) {
            all.add(c);
        }
        return all;
    }
    public boolean containsFromStringArray(String[] array, String cont) {
        for(String c : array) {
            if(c.equalsIgnoreCase(cont)) {
                return true;
            }
            continue;
        }
        return false;
    }
    public int getRandom(int min, int max) {
        Random r = new Random();
        return r.nextInt(max + 1 - min) + min;
    }
    public List<File> getAllFilesFromADirectory(String path) {
        return Arrays.stream(new File(path).listFiles()).toList();
    }

}
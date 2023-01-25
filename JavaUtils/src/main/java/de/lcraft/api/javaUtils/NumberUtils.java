package de.lcraft.api.javaUtils;

import java.util.Random;

public class NumberUtils {

    public static int getRandom(int min, int max) {
        return new Random().nextInt(max + 1 - min) + min;
    }

}

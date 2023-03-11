package de.lcraft.lapi.javaUtils;

import java.util.Random;

public class NumberUtils {

    public static int getRandom(int min, int max) {
        return new Random().nextInt(max + 1 - min) + min;
    }
    public static boolean isInteger(double v) {
        return (v== Math.floor(v)) && !Double.isInfinite(v);
    }

}

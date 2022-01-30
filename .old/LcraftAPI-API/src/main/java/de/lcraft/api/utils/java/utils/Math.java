package de.lcraft.api.utils.java.utils;

import java.util.Random;

public class Math {
    public static int getRandom(int min, int max) {
        Random r = new Random();
        return r.nextInt(max + 1 - min) + min;
    }
}


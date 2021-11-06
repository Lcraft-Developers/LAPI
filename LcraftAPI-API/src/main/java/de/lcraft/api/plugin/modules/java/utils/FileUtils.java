package de.lcraft.api.plugin.modules.java.utils;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class FileUtils {

    public static List<File> getAllFilesFromADirectory(String path) {
        return Arrays.stream(new File(path).listFiles()).toList();
    }

}

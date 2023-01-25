package de.lcraft.api.javaUtils;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class FileUtils {

    public static List<File> getAllFilesFromFolder(File folder) {
        return Arrays.asList(folder.listFiles());
    }

}

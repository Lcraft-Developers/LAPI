package de.lcraft.api.plugin.modules.java.utils;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.List;

public class FileUtils {

    public static List<File> getAllFilesFromADirectory(String path) {
        return Arrays.stream(new File(path).listFiles()).toList();
    }

    public static void addPath(File f) throws Exception {
        URL u = f.toURL();
        URLClassLoader urlClassLoader = (URLClassLoader) ClassLoader.getPlatformClassLoader();
        Class urlClass = URLClassLoader.class;
        Method method = urlClass.getDeclaredMethod("addURL", new Class[]{URL.class});
        method.setAccessible(true);
        method.invoke(urlClassLoader, new Object[]{u});
    }

}

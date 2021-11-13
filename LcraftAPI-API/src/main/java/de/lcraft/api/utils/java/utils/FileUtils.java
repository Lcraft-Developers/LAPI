package de.lcraft.api.utils.java.utils;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class FileUtils {

    public static List<File> getAllFilesFromADirectory(String path) {
        return Arrays.stream(new File(path).listFiles()).toList();
    }

    /*public static void addPath(File f) throws Exception {
        URL u = f.toURL();
        URLClassLoader urlClassLoader = new URLClassLoader(ClassLoader.getPlatformClassLoader().getClass().get);
        Class<?> urlClass = Class.forName("nameofclass", true, new URLClassLoader(urlClassLoader.getURLs()));
        Method method = urlClass.getDeclaredMethod("addURL", new Class[]{URL.class});
        method.setAccessible(true);
        method.invoke(urlClassLoader, new Object[]{u});
    }

    public class ClassPathManager {

        private static final Class[] parameters = new Class[]{URL.class};

        public static void addFile(String s) throws IOException {
            File f = new File(s);
            addFile(f);
        }//end method

        public static void addFile(File f) throws IOException {
            addURL(f.toURL());
        }//end method


        public static void addURL(URL u) throws IOException {
            URL[] test = new URL[1];
            test[0] = u;
            URLClassLoader sysloader = new URLClassLoader(test,  ClassLoader.getPlatformClassLoader());
            Class sysclass = URLClassLoader.class;

            try {
                Method method = sysclass.getDeclaredMethod("addURL", parameters);
                method.setAccessible(true);
                method.invoke(sysloader, new Object[]{u});
            } catch (Throwable t) {
                t.printStackTrace();
                throw new IOException("Error, could not add URL to system classloader");
            }//end try catch

        }//end method

    }//end class*/

}

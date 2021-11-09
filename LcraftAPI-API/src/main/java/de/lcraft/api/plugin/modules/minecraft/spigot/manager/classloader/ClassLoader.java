package de.lcraft.api.plugin.modules.minecraft.spigot.manager.classloader;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;

public class ClassLoader extends URLClassLoader {

    public ClassLoader(URL[] urls) {
        super(urls);
    }

    public void addFile(File file) {
        addPath(file.toPath());
    }

    public void addPath(Path path) {
        try {
            //addURL(path.toUri().toURL());
            addToClasspath(path.toUri().toURL());
        } catch (MalformedURLException e) {
            throw new AssertionError(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addToClasspath(URL url) throws IOException {
        java.lang.ClassLoader sysloader = ClassLoader.getSystemClassLoader();
        Class sysclass = URLClassLoader.class;

        try {
            Method method = sysclass.getDeclaredMethod("addURL");
            method.setAccessible(true);
            method.invoke(sysloader, new Object[]{url});
        } catch (Throwable t) {
            t.printStackTrace();
            throw new IOException("Error, could not add URL to system classloader");
        }
    }

}

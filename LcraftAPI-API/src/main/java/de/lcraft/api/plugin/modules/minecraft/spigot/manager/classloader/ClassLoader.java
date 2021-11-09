package de.lcraft.api.plugin.modules.minecraft.spigot.manager.classloader;

import java.io.File;
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
            addURL(path.toUri().toURL());
        } catch (MalformedURLException e) {
            throw new AssertionError(e);
        }
    }

    public void addToClasspath(Object plugin, Path path) {
        java.lang.ClassLoader pluginClassloader = path.getClass().getClassLoader();
        if (pluginClassloader instanceof ClassLoader) {
            ((ClassLoader) pluginClassloader).addPath(path);
        } else {
            throw new UnsupportedOperationException(
                    "Operation is not supported on non-Java Velocity plugins.");
        }
    }

}

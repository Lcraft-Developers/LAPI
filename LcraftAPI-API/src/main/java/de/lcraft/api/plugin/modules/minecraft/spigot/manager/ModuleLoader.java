package de.lcraft.api.plugin.modules.minecraft.spigot.manager;

import de.lcraft.api.plugin.modules.minecraft.spigot.manager.classloader.ClassLoader;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class ModuleLoader {

    private ModuleManager moduleManager;

    public ModuleLoader(ModuleManager moduleManager) {
        this.moduleManager = moduleManager;
    }

    public void loadModuleToClasspath(Module module) {
        getModuleManager().getModules().add(module);

        // TODO: Add the file from module to the classpath

        URL[] urls = new URL[1];
        try {
            urls[0] = new File("lmodules/").toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        ClassLoader classLoader = new ClassLoader(urls);
        classLoader.addFile(module.getMainFile());
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

}

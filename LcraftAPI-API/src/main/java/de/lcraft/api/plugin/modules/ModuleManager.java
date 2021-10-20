package de.lcraft.api.plugin.modules;

import org.yaml.snakeyaml.Yaml;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Map;
import java.util.zip.ZipFile;

public class ModuleManager {

    public static volatile ArrayList<Module> modules = new ArrayList<>();

    public void loadModules() {
        File dir = new File("lmodules/");
        if(!dir.exists()) dir.mkdir();
        File[] directoryListing = dir.listFiles();
        if(directoryListing != null) {
            for(File file : directoryListing) {
                loadModule(file);
                break;
            }
        }
    }
    public void loadModule(File file) {
        if(!file.isDirectory() && file.getName().endsWith(".jar")) {
            try {
                ZipFile jarFile = new ZipFile(file);
                Yaml yaml = new Yaml();
                InputStream inputStream = jarFile.getInputStream(jarFile.getEntry("module.yml"));
                Map<String, Object> data = yaml.load(inputStream);
                if(data.get("name") != null) {
                    if(data.get("main") != null) {
                        ClassLoader classLoader = URLClassLoader.newInstance(new URL[]{file.toURI().toURL()}, getClass().getClassLoader());
                        Class<?> clazz = classLoader.loadClass(data.get("main").toString());
                        Class<? extends Module> pluginClass = clazz.asSubclass(Module.class);
                        Module module = pluginClass.newInstance();
                        ModuleLoader moduleLoader = new ModuleLoader(module);
                        moduleLoader.loadModule();
                    } else {
                        System.out.println("Das Modul " + data.get("name") + " konnte nicht geladen werden da die main in der module.yml nicht existiert");
                    }
                } else {
                    System.out.println("Ein Modul konnte nicht geladen werdem da der Name in der module.yml nicht existiert!");
                }
            } catch(IOException | ClassNotFoundException | IllegalAccessException | InstantiationException exception) {
                exception.printStackTrace();
            }
        }
    }

    public static ArrayList<Module> getModules() {
        return modules;
    }

    public void onEnableAllModules() {
        for(Module c : getModules()) {
            c.onEnable();
        }
    }

    public void onLoadAllModules() {
        for(Module c : getModules()) {
            c.onLoad();
        }
    }

    public void onDisableAllModules() {
        for(Module c : getModules()) {
            c.onDisable();
        }
    }

}

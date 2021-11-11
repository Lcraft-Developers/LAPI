package de.lcraft.api.plugin.modules.minecraft.spigot.manager.classes;

import de.lcraft.api.plugin.modules.minecraft.spigot.manager.ModuleManager;

import java.util.HashMap;

public class ClassManager {

    private HashMap<String, Class> classes;
    private ModuleManager manager;

    public ClassManager(ModuleManager moduleManager) {
        classes = new HashMap<>();
        this.manager = moduleManager;
    }

    public Class<?> getClassByName(String name) {
        Class<?> cachedClass = classes.get(name);

        if(cachedClass != null) {
            return cachedClass;
        }
        return null;
    }

    public void setClass(String name, final Class<?> clazz) {
        if(!classes.containsKey(name)) {
            classes.put(name, clazz);
        }
    }

    public ModuleManager getModuleManager() {
        return manager;
    }
    public HashMap<String, Class> getClasses() {
        return classes;
    }

}

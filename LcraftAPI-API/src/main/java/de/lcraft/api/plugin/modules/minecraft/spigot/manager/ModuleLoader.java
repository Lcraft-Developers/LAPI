package de.lcraft.api.plugin.modules.minecraft.spigot.manager;

import de.lcraft.api.plugin.modules.minecraft.spigot.manager.classloader.ClassLoader;

public class ModuleLoader {

    private ModuleManager moduleManager;

    public ModuleLoader(ModuleManager moduleManager) {
        this.moduleManager = moduleManager;
    }

    public void loadModuleToClasspath(Module module) {
        getModuleManager().getModules().add(module);

        // TODO: Add the file from module to the classpath
        ClassLoader classLoader = new ClassLoader();
        classLoader.addFile(module.getMainFile());
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

}

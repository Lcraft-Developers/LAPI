package de.lcraft.api.plugin.modules.minecraft.spigot.manager;

public class ModuleLoader {

    private ModuleManager moduleManager;

    public ModuleLoader(ModuleManager moduleManager) {
        this.moduleManager = moduleManager;
    }

    public void loadModule(Module module) {
        getModuleManager().getModules().add(module);

        // TODO: Add the file from module to the classpath
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

}

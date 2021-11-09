package de.lcraft.api.plugin.modules.minecraft.bungeecord.manager;

public class ModuleLoader {

    private ModuleManager moduleManager;

    public ModuleLoader(ModuleManager moduleManager) {
        this.moduleManager = moduleManager;
    }

    public void loadModuleToClasspath(Module module) {
        getModuleManager().getModules().add(module);

        // TODO: Add the file from module to the classpath
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

}

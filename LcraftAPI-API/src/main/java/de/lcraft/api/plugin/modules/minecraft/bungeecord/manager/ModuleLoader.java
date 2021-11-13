package de.lcraft.api.plugin.modules.minecraft.bungeecord.manager;

import java.util.ArrayList;

public class ModuleLoader {

    private ModuleManager moduleManager;

    public ModuleLoader(ModuleManager moduleManager) {
        this.moduleManager = moduleManager;
    }
    public void loadModuleToService(ArrayList<Module> modules) throws Exception {
        // Add Modules to ArrayList
        for(Module c : modules) {
            getModuleManager().getModules().add(c);
        }

        // Reload Configuration
        for(Module c : modules) {
            c.getModuleDescriptionFile().load();
            c.getModuleDescriptionFile().reloadRequiredModules(moduleManager);
        }
    }
    public ModuleManager getModuleManager() {
        return moduleManager;
    }

}

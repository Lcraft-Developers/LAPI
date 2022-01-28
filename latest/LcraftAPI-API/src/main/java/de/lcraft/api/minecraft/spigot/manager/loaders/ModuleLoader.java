package de.lcraft.api.minecraft.spigot.manager.loaders;

import de.lcraft.api.minecraft.spigot.manager.Module;
import de.lcraft.api.minecraft.spigot.manager.ModuleManager;

import java.util.ArrayList;

public class ModuleLoader {

    private ModuleManager moduleManager;

    public ModuleLoader(ModuleManager moduleManager) {
        this.moduleManager = moduleManager;
    }
    public void loadModuleToService(ArrayList<Module> modules) throws Exception {
        // Reload Configuration
        for(Module c : modules) {
            c.getModuleDescriptionFile().load();
            c.getModuleDescriptionFile().reloadRequiredModules(moduleManager);
        }

        // Add Modules to ArrayList
        for(Module c : modules) {
            getModuleManager().getModules().add(c);
        }
    }
    public ModuleManager getModuleManager() {
        return moduleManager;
    }

}

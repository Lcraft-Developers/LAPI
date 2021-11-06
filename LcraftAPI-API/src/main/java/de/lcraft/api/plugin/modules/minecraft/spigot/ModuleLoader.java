package de.lcraft.api.plugin.modules.minecraft.spigot;

import java.io.IOException;

public class ModuleLoader {

    private Module module;

    public ModuleLoader(Module module) {
        this.module = module;
    }

    public void loadModule() throws IOException {
        System.out.println("The Spigot Module " + module.getName() + " will be loaded.");
        module.onLoad();
        enableModule();
    }
    public void enableModule() {
        System.out.println("The Spigot Module " + module.getName() + " is loaded.");
        module.onEnable();
        ModuleManager.modules.add(module);
    }
    public void disableModule() {
        System.out.println("The Spigot Module " + module.getName() + " was disabled.");
        module.onDisable();
        ModuleManager.modules.remove(module);
    }

}

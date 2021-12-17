package de.lcraft.api.minecraft.bungeecord.manager;

import java.io.IOException;

public class ModuleEventManager {

    private Module module;

    public ModuleEventManager(Module module) {
        this.module = module;
    }

    public void enableModule() throws IOException {
        System.out.println("The Spigot Module " + module.getModuleDescriptionFile().getName() + " is loaded.");
        module.onEnable();
    }
    public void disableModule() throws IOException {
        System.out.println("The Spigot Module " + module.getModuleDescriptionFile().getName() + " was disabled.");
        module.onDisable();
    }

}

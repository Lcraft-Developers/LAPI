package de.lcraft.api.minecraft.spigot.manager;

import de.lcraft.api.java_utils.Internet;
import de.lcraft.api.minecraft.spigot.manager.logger.ModuleLoggerType;

import java.io.IOException;

public class ModuleEventManager {

    private Module module;

    public ModuleEventManager(Module module) {
        this.module = module;
    }

    public void enableModule() throws IOException {
        module.getLogger().send(ModuleLoggerType.INFO, "The Spigot Module " + module.getModuleDescriptionFile().getName() + " is loaded.");
        module.onEnable();
    }
    public void sendUpdateMessageModule() {
        if(module.getModuleDescriptionFile().getUpdate_url() != null) {
            if(new Internet.SpigotMc().isUpdated(module.getModuleDescriptionFile().getUpdate_url(), module.getModuleDescriptionFile().getVersion())) {
                module.getLogger().send(ModuleLoggerType.INFO, "The Spigot Module " + module.getModuleDescriptionFile().getName() + " is up to date.");
            } else {
                module.getLogger().send(ModuleLoggerType.INFO, "The Spigot Module " + module.getModuleDescriptionFile().getName() + " is outdated.");
            }
        } else if(module.getModuleDescriptionFile().getSpigotmc_id() != null) {
            if(new Internet.SpigotMc().isUpdated(Integer.valueOf(module.getModuleDescriptionFile().getSpigotmc_id()), module.getModuleDescriptionFile().getVersion())) {
                module.getLogger().send(ModuleLoggerType.INFO, "The Spigot Module " + module.getModuleDescriptionFile().getName() + " is up to date.");
            } else {
                module.getLogger().send(ModuleLoggerType.INFO, "The Spigot Module " + module.getModuleDescriptionFile().getName() + " is outdated.");
            }
        }
    }
    public void disableModule() throws IOException {
        module.getLogger().send(ModuleLoggerType.INFO, "The Spigot Module " + module.getModuleDescriptionFile().getName() + " was disabled.");
        module.onDisable();
    }

}

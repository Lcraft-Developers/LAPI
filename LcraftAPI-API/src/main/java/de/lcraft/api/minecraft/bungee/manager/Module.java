package de.lcraft.api.minecraft.bungee.manager;

import de.lcraft.api.minecraft.bungee.manager.command.ModuleCommandManager;
import de.lcraft.api.minecraft.bungee.manager.logger.ModuleLogger;
import de.lcraft.api.minecraft.bungee.manager.utils.LanguagesManager;
import de.lcraft.api.minecraft.bungee.manager.utils.ListenerManager;
import de.lcraft.api.minecraft.bungee.manager.utils.PermsManager;
import net.md_5.bungee.api.plugin.Plugin;
import java.io.File;
import java.io.IOException;

public abstract class Module {

    private ModuleDescriptionFile moduleDescriptionFile;
    private ModuleLogger moduleLogger;
    private ModuleCommandManager moduleCommandManager;
    private LanguagesManager languagesManager;
    private Plugin plugin;
    private ListenerManager.ModuleListenerManager listenerManager;
    private File file;
    private ModuleManager manager;
    private PermsManager permsManager;
    private ModuleConfig config;

    public void load(ModuleManager manager) throws Exception {
        this.manager = manager;
        ModuleEventManager eventManager = new ModuleEventManager(this);

        moduleDescriptionFile = new ModuleDescriptionFile(file);
        moduleDescriptionFile.load();
        moduleLogger = new ModuleLogger(moduleDescriptionFile.getName());
        config = new ModuleConfig(getModuleDescriptionFile().getName(), "config.yml");

        permsManager = new PermsManager();
        languagesManager = new LanguagesManager();
        moduleCommandManager = new ModuleCommandManager(this);
        listenerManager = new ListenerManager.ModuleListenerManager(this);

        eventManager.enableModule();

        listenerManager.registerAllListeners();
        moduleCommandManager.reloadConfigs();
    }

    public abstract void onEnable() throws IOException;
    public abstract void onDisable() throws IOException;

    public ModuleLogger getLogger() {
        return moduleLogger;
    }
    public ModuleCommandManager getModuleCommandManager() {
        return moduleCommandManager;
    }
    public Plugin getPlugin() {
        return plugin;
    }
    public void setPlugin(Plugin plugin) {
        this.plugin = plugin;
    }
    public void setFile(File file) {
        this.file = file;
    }
    public File getFile() {
        return file;
    }
    public ListenerManager.ModuleListenerManager getListenerManager() {
        return listenerManager;
    }
    public ModuleDescriptionFile getModuleDescriptionFile() {
        return moduleDescriptionFile;
    }
    public void setModuleDescriptionFile(ModuleDescriptionFile moduleDescriptionFile) {
        this.moduleDescriptionFile = moduleDescriptionFile;
    }
    public LanguagesManager getLanguagesManager() {
        return languagesManager;
    }
    public ModuleManager getModuleManager() {
        return manager;
    }
    public PermsManager getPermsManager() {
        return permsManager;
    }

}

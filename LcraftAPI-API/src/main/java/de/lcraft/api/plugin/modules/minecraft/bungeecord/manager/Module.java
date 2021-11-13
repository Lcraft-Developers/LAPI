package de.lcraft.api.plugin.modules.minecraft.bungeecord.manager;

import de.lcraft.api.plugin.modules.minecraft.bungeecord.manager.logger.ModuleLogger;
import de.lcraft.api.plugin.modules.minecraft.bungeecord.module.commands.ModuleCommandManager;
import de.lcraft.api.plugin.modules.minecraft.bungeecord.module.listeners.ListenerManager;
import net.md_5.bungee.api.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.io.IOException;

public abstract class Module {

    private ModuleDescriptionFile moduleDescriptionFile;
    private ModuleLogger moduleLogger;
    private ModuleCommandManager moduleCommandManager;
    private Plugin plugin;
    private ListenerManager listenerManager;
    private File file;
    private ModuleManager manager;

    public void load(ModuleManager manager) throws Exception {
        moduleDescriptionFile = new ModuleDescriptionFile(file);
        moduleDescriptionFile.load();
        moduleLogger = new ModuleLogger(moduleDescriptionFile.getName());
        moduleCommandManager = new ModuleCommandManager(this);
        listenerManager = new ListenerManager(this);
        this.manager = manager;
        listenerManager.registerAllListeners();
    }

    public abstract void onLoad() throws IOException;
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
    public ListenerManager getListenerManager() {
        return listenerManager;
    }
    public ModuleDescriptionFile getModuleDescriptionFile() {
        return moduleDescriptionFile;
    }
    public void setModuleDescriptionFile(ModuleDescriptionFile moduleDescriptionFile) {
        this.moduleDescriptionFile = moduleDescriptionFile;
    }

}

package de.lcraft.api.plugin.modules.minecraft.bungeecord;

import de.lcraft.api.plugin.logger.Logger;
import de.lcraft.api.plugin.modules.minecraft.bungeecord.commands.ModuleCommandManager;
import de.lcraft.api.plugin.modules.minecraft.bungeecord.listeners.ListenerManager;
import net.md_5.bungee.api.plugin.Plugin;

public abstract class Module {

    private String name,
                   id;
    private Logger logger;
    private ModuleCommandManager moduleCommandManager;
    private Plugin plugin;
    private ListenerManager listenerManager;

    public Module(String name, String id) {
        this.name = name;
        this.id = id;
        logger = new Logger(getName());
        moduleCommandManager = new ModuleCommandManager(this);
        listenerManager = new ListenerManager(this);
    }

    public abstract void onLoad();
    public abstract void onEnable();
    public abstract void onDisable();

    public String getName() {
        return name;
    }
    public Logger getLogger() {
        return logger;
    }
    public String getId() {
        return id;
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

}

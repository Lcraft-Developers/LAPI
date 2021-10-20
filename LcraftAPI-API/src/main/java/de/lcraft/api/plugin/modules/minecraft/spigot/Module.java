package de.lcraft.api.plugin.modules.minecraft.spigot;

import de.lcraft.api.plugin.logger.Logger;
import de.lcraft.api.plugin.modules.minecraft.spigot.commands.ModuleCommandManager;
import de.lcraft.api.plugin.modules.minecraft.spigot.listeners.ListenerManager;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class Module {

    private String name,
                   id;
    private Logger logger;
    private ModuleCommandManager moduleCommandManager;
    private JavaPlugin plugin;
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
    public JavaPlugin getPlugin() {
        return plugin;
    }
    public void setPlugin(JavaPlugin plugin) {
        this.plugin = plugin;
    }

}
